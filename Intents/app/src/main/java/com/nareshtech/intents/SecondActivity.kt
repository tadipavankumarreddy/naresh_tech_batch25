package com.nareshtech.intents

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // TODO: Receive data from the intent object
        val d = intent.getStringExtra("name")
        val tv = findViewById<TextView>(R.id.textView)
        tv.text = d
    }

    fun giveReply(view: View) {
        val replyIntent = Intent()
        replyIntent.putExtra("replyKey", "Surprise Received")
        setResult(RESULT_OK, replyIntent)
        finish()
    }
}