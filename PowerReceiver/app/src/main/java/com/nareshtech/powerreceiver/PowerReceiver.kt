package com.nareshtech.powerreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.ImageView

class PowerReceiver(val imageView: ImageView) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        // TODO 1.1: Implement the broadcast receiver to show the images based on the broadcast received.
        when (intent.action) {
            Intent.ACTION_POWER_CONNECTED -> imageView.setImageResource(R.drawable.baseline_battery_charging_full_24)
            Intent.ACTION_POWER_DISCONNECTED -> imageView.setImageResource(R.drawable.baseline_battery_alert_24)
            MainActivity.CUSTOM_BROADCAST_STRING -> imageView.setImageResource(R.drawable.ic_launcher_background)
        }
    }
}