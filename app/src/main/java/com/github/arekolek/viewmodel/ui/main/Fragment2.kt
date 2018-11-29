package com.github.arekolek.viewmodel.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.arekolek.viewmodel.R
import kotlinx.android.synthetic.main.fragment2.*

class Fragment2 : Fragment() {

    companion object {
        fun newInstance() = Fragment2()
    }

    private val viewModel: MainViewModel by viewModels { requireNotNull(parentFragment) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment2, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.text.observe(this) {
            textView.text = it
        }
    }

}
