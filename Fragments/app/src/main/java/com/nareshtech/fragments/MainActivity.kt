package com.nareshtech.fragments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_vieew,CalculatorFragment())
            .commit()
        
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_vieew2,CalculatorFragment())
            .commit()
    }
}

// TODO 1: How do we create a fragment
// Right click on the app > New > select Fragment -> Choose Fragment Blank

// TODO 2: Implementing the fragment.

// TODO 3: Load the fragment on MainActivity
// In order to load a fragment on mainactivity, you need to have the following components
// 1. FragmentManager
// 2. FragmentTransaction
// 3. Fragment container to host the fragment is required.