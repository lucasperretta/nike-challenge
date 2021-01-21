package com.lucas.urbarndictionary.extensions

import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.lifecycle.MutableLiveData

fun String.toHtml(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

fun <T> MutableLiveData<T>.notifyObserver() {
    this.value = this.value
}