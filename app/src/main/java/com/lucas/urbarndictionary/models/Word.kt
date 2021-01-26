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
    @SerializedName("thumbs_up") var thumbsUp: Int = 0
    @SerializedName("thumbs_down") var thumbsDown: Int = 0
    lateinit var author: String
    @SerializedName("written_on") lateinit var writtenOn: String

    val date: Date?
        get() {
            return writtenOn.parseDate("yyyy-MM-dd")
        }
    val description: String
        get() {
            var htmlString = "${definition}<br><br><i>${example}</i>"
            htmlString = htmlString
                .replace("[", "<font color=#134FE6>")
                .replace("]", "</font>")

            return htmlString
        }
    val authorDate: String
        get() {
            return "by <font color=#134FE6>$author</font> ${date?.toStringFormat("MMMM dd, yyyy") ?: "ERROR"}"
        }

    class IndexAPIData {
        lateinit var list: ArrayList <Word>
    }

}