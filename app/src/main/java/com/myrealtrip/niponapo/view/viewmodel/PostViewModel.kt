package com.example.niponapo_3.ui.post

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PostViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is posting Fragment"
    }
    val text: LiveData<String> = _text
}