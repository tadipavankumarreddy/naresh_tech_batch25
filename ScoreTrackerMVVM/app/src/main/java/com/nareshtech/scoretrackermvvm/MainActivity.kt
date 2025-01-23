package com.nareshtech.scoretrackermvvm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nareshtech.scoretrackermvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // TODO 1: Inorder to create ViewBinding object to access the activity_main.xml
        lateinit var binding:ActivityMainBinding
        lateinit var mainViewModel: MainViewModel

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        if(mainViewModel!=null && mainViewModel.count.value!=0){
            binding.textView.text = mainViewModel.count.value.toString()
        }

        // TODO 2: Implement the logic
        binding.button.setOnClickListener {
            mainViewModel.increment()
        }

        binding.button2.setOnClickListener {
           mainViewModel.decrement()
        }

        mainViewModel.count.observe(this,object:Observer<Int>{
            override fun onChanged(value: Int) {
                binding.textView.text = value.toString()
            }
        })
    }
}