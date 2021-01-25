package com.lucas.urbarndictionary.repositories

import com.lucas.urbarndictionary.models.Word
import com.lucas.urbarndictionary.tasks.HTTPRequest
import com.lucas.urbarndictionary.viewmodels.IndexActivityViewModel
import org.json.JSONObject

object IndexRepository {

    private var httpRequest: HTTPRequest? = null
    private var results: ArrayList<Word>? = null

    fun getData(searchTerm: String, filter: ThumbsFilter, callback: (ArrayList<Word>?) -> Unit) {
        httpRequest?.cancel(true)
        httpRequest = HTTPRequest.make("https://mashape-community-urban-dictionary.p.rapidapi.com/define?term=$searchTerm") {
            callback(if (it != null) parseJsonResponse(it, filter) else null)
        }
    }

    private fun parseJsonResponse(data: JSONObject, filter: ThumbsFilter): ArrayList<Word>? {
        val list = data.getJSONArray("list")
        results = ArrayList()
        results?.let { results ->
            for (index in 0 until list.length()) {
                results.add(Word(list.getJSONObject(index)))
            }
            filter.method(results)
        }

        return results
    }

    enum class ThumbsFilter(val method: (ArrayList<Word>) -> Unit) {
        Up({ list -> list.sortByDescending { it.thumbsUp } }),
        Down({ list -> list.sortByDescending { it.thumbsDown } }),
    }

}