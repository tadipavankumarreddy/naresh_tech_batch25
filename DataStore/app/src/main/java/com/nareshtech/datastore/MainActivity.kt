package com.nareshtech.datastore

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.*
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    // I want to use preferences data store
    // TODO 1: Create DataStore Preferences object
    val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "settings")

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

    fun saveData(view: View) {
        val name = findViewById<TextInputLayout>(R.id.textInputLayoutText)?.editText?.text.toString()
        val age = findViewById<TextInputLayout>(R.id.textInputLayoutInteger)?.editText?.text.toString().toInt()

        // TODO 2: Create a datastore in teh coroutine's context and save the data
        CoroutineScope(Dispatchers.IO).launch{
            datastore.edit { settings ->
                settings[stringPreferencesKey("NAME")] = name
                settings[intPreferencesKey("AGE")] = age
            }
        }
    }

    fun loadData(view: View) {

        // TODO 3: Get the key and value from the preferences
        val preferences = datastore.data.map {
            preferences ->
            preferences[stringPreferencesKey("NAME")] ?: ""
        }

        val age = datastore.data.map {
                age ->
            age[intPreferencesKey("AGE")] ?: 0
        }

        CoroutineScope(Dispatchers.IO).launch {
            val name = preferences.first()
            val age = age.first()
            runOnUiThread{
                findViewById<TextView>(R.id.textViewResult).text = "$name $age"
            }
        }

    }
}