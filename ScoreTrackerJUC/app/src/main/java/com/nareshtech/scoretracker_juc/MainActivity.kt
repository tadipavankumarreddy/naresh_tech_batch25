package com.nareshtech.scoretracker_juc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nareshtech.scoretracker_juc.ui.theme.ScoreTrackerJUCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScoreTrackerJUCTheme {
                val countViewModel:CountViewModel = ViewModelProvider(this)
                    .get(CountViewModel::class.java)
                GenerateScreen(countViewModel)
            }
        }
    }
}

class CountViewModel:ViewModel(){
    var count by mutableStateOf(0)

    fun increment(){
        count++
    }

    fun decrement(){
        count--
    }
}

/*@Preview(showBackground = true)*/
@Composable
fun GenerateScreen(countViewModel: CountViewModel){
    val isDarkTheme = isSystemInDarkTheme()
    var count = countViewModel.count
    val textBackgroundColor = if (isDarkTheme) Color.Black else Color.Yellow
    val textColor = if (isDarkTheme) Color.White else Color.Black

    val paddingModifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())
        .padding(WindowInsets.navigationBars.asPaddingValues())
        .padding(20.dp)

    Column(modifier = Modifier.fillMaxSize()
        .then(paddingModifier))
    {
        Button(onClick = {
            countViewModel.increment()
        },
            modifier = Modifier.fillMaxWidth().weight(1f))
        {
            Text(text = "+")
        }

        Box(modifier = Modifier.fillMaxWidth()
            .background(textBackgroundColor)
            .weight(8f).border(BorderStroke(5.dp,Color.White)),
            contentAlignment = Alignment.Center)
        {
            Text(
                text = count.toString(),
                color = textColor,
                fontSize = 100.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Button(onClick = {
            countViewModel.decrement()
        },
            modifier = Modifier.fillMaxWidth().weight(1f))
        {
            Text(text = "-")
        }
    }
}
