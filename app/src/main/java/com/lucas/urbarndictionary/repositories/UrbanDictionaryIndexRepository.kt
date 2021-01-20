package com.lucas.urbarndictionary.repositories

import android.util.Log
import com.lucas.urbarndictionary.models.Word
import com.lucas.urbarndictionary.tasks.HTTPRequest
import org.json.JSONObject

object UrbanDictionaryIndexRepository {

    fun getData(searchTerm: String, callback: (ArrayList<Word>?) -> Unit) {
        HTTPRequest.make("https://mashape-community-urban-dictionary.p.rapidapi.com/define?term=$searchTerm") {
            callback(if (it != null) parseJsonResponse(it) else null)
        }
    }

    private fun parseJsonResponse(data: JSONObject): ArrayList<Word> {
        val list = data.getJSONArray("list")
        val result = ArrayList<Word>()
        for (index in 0 until list.length()) {
            result.add(Word(list.getJSONObject(index)))
        }

        return result
    }

}