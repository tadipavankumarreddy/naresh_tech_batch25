package com.nareshtech.favoriteactors

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    data class Actors(
        val image: Int,
        val name: String,
        val yob: Int,
        val moviesCount: Int,
        val description: String,
        val wikipediaLink: String
    ):Serializable

    var actors:MutableList<Actors> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO 1: Preparing data
        val actors = mutableListOf<Actors>(
            Actors(R.drawable.alluarjun, "Allu Arjun", 1982, 20, "Stylish Star Allu Arjun is an Indian actor known for his energetic dance moves and charismatic screen presence.", "https://en.wikipedia.org/wiki/Allu_Arjun"),
            Actors(R.drawable.chiranjeevi, "Chiranjeevi", 1955, 150, "Megastar Chiranjeevi is a legendary Indian actor, producer, and politician, known for his powerful performances and philanthropic work.", "https://en.wikipedia.org/wiki/Chiranjeevi"),
            Actors(R.drawable.amitabh, "Amitabh Bachchan", 1942, 200, "Amitabh Bachchan, often referred to as 'Big B', is a legendary Indian actor, known for his iconic voice, intense performances, and significant contributions to Indian cinema.", "https://en.wikipedia.org/wiki/Amitabh_Bachchan"),
            Actors(R.drawable.kamal, "Kamal Hassan", 1954, 230, "Kamal Haasan is a versatile Indian actor, director, screenwriter, and producer, known for his experimental and challenging roles.", "https://en.wikipedia.org/wiki/Kamal_Haasan"),
            Actors(R.drawable.rana, "Rana Daggubati", 1984, 30, "Rana Daggubati is an Indian actor known for his portrayal of powerful and intense characters.", "https://en.wikipedia.org/wiki/Rana_Daggubati"),
            Actors(R.drawable.prabhas, "Prabhas", 1979, 25, "Prabhas is an Indian actor known for his role as Baahubali, a historical fantasy film that gained international recognition.", "https://en.wikipedia.org/wiki/Prabhas"),
            Actors(R.drawable.ranbir, "Ranbir Kapoor", 1982, 25, "Ranbir Kapoor is an Indian actor known for his versatility and natural acting style.", "https://en.wikipedia.org/wiki/Ranbir_Kapoor"),
            Actors(R.drawable.salman, "Salman Khan", 1965, 100, "Salman Khan is a popular Indian actor, producer, and television personality, known for his action and romantic roles.", "https://en.wikipedia.org/wiki/Salman_Khan"),
            Actors(R.drawable.charan, "Ram Charan", 1985, 20, "Ram Charan is an Indian actor known for his stylish action sequences and charming personality.", "https://en.wikipedia.org/wiki/Ram_Charan"),
            Actors(R.drawable.vijay, "Vijay", 1975, 65, "Vijay is a popular South Indian actor known for his mass appeal and energetic dance performances.", "https://en.wikipedia.org/wiki/Vijay_(actor)")
        )

        // TODO 5: set up the adapter on the recyclerview
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = ActorAdapter(this, actors)

        // TODO 6: Set up the LayoutManager on the recyclerview
        /*recyclerView.layoutManager = LinearLayoutManager(this)*/
        /*recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)*/
        recyclerView.layoutManager = GridLayoutManager(this,2)
    }
}