package com.nareshtech.hydrationremainder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // TODO 1: TO trigger an alarm or to cancel it, we need an AlarmManager and a PendingIntent
    lateinit var alaramManager:AlarmManager
    lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        alaramManager = getSystemService(ALARM_SERVICE) as AlarmManager
        // In order to show the notification every fifteen minutes to the users, we need a broadcast receiver -  The pending intent has to be created with getBroadcast(...)
        // TODO 3: Create a Pending Intent that should work as soon as your alarm is triggered.
        pendingIntent = PendingIntent.getBroadcast(this, 12, Intent(this,HydrationReceiver::class.java), PendingIntent.FLAG_IMMUTABLE)
    }

    fun scheduleTask(view: View) {
        // TODO 4: set up the alarm
        val alarmType = AlarmManager.ELAPSED_REALTIME
        val firstTrigger:Long = SystemClock.elapsedRealtime() // This is the time calculated since the system boot time in milli seconds.
        val intervalTime:Long = AlarmManager.INTERVAL_FIFTEEN_MINUTES
        alaramManager.setInexactRepeating(alarmType,firstTrigger,intervalTime,pendingIntent)
    }

    fun cancelAlarm(view: View) {
        // TODO 5: Cancel the alarm
        alaramManager.cancel(pendingIntent)
    }
}