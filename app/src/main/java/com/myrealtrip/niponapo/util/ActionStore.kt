package com.myrealtrip.niponapo.util

interface ActionStore {
    fun showMessage(message: String, extra: Boolean, listener: MessageDialogClickListener?)
}