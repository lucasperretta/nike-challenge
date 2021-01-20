package com.lucas.urbarndictionary.repositories

import com.lucas.urbarndictionary.models.Word
import org.json.JSONObject

object UrbanDictionaryIndexRepository {

    fun getData(callback: (ArrayList<Word>) -> Unit) {
        callback(arrayListOf(Word(JSONObject("{\"title\": \"Test\", \"detail\": \"Detail Test\"}"))))
    }

}