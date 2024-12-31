package com.nareshtech.f4ffactsforfun

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var progressBar:ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = ProgressBar.INVISIBLE
    }

    fun getFact(view: View) {
        // We are supposed to perform AsyncTask to fetch the data asynchronously.
        // TODO 1: Add INTERNET Permission to the manifest file - to use internet, we need to add this in manifest file.
        // TODO 2: Create an AsyncTask Subclass and implement doInBackground(...) method to do
    //      the background task (Fetching and reading the data from the url)
        progressBar.visibility = ProgressBar.VISIBLE
        FetchFact(findViewById<TextView>(R.id.fact_tv), progressBar)
            .execute("https://uselessfacts.jsph.pl/api/v2/facts/random")
    }
}