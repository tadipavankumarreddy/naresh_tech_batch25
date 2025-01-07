package com.nareshtech.weatherforecast

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.nareshtech.weatherforecast.dataclasses.Current
import com.nareshtech.weatherforecast.dataclasses.Hourly


class MainActivity : AppCompatActivity() {
    lateinit var progressbar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // tODO 1: perfrom a network request and load the data on the textview.
        val textView = findViewById<TextView>(R.id.textView)
        progressbar = findViewById(R.id.progressBar)
        progressbar.visibility = ProgressBar.VISIBLE
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.open-meteo.com/v1/forecast?latitude=17.3616&longitude=78.4747&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m&timezone=IST"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                progressbar.visibility = ProgressBar.INVISIBLE
                val gson = Gson()
                val resp = gson.fromJson(response, com.nareshtech.weatherforecast.dataclasses.Response::class.java)
                val current:Current = resp.current!!
                val time = current.time
                val date = time?.split("T")?.joinToString(" ")
                textView.text = "Time: ${date}\nTemperature: ${current.temperature2m}°C\nWind Speed: ${current.windSpeed10m} km/hr"

                // Implement recyclerview
                implementRecyclerveiw(resp.hourly!!)
            },
            {  })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }

    private fun implementRecyclerveiw(hourly: Hourly) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = ForecastAdapter(this,hourly)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }
}