package com.nareshtech.roomdatabase

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    lateinit var personDatabase: PersonDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        personDatabase = Room.databaseBuilder(this, PersonDatabase::class.java, "pavan")
            .allowMainThreadQueries()
            .build()
    }

    fun loadData(view: View) {
        val data:List<Human> = personDatabase.personDao().getAllData()
        val tv:TextView = findViewById(R.id.textViewResult)
        tv.setText("")
        for(i in data){
            tv.append("${i.person_id} ${i.person_name} ${i.person_age}\n")
        }
    }

    fun saveData(view: View) {
        val name = (findViewById<TextInputLayout>(R.id.textInputLayoutText)).editText?.text.toString()
        val age = (findViewById<TextInputLayout>(R.id.textInputLayoutInteger)).editText?.text.toString().toInt()

        // Create a person object.
        val person = Human(0,name, age)
        personDatabase.personDao().insertData(person)

       (findViewById<TextInputLayout>(R.id.textInputLayoutText)).editText?.text?.clear()
       (findViewById<TextInputLayout>(R.id.textInputLayoutInteger)).editText?.text?.clear()

        Snackbar.make(view,"Data Inserted Successfully",Snackbar.LENGTH_LONG).show()
    }
}