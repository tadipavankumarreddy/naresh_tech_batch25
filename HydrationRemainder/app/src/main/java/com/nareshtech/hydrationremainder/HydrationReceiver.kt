package com.nareshtech.hydrationremainder

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import androidx.core.app.NotificationCompat

class HydrationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        // TODO 2: Create a notification for the users to remind them of having a glass of water.
        val notificaitonManager:NotificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val notificationChannel = NotificationChannel("hydration","Hydration Remainder",NotificationManager.IMPORTANCE_HIGH)
        notificaitonManager.createNotificationChannel(notificationChannel)

        val notification = NotificationCompat.Builder(context, "hydration")
            .setSmallIcon(R.drawable.baseline_water_drop_24)
            .setContentTitle("Hydration is needed")
            .setContentText("Drink up just 200ml of water to keep yourself hydrated and healthy!")
            .setAutoCancel(true)
            .build()

        notificaitonManager.notify(235,notification)
    }
}