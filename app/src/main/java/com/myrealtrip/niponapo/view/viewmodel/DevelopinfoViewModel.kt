package com.example.niponapo_3.ui.developinfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DevelopinfoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is development info Fragment"
    }
    val text: LiveData<String> = _text
}