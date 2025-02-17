package com.nareshtech.registrationjuc

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

    val context = LocalContext.current

    // All variables go here
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var languages = listOf("Tel","Eng","Hin","Tam")
    val selectedLanguages = remember { mutableStateListOf<String>() }

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

        // Read Gender (Male/Female)
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround) {

            Text(text="Gender")

            RadioButton(selected = gender == "Male", onClick = {gender = "Male"})
            Text(text = "Male")

            RadioButton(selected = gender == "Female", onClick = {gender = "Female"})
            Text(text = "Female")

        }

        // Let the user select multiple languages
        Text(text = "Languages known", fontWeight = FontWeight.Bold)
        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround) {

            languages.forEach { language ->
                Checkbox(checked = selectedLanguages.contains(language), onCheckedChange = {
                    if(it) selectedLanguages.add(language) else selectedLanguages.remove(language)
                })
                Text(text = language, modifier = Modifier.weight(1f))
            }
        }

        Button(onClick = {
            //create an intent to navigate to DetailsActivity
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.NAME, name)
            intent.putExtra(DetailsActivity.AGE, age)
            intent.putExtra(DetailsActivity.GENDER, gender)
            intent.putExtra(DetailsActivity.LANGUAGES, ArrayList(selectedLanguages))
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)

        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Register")
        }
    }
}