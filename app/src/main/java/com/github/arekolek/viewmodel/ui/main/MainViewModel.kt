package com.github.arekolek.viewmodel.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _text = MutableLiveData<Int>().apply { value = 0 }
    val text: LiveData<Int> = _text

    fun handleClick() {
        _text.value = _text.value!! + 1
    }

}
