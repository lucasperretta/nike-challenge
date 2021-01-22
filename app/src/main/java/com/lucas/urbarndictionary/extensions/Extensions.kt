package com.lucas.urbarndictionary.extensions

import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.lifecycle.MutableLiveData
import java.text.SimpleDateFormat
import java.util.*

fun String.toHtml(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

fun String.parseDate(format: String): Date? {
    return SimpleDateFormat(format, Locale.getDefault()).parse(this)
}

fun Date.toStringFormat(format: String): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(this)
}
