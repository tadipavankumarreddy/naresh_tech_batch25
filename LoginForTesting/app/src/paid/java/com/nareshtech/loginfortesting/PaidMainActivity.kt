package com.nareshtech.loginfortesting

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class PaidMainActivity : AppCompatActivity() {

    lateinit var userName:TextInputLayout
    lateinit var password:TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userName=findViewById(R.id.textInputLayout)
        password=findViewById(R.id.textInputLayout2)
    }

    fun login(view: View) {
        val name=userName.editText?.text.toString()
        val psw=password.editText?.text.toString()
        if(isLoginValid(name,psw)){
            Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Login Failed",Toast.LENGTH_SHORT).show()
        }
    }

    fun isLoginValid(name:String,psw:String):Boolean{
        return name == "admin" && psw == "password123"
    }
}