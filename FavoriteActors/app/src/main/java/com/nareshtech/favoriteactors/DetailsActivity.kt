package com.nareshtech.favoriteactors

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val actor:MainActivity.Actors = intent.getSerializableExtra("DATA") as MainActivity.Actors
        findViewById<ImageView>(R.id.actorImage).setImageResource(actor.image)
        findViewById<TextView>(R.id.actorName).text = actor.name
        findViewById<TextView>(R.id.actorYearOfBirth).text = "Year of Birth: ${actor.yob}"
        findViewById<TextView>(R.id.actorMoviesCount).text = "Movies: ${actor.moviesCount}"
        findViewById<TextView>(R.id.actorDescription).text = "${actor.description}\nLearn More by this link ${actor.wikipediaLink}"
    }
}