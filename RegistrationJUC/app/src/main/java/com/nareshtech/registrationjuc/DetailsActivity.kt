package com.nareshtech.registrationjuc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nareshtech.registrationjuc.ui.theme.RegistrationJUCTheme

class DetailsActivity : ComponentActivity() {

    companion object{
        const val NAME = "name"
        const val AGE = "age"
        const val LANGUAGES = "languages"
        const val GENDER = "gender"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegistrationJUCTheme {
                Column(modifier = Modifier.fillMaxSize()
                        .padding(WindowInsets.statusBars.asPaddingValues())
                        .padding(WindowInsets.navigationBars.asPaddingValues())
                        .padding(16.dp)) {
                    Text(text = intent.getStringExtra(NAME)?:"", fontWeight = FontWeight.Bold)
                    Text(text = intent.getStringExtra(AGE)?:"", fontWeight = FontWeight.Bold)
                    Text(text = intent.getStringExtra(GENDER)?:"", fontWeight = FontWeight.Bold)
                    Text(text = (intent.getStringArrayListExtra(LANGUAGES)?: arrayListOf<String>()).joinToString(","))
                }
            }
        }
    }
}
