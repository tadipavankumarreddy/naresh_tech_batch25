package com.nareshtech.sqlite

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        databaseHelper = DatabaseHelper(this, "person",1)
    }

    fun saveData(view: View) {
        // TODO 2: get the data, map the data with coloumn names and send the data to the database helper
        val pName = findViewById<TextInputLayout>(R.id.textInputLayoutText)
        val pAge = findViewById<TextInputLayout>(R.id.textInputLayoutInteger)
        val name = pName.editText?.text.toString()
        val age = pAge.editText?.text.toString().toInt()

        // Map the values with the coloumn names
        val values:ContentValues = ContentValues()
        values.put(DatabaseHelper.COL_1, name)
        values.put(DatabaseHelper.COL_2, age)

        // Should be sent to the insert method on Database helper
        databaseHelper.insertData(values)

        Snackbar.make(view, "Data Inserted Successfully", Snackbar.LENGTH_LONG).show()

        // Reset the form
        findViewById<TextInputLayout>(R.id.textInputLayoutText).editText?.text?.clear()
        findViewById<TextInputLayout>(R.id.textInputLayoutInteger).editText?.text?.clear()
    }

    fun loadData(view: View) {
        // Read the data
        val c:Cursor = databaseHelper.getData()
        val result:TextView = findViewById(R.id.textViewResult)

        result.setText("")
        c.moveToFirst()
        do{

            val id = c.getInt(0)
            val name = c.getString(1)
            val age = c.getInt(2)
            result.append("$id $name $age\n")
        }while (c.moveToNext())
    }

    // TODO 1: Create a database using SQliteOpenHelper class.
}