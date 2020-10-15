package com.example.niponapo_3.ui.notice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NoticeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notice Fragment"
    }
    val text: LiveData<String> = _text
}