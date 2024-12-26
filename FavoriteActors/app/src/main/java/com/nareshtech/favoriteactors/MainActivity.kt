package com.nareshtech.favoriteactors

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    data class Actors(val image:Int, val name:String, val yob:Int)
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
        actors.add(Actors(R.drawable.alluarjun,"Allu Arjun", 1982))
        actors.add(Actors(R.drawable.chiranjeevi,"Chiranjeevi", 1955))
        actors.add(Actors(R.drawable.amitabh,"Amitabh Bachchan", 1942))
        actors.add(Actors(R.drawable.kamal,"Kamal Hassan", 1954))
        actors.add(Actors(R.drawable.rana,"Rana Daggubati", 1984))
        actors.add(Actors(R.drawable.prabhas,"Prabhas", 1979))
        actors.add(Actors(R.drawable.ranbir,"Ranbir Kapoor", 1982))
        actors.add(Actors(R.drawable.salman,"Salman Khan", 1965))
        actors.add(Actors(R.drawable.charan,"Ram Charan", 1985))
        actors.add(Actors(R.drawable.vijay,"Vijay", 1975))
    }
}