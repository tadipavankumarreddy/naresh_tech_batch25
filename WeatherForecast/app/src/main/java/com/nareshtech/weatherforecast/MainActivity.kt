package com.nareshtech.weatherforecast

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
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
        val textView = findViewById<TextView>(R.id.weather_data_tv)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.open-meteo.com/v1/forecast?latitude=17.3616&longitude=78.4747&current=temperature_2m,wind_speed_10m&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m&timezone=IST"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(Request.Method.GET, url,
            { response ->
                val obj = JSONObject(response)
                val current = obj.getJSONObject("current")
                val time = current.getString("time")
                val temp = current.getDouble("temperature_2m")
                val wind = current.getDouble("wind_speed_10m")
                val actual_time = time.split("T").joinToString(" ")
                textView.text = "At ${actual_time}\nTemperature:${temp}°C\nWind:${wind} Km/hr"
            },
            { textView.text = "That didn't work!" })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}