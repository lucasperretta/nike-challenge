package com.lucas.urbarndictionary.kotlin

import android.app.Activity
import android.content.res.ColorStateList
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.lifecycle.MutableLiveData
import com.chocolate.utilities.Time
import java.util.*

fun MutableLiveData<*>.notifyObservers() {
    this.value = this.value
}

fun String.toHtml() = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)

fun String.parseDate(format: String): Date? = Time.convert.toDate(this, format)

fun Date.toStringFormat(format: String): String? = Time.convert.toString(this, format)

fun Activity.getTint(@ColorRes id: Int) = ColorStateList.valueOf(ContextCompat.getColor(this, id))