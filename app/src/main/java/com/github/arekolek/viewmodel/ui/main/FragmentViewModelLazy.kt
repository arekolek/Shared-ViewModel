package com.github.arekolek.viewmodel.ui.main

import android.app.Activity
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import kotlin.reflect.KClass

/**
 * Returns a property delegate to access Activity's [ViewModel], if [factory] is specified
 * it will be used to create [ViewModel] first time.
 *
 * ```
 * class MyFragment : Fragment() {
 *     val viewmodel: NYViewModel by viewmodels()
 * }
 * ```
 *
 * This property can be accessed only after this Fragment is attached i.e., after
 * [Fragment.onAttach()], and access prior to that will result in IllegalArgumentException.
 */
@MainThread
inline fun <reified VM : ViewModel> Fragment.viewModels(
    factory: ViewModelProvider.Factory? = null,
    noinline lazyOwner: () -> ViewModelStoreOwner = { this }
): Lazy<VM> =
    FragmentViewModelLazy(lazyOwner, VM::class, factory)

/**
 * An implementation of [Lazy] used by [Fragment.viewModels] tied to the given [fragment],
 * [viewModelClass], [factory]
 */
class FragmentViewModelLazy<VM : ViewModel>(
    private val lazyOwner: () -> ViewModelStoreOwner,
    private val viewModelClass: KClass<VM>,
    private val factory: ViewModelProvider.Factory?
) : Lazy<VM> {
    private var cached: VM? = null

    override val value: VM
        get() {
            val owner = lazyOwner()
            var viewModel = cached
            if (viewModel == null) {
                val activity = (owner as? Fragment)?.activity ?: owner as? Activity
                val application = activity?.application
                    ?: throw IllegalArgumentException(
                        "ViewModel can be accessed " +
                                "only when Fragment is attached"
                    )
                val resolvedFactory = factory ?: ViewModelProvider.AndroidViewModelFactory.getInstance(
                    application
                )
                viewModel = ViewModelProvider(owner, resolvedFactory).get(viewModelClass.java)
                cached = viewModel
            }
            return viewModel
        }

    override fun isInitialized() = cached != null
}
