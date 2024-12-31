package com.nareshtech.f4ffactsforfun

import android.content.Context
import android.os.AsyncTask
import android.widget.ProgressBar
import android.widget.TextView
import org.json.JSONObject
import java.net.URL
import java.util.Scanner
import javax.net.ssl.HttpsURLConnection

class FetchFact(val textView:TextView, val progressBar: ProgressBar):AsyncTask<String, Void,String>() {

    override fun doInBackground(vararg p0: String?): String {
        // TODO 2.1 : Perform the long running operation
            // TODO 2.1.1 -> Establish a UrlConnection
            val link = p0.get(0)
            val url = URL(link)
            val urlConnection = url.openConnection() as HttpsURLConnection
            // TODO 2.1.2 -> Read the input stream from the url
            val inputStream = urlConnection.inputStream
            // TODO 2.1.3 -> Read the input stream as a String.
            val stringBuilder = StringBuilder()
            val s:Scanner = Scanner(inputStream)
            while(s.hasNextLine()){
                stringBuilder.append(s.nextLine())
            }
            // TODO 2.1.4 -> return the data to onPostExecute(...)
            return stringBuilder.toString()
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        // TODO 2.2: Display the text on the textview.
        progressBar.visibility = ProgressBar.INVISIBLE
        val jsonObject = JSONObject(result)
        val fact = jsonObject.getString("text")
        textView.text = fact
    }
}