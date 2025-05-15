package com.nareshtech.shoemart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModelProvider
import com.nareshtech.shoemart.data.ShoeViewModel
import com.nareshtech.shoemart.networking.isInternetAvailable
import com.nareshtech.shoemart.screens.ShoeApp
import com.nareshtech.shoemart.ui.theme.ShoeMartTheme

class MainActivity : ComponentActivity() {
    lateinit var viewModel: ShoeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoeMartTheme {
                viewModel = ViewModelProvider(this)[ShoeViewModel::class.java]
                val showDialog = remember { mutableStateOf(false) }
                if(!isInternetAvailable(applicationContext)){
                    showDialog.value = true
                }
                if(!showDialog.value){
                    ShoeApp(viewModel)
                }else{
                    AlertDialog(
                        onDismissRequest = { /* do nothing or retry */ },
                        confirmButton = {
                            TextButton(onClick = { showDialog.value = false}) {
                                Text("OK")
                            }
                        },
                        title = { Text("No Internet") },
                        text = { Text("Please check your internet connection and try again.") }
                    )
                }

            }
        }
    }
}


