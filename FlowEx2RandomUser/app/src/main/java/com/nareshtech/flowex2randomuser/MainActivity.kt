package com.nareshtech.flowex2randomuser

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL
import java.util.Scanner
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var progressBar: ProgressBar
    lateinit var scope: CoroutineScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textView = findViewById(R.id.textview)
        button = findViewById(R.id.button)
        progressBar = findViewById(R.id.progressBar)

        progressBar.visibility = View.INVISIBLE
        textView.text = "Click to fetch user"

        scope = CoroutineScope(Dispatchers.Main)

        button.setOnClickListener {
            fetchUser()
        }
    }

    // TODO 3: Populate the results on the UI
    private fun fetchUser() {
       progressBar.visibility = View.VISIBLE

       scope.launch {
           fetchUserFlow()
               .catch { e -> textView.text = "Error: ${e.message}" }
               .collect{ user ->
               textView.text = "Name: ${user.name} \nEmail: ${user.email} \nPhone: ${user.phone}"
               progressBar.visibility = View.INVISIBLE
           }
       }
    }

    // TODO 2: Create a function that uses Flow to fetch data from API
    private fun fetchUserFlow():Flow<User> = flow {
        val url = URL("https://randomuser.me/api/")
        val connection = url.openConnection() as HttpsURLConnection
        val inputStream = connection.inputStream
        val scanner = Scanner(inputStream)
        val stringBuilder = StringBuilder()

        while(scanner.hasNextLine()){
            stringBuilder.append(scanner.nextLine())
        }

        val jsonResponse = JSONObject(stringBuilder.toString())
        val results = jsonResponse.getJSONArray("results").getJSONObject(0)
        val nameObj = results.getJSONObject("name")
        val user = User(
            name = "${nameObj.getString("title")} ${nameObj.getString("first")} ${nameObj.getString("last")}",
            email = results.getString("email"),
            phone = results.getString("phone")
        )

        emit(user)

    }.flowOn(Dispatchers.IO)

    // TODO 4: Destroy the flow and other coroutines
    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}