package com.nareshtech.scoretracker

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var count = 0
    lateinit var tv:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tv = findViewById(R.id.score)

        if(savedInstanceState!=null && savedInstanceState.containsKey("COUNT")){
            count = savedInstanceState.getInt("COUNT")
            tv.text = count.toString()
        }
    }

    fun incrementValue(view: View){
      // Increment Logic here
        count++
        tv.text = count.toString()
    }

    fun decrementValue(view: View) {
        // Decrement Logic here
        count--
        tv.text = count.toString()
    }

    // This method gets invoked before your activity gets destroyed.
    // Here we can save values that we want to use in the next instance of the activity in the bundle object.
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("COUNT", count)
    }
}