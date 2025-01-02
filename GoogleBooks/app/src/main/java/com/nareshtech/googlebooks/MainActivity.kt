package com.nareshtech.googlebooks

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject
import org.w3c.dom.Text
import java.net.URL
import java.util.Scanner
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection
import kotlin.text.StringBuilder

class MainActivity : AppCompatActivity() {
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progressBar = findViewById(R.id.progressbar)
        progressBar.visibility = ProgressBar.INVISIBLE
    }

    fun getTheVerse(view: View) {
        val spinner:Spinner = findViewById(R.id.chapters_spinner)
        val chap_num = spinner.selectedItemPosition
        val verse_number = (findViewById<TextInputLayout>(R.id.textInputLayout).editText?.text.toString()).toInt()
        if(chap_num!=0){
            progressBar.visibility = ProgressBar.VISIBLE
            val baseUrl = "https://gita-api.vercel.app/tel/verse/$chap_num/$verse_number"
            // Now Perform Networking to read the data.
            val executor = Executors.newSingleThreadExecutor()
            // To handle data (update data on the UI)
            val handler = Handler(Looper.getMainLooper())
            // execute the task using the executor
            executor.execute {
                val url = URL(baseUrl)
                val urlConnection = url.openConnection() as HttpsURLConnection
                val ip = urlConnection.inputStream
                val s = Scanner(ip)
                val sb:StringBuilder = StringBuilder()
                do{
                    sb.append(s.nextLine())
                }while(s.hasNextLine())

                // Once the network operation is done, you can post the results.
                handler.post {
                    // YOu now switched to main thread to post the results.
                    progressBar.visibility = ProgressBar.INVISIBLE
                    // JSON Parsing
                    val obj:JSONObject = JSONObject(sb.toString())
                    val verse = obj.getString("verse")
                    (findViewById<TextView>(R.id.verse)).text = verse
                    val translation = obj.getString("translation")
                    (findViewById<TextView>(R.id.translation)).text = translation
                    val purport = obj.getJSONArray("purport")
                    Log.v("MAIN", purport.length().toString())
                    (findViewById<TextView>(R.id.purport)).append(purport.get(0).toString())
                }
            }

            executor.shutdown()

        }else{
            Snackbar.make(view, "Please choose a chapter", Snackbar.LENGTH_LONG).show()
        }
    }
}