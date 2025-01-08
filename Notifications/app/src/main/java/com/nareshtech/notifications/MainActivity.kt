package com.nareshtech.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    // TODO 1: Create a NotificaitonManager Object to Manage the Notifications.
    lateinit var notificationManager:NotificationManager

    // TODO 2: Inorder to create a NotificationChannel, we need a channel Id and the channel name.
    //  you can create as many notification channels as you need
    private val CHANNEL_ID:String = "pavan"

    private val NOTIFICATION_ID:Int = 45

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // TODO 1.1: Initialize the NotificationManager Object
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        // TODO 3: Create a NotificationChannel
        val notificationChannel = NotificationChannel(CHANNEL_ID, "My Notification Channel", IMPORTANCE_HIGH)
        notificationManager.createNotificationChannel(notificationChannel)
    }

    // To send a notification
    fun sendNotification(view: View) {
        // TODO 4: Build a Notification
        // TODO 7: Bring the notification into Action
        val intent = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_beach_access_24)
            .setContentTitle("My Notification")
            .setContentText("Sample Text to see how this content Text gets displayed.")
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        // TODO 5: Send the Notification
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    // TO cancel an existing notification
    fun cancelNotification(view: View) {
        // TODO 6: Cancel the Notification
        notificationManager.cancel(NOTIFICATION_ID)
    }
}