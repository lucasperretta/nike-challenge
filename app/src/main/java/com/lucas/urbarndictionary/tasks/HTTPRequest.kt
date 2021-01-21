@file:Suppress("MemberVisibilityCanBePrivate", "SpellCheckingInspection")

package com.lucas.urbarndictionary.tasks

import android.os.AsyncTask
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class HTTPRequest(private val url: String, val callback: (JSONObject?) -> Unit) : AsyncTask<Void, Int, JSONObject?>() {

    companion object {

        fun make(url: String, callback: (JSONObject?) -> Unit): HTTPRequest {
            val request = HTTPRequest(url, callback)
            request.execute()

            return request
        }

    }

    override fun doInBackground(vararg p0: Void?): JSONObject? {
        val client: HttpURLConnection = URL(url).openConnection() as HttpURLConnection
        client.requestMethod = "GET"
        client.setRequestProperty(
            "X-RapidAPI-Key",
            "kRLTeO9IyCmshsdmTBuOQmHULNrkp1gzQHAjsnzB8w9qhonso1"
        )
        client.setRequestProperty(
            "X-RapidAPI-Host",
            "mashape-community-urban-dictionary.p.rapidapi.com"
        )
        val builder = StringBuilder()

        if (client.responseCode != 200) {
            return null
        }

        val reader = BufferedReader(InputStreamReader(client.inputStream, "utf-8"))
        var responseLine: String?
        while (reader.readLine().also { responseLine = it } != null) {
            builder.append(responseLine!!.trim { it <= ' ' })
        }
        reader.close()

        return JSONObject(builder.toString())
    }

    override fun onPostExecute(result: JSONObject?) {
        super.onPostExecute(result)
        callback(result)
    }

}