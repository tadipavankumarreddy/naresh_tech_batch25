package com.nareshtech.commonintents

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

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
    }

    fun openBrowser(view: View) {
        // Reading the url
        val url = findViewById<TextInputLayout>(R.id.url_input).editText?.text.toString()
        // Opening the browser
        val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://$url"))
        startActivity(i)
    }

    fun openAddress(view: View) {
        // Reading the url
        val url = findViewById<TextInputLayout>(R.id.address_input).editText?.text.toString()
        // Opening the browser
        val i = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=$url"))
        startActivity(i)
    }

    // Alert Dialogs in Android
    fun showAlert(view: View) {
        val dialog = AlertDialog.Builder(this)
            .setIcon(R.drawable.baseline_back_hand_24)
            .setTitle("You Clicked the button")
            .setMessage("Nice to see you working on Alert Dialogs")
            .setPositiveButton("Yes", DialogInterface.OnClickListener(){
                dialog, which ->
                Toast.makeText(this,"Hello", Toast.LENGTH_LONG).show()
            })
            .setNegativeButton("No", null)
            .setNeutralButton("Cancel", null)

        dialog.show()
    }



}