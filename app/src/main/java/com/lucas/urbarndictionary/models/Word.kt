package com.lucas.urbarndictionary.models

import com.google.gson.annotations.SerializedName
import com.lucas.urbarndictionary.kotlin.parseDate
import com.lucas.urbarndictionary.kotlin.toStringFormat
import java.util.*

@Suppress("MemberVisibilityCanBePrivate")
class Word {

    lateinit var word: String
    lateinit var definition: String
    lateinit var example: String
    lateinit var author: String
    @SerializedName("written_on") lateinit var writtenOn: String
    @SerializedName("thumbs_up") var thumbsUp: Int? = null
    @SerializedName("thumbs_down") var thumbsDown: Int? = null

    val date: Date? get() = writtenOn.parseDate("yyyy-MM-dd")
    val authorDate: String get() = "by <font color=#134FE6>$author</font> ${date?.toStringFormat("MMMM dd, yyyy") ?: "ERROR"}"
    val description: String get() = "${definition}<br><br><i>${example}</i>"
        .replace("[", "<font color=#134FE6>")
        .replace("]", "</font>")

    class IndexAPIData {
        lateinit var list: ArrayList <Word>
    }

}