package com.nareshtech.contentresolver

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.coroutines.Continuation

class MainActivity : AppCompatActivity() {
    val CONTENT_URI = "content://com.nareshtech.sqlite.CONTENT_URI"
    lateinit var textView: TextView
    lateinit var uri:Uri

    companion object{
        val COL_0 = "person_id"
        val COL_1 = "person_name"
        val COL_2 = "person_age"
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

        textView = findViewById(R.id.textView)
        uri = Uri.parse(CONTENT_URI)
    }

    fun getData(view: View) {
        // You want to read the data from the SQLite app that has Content Provider
        val c = contentResolver.query(uri, null, null, null, null)
        textView.setText("")
        c?.moveToFirst()
        do{

            val id = c?.getInt(0)
            val name = c?.getString(1)
            val age = c?.getInt(2)
            textView.append("$id $name $age\n")
        }while (c?.moveToNext() == true)
    }

    fun addRow(view: View) {
        val values:ContentValues = ContentValues()
        values.put(COL_1, "Dummy Data")
        values.put(COL_2, 2)
        contentResolver.insert(uri, values)
    }
}