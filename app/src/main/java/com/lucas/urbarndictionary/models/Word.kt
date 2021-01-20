package com.lucas.urbarndictionary.models

import org.json.JSONObject

class Word(json: JSONObject) {

    var title: String = json.getString("title")
    var detail: String = json.getString("detail")

}