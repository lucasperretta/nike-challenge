package com.lucas.urbarndictionary.repositories

import android.content.Context
import com.chocolate.requests.Request
import com.loopj.android.http.RequestHandle
import com.lucas.urbarndictionary.R
import com.lucas.urbarndictionary.kotlin.IndexApiCallResult
import com.lucas.urbarndictionary.models.Word

object IndexRepository {

    private var lastRequest: RequestHandle? = null

    fun getData(context:Context, searchTerm: String, filter: ThumbsFilter, callback: (IndexApiCallResult) -> Unit) {
        lastRequest?.cancel(true)
        lastRequest = Request.jsonObject(context, "Index", Word.IndexAPIData::class.java)
                .to("https://mashape-community-urban-dictionary.p.rapidapi.com/define")
                .body("term", searchTerm)
                .addHeader("X-RapidAPI-Key", context.resources.getString(R.string.PROJECT_API_KEY))
                .addHeader("X-RapidAPI-Host", context.resources.getString(R.string.PROJECT_API_HOST))
                .start { response ->
                    if (response.failed()) {
                        callback(Result.failure(response.error))

                        return@start
                    }
                    filter.method(response.value.list)
                    callback(Result.success(response.value))
                }
    }

    enum class ThumbsFilter(val method: (ArrayList<Word>) -> Unit) {
        Up({ list -> list.sortByDescending { it.thumbsUp } }),
        Down({ list -> list.sortByDescending { it.thumbsDown } }),
    }

}