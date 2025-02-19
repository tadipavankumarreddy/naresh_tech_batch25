package com.nareshtech.servicesinandroid

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat

class MyForegroundService : Service() {

    // This is a mandatory method to implement, hence returning null as we do not intend to perform RPC or IPC.
    override fun onBind(intent: Intent): IBinder {
        return null!!
    }

    lateinit var player: MediaPlayer
    lateinit var nm:NotificationManager

    //We set up the service here
    override fun onCreate(){
        super.onCreate()
        player = MediaPlayer.create(this, R.raw.my_audio)
        nm = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    }

    // This is the method where you will actually perform the task
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // logic to perform the foreground service
        if(intent?.action == "MYACTION"){
            // to stop
            nm.cancel(32)
            player.stop()
            stopSelf()
        }else{
            player.start()
            sendNotification()
        }
        return START_NOT_STICKY
    }

    private fun sendNotification() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            // Create the notification channel
            val chanel = NotificationChannel("Pavan","Service Notification",NotificationManager.IMPORTANCE_HIGH)
            nm.createNotificationChannel(chanel)
        }

        val n = NotificationCompat.Builder(this, "Pavan")
            .setContentTitle("My foreground Service is Started")
            .setContentText("Hello how are you")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .build()

        nm.notify(32, n)
    }
}