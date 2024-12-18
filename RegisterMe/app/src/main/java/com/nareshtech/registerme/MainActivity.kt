package com.nareshtech.registerme

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    lateinit var userName:TextInputLayout
    lateinit var userAge:TextInputLayout
    lateinit var genderSelection:RadioGroup
    lateinit var englishCB:CheckBox
    lateinit var hindiCB:CheckBox
    lateinit var teluguCB:CheckBox
    lateinit var spinner:Spinner
    lateinit var switch:SwitchCompat
    lateinit var results: TextView
    lateinit var submit:Button
    var g = "Male"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUIComponents()

        genderSelection.setOnCheckedChangeListener { radioGroup, i ->
            g = if(i == R.id.male) "Male" else "Female"
        }

        // Logic to read data
        submit.setOnClickListener { v->
            val name = userName.editText?.text.toString()
            val age = userAge.editText?.text.toString()
            // validating if the user has entered the right characters for name.
            // we shall also see that there are no special symbols in the name input
            if(name.matches("[a-zA-Z ]+".toRegex())) {
                Snackbar.make(v, "Name is valid", Snackbar.LENGTH_SHORT).show()
                var languages = mutableListOf<String>()
                if(englishCB.isChecked) languages.add("English")
                if(hindiCB.isChecked) languages.add("Hindi")
                if(teluguCB.isChecked) languages.add("Telugu")
                //get the item selected from the spinner
                val country = spinner.selectedItem.toString()
                // Read the availability from the switch
                val available = switch.isChecked
                results.text = " Name: $name \n Age: $age \n Gender: $g \n Languages: ${languages.joinToString(", ")} \n Country: $country \n Available: $available"
            }else{
                userName.editText?.setError("Please enter a valid name!")
            }
            /*// read gender selected
            val g = if(genderSelection.checkedRadioButtonId == R.id.male) "Male" else "Female"

            Snackbar.make(v,g,Snackbar.LENGTH_SHORT).show()*/
        }

        // Once the spinner item is selected, how do we show it on the snackbar notification.
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Snackbar.make(view!!, spinner.selectedItem.toString(), Snackbar.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle nothing selected (optional)
            }
        }
    }

    private fun initUIComponents() {
        userName = findViewById(R.id.textInputName)
        userAge = findViewById(R.id.textInputAge)
        genderSelection = findViewById(R.id.gender_selection)
        englishCB = findViewById(R.id.english_cb)
        hindiCB = findViewById(R.id.hindi_cb)
        teluguCB = findViewById(R.id.telugu_cb)
        spinner = findViewById(R.id.spinner)
        results = findViewById(R.id.results)
        switch = findViewById(R.id.switch1)
        submit = findViewById(R.id.button4)

    }
}