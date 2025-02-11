package com.nareshtech.jokes_coroutines

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import java.net.URL
import java.util.Scanner
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {
    val fetchJokes = "https://api.chucknorris.io/jokes/random"
    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var progressBar: ProgressBar

    // tODO 1.1 Create a scope variable
    lateinit var scope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textView = findViewById<TextView>(R.id.textView)
        button = findViewById<Button>(R.id.button)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar.visibility = ProgressBar.INVISIBLE
        textView.text = ""

        scope = CoroutineScope(Dispatchers.Main)

        button.setOnClickListener {
            fetchData()
        }

    }

    private fun fetchData() {
        // TODO 1: Make use of coroutine to perform this operation.
        progressBar.visibility = ProgressBar.VISIBLE
       scope.launch {
           // Which means we are on main thread in this scope.
           val data = fetchDataAsync()
           val d = data.await()
           textView.text = d
           progressBar.visibility = ProgressBar.INVISIBLE
       }
    }

    private suspend fun fetchDataAsync(): Deferred<String> = scope.async(Dispatchers.IO) {
        val url = URL(fetchJokes)
        val connection = url.openConnection() as HttpsURLConnection
        val inputStream = connection.inputStream
        val scanner = Scanner(inputStream)
        val stringBuilder:StringBuilder = StringBuilder()
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine())
        }
        stringBuilder.toString()
    }
}