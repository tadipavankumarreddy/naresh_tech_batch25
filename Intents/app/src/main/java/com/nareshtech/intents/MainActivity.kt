package com.nareshtech.intents

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    // TODO: Expect some data as a return value
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == RESULT_OK) {
                    // TODO: Handle the returned data
                    val replyData = result.data?.getStringExtra("replyKey") ?: "No Reply Received"
                    Snackbar.make(this, findViewById(R.id.main), replyData, Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }

        fun giveSurprise(view: View) {
            // TODO Read the name from the textInputLayout
            val til = findViewById<TextInputLayout>(R.id.textInputLayout)
            val data = til.editText?.text.toString()

            // TODO Now this data needs to be sent to the other screen
            val i = Intent(this, SecondActivity::class.java)
            i.putExtra("name", data)
            /*startActivity(i)*/
            resultLauncher.launch(i)
        }
}