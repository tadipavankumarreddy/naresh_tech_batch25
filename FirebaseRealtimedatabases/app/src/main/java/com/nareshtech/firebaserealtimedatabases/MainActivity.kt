package com.nareshtech.firebaserealtimedatabases

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    // TODO 2: ctnd
    data class HumanBeing(val name:String, val age:Int){
        constructor():this("",0)
    }

    lateinit var tilName:TextInputLayout
    lateinit var tilAge:TextInputLayout
    lateinit var progressBar: ProgressBar
    lateinit var textView: TextView

    // TODO 1: Create a firebase realtime database instance
    lateinit var firebaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tilName=findViewById(R.id.textInputLayoutText)
        tilAge=findViewById(R.id.textInputLayoutInteger)
        progressBar=findViewById(R.id.progressBar)
        textView=findViewById(R.id.textViewResult)
        progressBar.visibility=View.INVISIBLE

        // TODO 1 (cntd): Initialize the firebase realtime database instance
        firebaseReference= FirebaseDatabase.getInstance().getReference()

        // TODO 2: Create structure for the data that you store using dataclasses in kotlin
    }

    fun loadData(view: View) {
        progressBar.visibility = ProgressBar.VISIBLE
        val getListner:ValueEventListener = object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val stringBuilder:StringBuilder = StringBuilder()
                for(s in snapshot.children){
                    val humanBeing:HumanBeing = s.getValue(HumanBeing::class.java) as HumanBeing
                    stringBuilder.append("Name: ${humanBeing.name} Age: ${humanBeing.age}\n\n")
                }
                textView.text = stringBuilder.toString()
                progressBar.visibility = ProgressBar.INVISIBLE
                Snackbar.make(view, "Data fetched Succcessfully", Snackbar.LENGTH_LONG).show()
            }

            override fun onCancelled(error: DatabaseError) {

            }
        }

        // Invoke the get listener
        firebaseReference.addValueEventListener(getListner)
    }

    fun saveData(view: View) {
        progressBar.visibility=View.VISIBLE
        val name = tilName.editText?.text.toString()
        val age = tilAge.editText?.text.toString().toInt()

        val humanBeing = HumanBeing(name, age)

        firebaseReference.push().setValue(humanBeing).addOnSuccessListener {
            progressBar.visibility = ProgressBar.INVISIBLE
            Snackbar.make(view, "Data sent Succcessfully", Snackbar.LENGTH_LONG).show()
        }.addOnFailureListener {
            progressBar.visibility = ProgressBar.INVISIBLE
            Snackbar.make(view, "Data is not sent", Snackbar.LENGTH_LONG).show()
        }


        tilName.editText?.text?.clear()
        tilAge.editText?.text?.clear()
    }
}