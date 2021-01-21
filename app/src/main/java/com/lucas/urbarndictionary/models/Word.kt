package com.lucas.urbarndictionary.models

import com.lucas.urbarndictionary.extensions.parseDate
import com.lucas.urbarndictionary.extensions.toStringFormat
import org.json.JSONObject
import java.util.*

@Suppress("MemberVisibilityCanBePrivate")
class Word(json: JSONObject) {

    var word: String = json.getString("word")
    var definition: String = json.getString("definition")
    var example: String = json.getString("example")
    var thumbsUp: Int = json.getInt("thumbs_up")
    var thumbsDown: Int = json.getInt("thumbs_down")
    var author: String = json.getString("author")
    var date: Date = json.getString("written_on").parseDate("yyyy-MM-dd")!!
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
            return "by <font color=#134FE6>$author</font> ${date.toStringFormat("MMMM dd, yyyy")}"
        }

}