package com.nareshtech.sharedpreferences

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity(), OnSharedPreferenceChangeListener{

    // TODO 1: Creating shared Preferences
    lateinit var sharedpreferences:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedpreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        /**
         * MyPrefs -> Name of the preferences file
         * MODE_PRIVATE -> The file is private to the application
         * */
        sharedpreferences.registerOnSharedPreferenceChangeListener(this)
    }

    fun saveData(view: View) {
        // TODO 2: Save Data using Editor Interface on SharedPreferences
        // Read from user
        val name = (findViewById<TextInputLayout>(R.id.textInputLayoutText)).editText?.text.toString()
        val age = (findViewById<TextInputLayout>(R.id.textInputLayoutInteger)).editText?.text.toString().toInt()
        val editor = sharedpreferences.edit()
        editor.putString("NAME",name)
        editor.putInt("AGE",age)
        editor.apply()  // or editor.commit()
        /**
         * to clear data
         * editor.clear()
         * editor.apply()
         * */
        Snackbar.make(view, "Successful", Snackbar.LENGTH_LONG).show()
        (findViewById<TextInputLayout>(R.id.textInputLayoutText)).editText?.text?.clear()
        (findViewById<TextInputLayout>(R.id.textInputLayoutInteger)).editText?.text?.clear()
    }

    fun loadData(view: View) {
        val name = sharedpreferences.getString("NAME","Default")
        val age = sharedpreferences.getInt("AGE",0)
        (findViewById<TextView>(R.id.textViewResult)).text = "${name} ${age}"
    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences?, p1: String?) {
        val name = sharedpreferences.getString("NAME","Default")
        val age = sharedpreferences.getInt("AGE",0)
        (findViewById<TextView>(R.id.textViewResult)).text = "${name} ${age}"
    }
}