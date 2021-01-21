package com.lucas.urbarndictionary.models

import org.json.JSONObject

@Suppress("MemberVisibilityCanBePrivate")
class Word(json: JSONObject) {

    var word: String = json.getString("word")
    var definition: String = json.getString("definition")
    var example: String = json.getString("example")
    var thumbsUp: Int = json.getInt("thumbs_up")
    var thumbsDown: Int = json.getInt("thumbs_down")

    val description: String
        get() {
            var htmlString = "${definition}<br><br><i>${example}</i>"
            htmlString = htmlString
                .replace("[", "<font color=#134FE6>")
                .replace("]", "</font>")

            return htmlString
        }

}