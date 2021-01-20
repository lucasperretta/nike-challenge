package com.lucas.urbarndictionary.models

import org.json.JSONObject

class Word(json: JSONObject) {

    var word: String = json.getString("word")
    var definition: String = json.getString("definition")
    var example: String = json.getString("example")

}