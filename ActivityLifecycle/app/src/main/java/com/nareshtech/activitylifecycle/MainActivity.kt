package com.nareshtech.activitylifecycle

import android.os.Bundle
import android.util.Log
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
        Log.v("MAIN","onCreate()")
    }

    override fun onStart() {
        super.onStart()
        Log.v("MAIN","onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.v("MAIN","onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.v("MAIN","onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.v("MAIN","onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("MAIN","onDestroy()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v("MAIN","onRestart()")
    }
}