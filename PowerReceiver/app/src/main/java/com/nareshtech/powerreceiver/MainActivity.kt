package com.nareshtech.powerreceiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    lateinit var imageView:ImageView
    companion object {
        val CUSTOM_BROADCAST_STRING:String = "com.nareshtech.powerreceiver.CUSTOM_BROADCAST"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // TODO 1: Create BroadcastReceiver that receives the system broadcasts of our interest
            // TODO 1.1: Implement the broadcast receiver to show the images based on the broadcast received.
        imageView = findViewById(R.id.imageView)
        // TODO 2: Register the BroadcastReceiver (Dynamically)
        val receiver = PowerReceiver(imageView)

        val intentfilter = IntentFilter()
        intentfilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentfilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        intentfilter.addAction(CUSTOM_BROADCAST_STRING)
        registerReceiver(receiver, intentfilter, RECEIVER_NOT_EXPORTED)
    }

    override fun onDestroy() {
        super.onDestroy()
        // TODO 3: Unregister the BroadcastReceiver (Dynamically)
        val receiver = PowerReceiver(imageView)
        unregisterReceiver(receiver)
    }

    fun sendBroadcast(view: View) {
        // TODO 4: Send a broadcast to the system
        val intent = Intent(CUSTOM_BROADCAST_STRING)
        sendBroadcast(intent)
    }
}