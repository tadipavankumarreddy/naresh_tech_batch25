package com.nareshtech.registrationjuc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nareshtech.registrationjuc.ui.theme.RegistrationJUCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegistrationJUCTheme {
                RegistrationScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RegistrationScreen(){

    // All variables go here
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
        .background(Color.White)
        .padding(WindowInsets.statusBars.asPaddingValues())
        .padding(WindowInsets.navigationBars.asPaddingValues())) {

        // Heading as Register me text
        Text(text="Register Me!", fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, fontSize = 30.sp,
            modifier = Modifier.fillMaxWidth().rotate(-10f).padding(10.dp))

        // Create a outlined textField to read Name
        OutlinedTextField(value = name, onValueChange = {name = it},
            label = { Text(text = "Enter your Name")},
            modifier = Modifier.fillMaxWidth())

        // Create a outlined textField to read age
        OutlinedTextField(value = age, onValueChange = {age = it},
            label = { Text(text = "Enter your Age")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth())
    }
}