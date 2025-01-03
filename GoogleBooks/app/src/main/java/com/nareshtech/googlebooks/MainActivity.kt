package com.nareshtech.googlebooks

import android.content.Context
import android.os.Build
import android.provider.Settings
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONObject
import java.net.URL
import java.util.Scanner
import java.util.concurrent.Executors
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {
    lateinit var progressBar: ProgressBar
    lateinit var scrollview: ScrollView
    lateinit var imageButton: ImageButton
    lateinit var mediaPlayer: MediaPlayer
    var audio_link = ""

    // Play Audio using this function
    private fun playAudio(url: String) {
        imageButton.setImageResource(R.drawable.baseline_stop_circle_24)
        imageButton.isClickable = false
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(url)
        mediaPlayer.prepare()
        mediaPlayer.start()

        mediaPlayer.setOnCompletionListener {
            mediaPlayer.reset()
            imageButton.setImageResource(R.drawable.baseline_play_circle_24)
            imageButton.isClickable = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        progressBar = findViewById(R.id.progressbar)
        progressBar.visibility = ProgressBar.INVISIBLE

        scrollview = findViewById(R.id.scrollview)
        scrollview.visibility = ScrollView.INVISIBLE

        imageButton = findViewById(R.id.imageButton)

        imageButton.setOnClickListener { v ->
            if (audio_link != "") {
                playAudio(audio_link)
            }
        }

    }

    fun checkInternetConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false

        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    fun getTheVerse(view: View) {
        val spinner: Spinner = findViewById(R.id.chapters_spinner)
        val chap_num = spinner.selectedItemPosition
        val verse_number =
            (findViewById<TextInputLayout>(R.id.textInputLayout).editText?.text.toString()).toInt()
        if(!checkInternetConnection(this)){
            Snackbar.make(view,"No Internet Connection ${getMobileName()}",Snackbar.LENGTH_LONG).show()
            return
        }
        if (chap_num != 0) {
            progressBar.visibility = ProgressBar.VISIBLE
            val baseUrl = "https://gita-api.vercel.app/tel/verse/$chap_num/$verse_number"
            // Now Perform Networking to read the data.
            val executor = Executors.newSingleThreadExecutor()
            // To handle data (update data on the UI)
            val handler = Handler(Looper.getMainLooper())
            // execute the task using the executor
            executor.execute {
                // TODO: Check if internet connection is available and call the doNetworking method only if there is internet

                val sb = doNetworking(baseUrl)

                handler.post {
                    handleUI(sb)
                }
            }

            executor.shutdown()

        } else {
            Snackbar.make(view, "Please choose a chapter", Snackbar.LENGTH_LONG).show()
        }
    }

    private fun doNetworking(baseUrl: String): java.lang.StringBuilder {
        val url = URL(baseUrl)
        val urlConnection = url.openConnection() as HttpsURLConnection
        val ip = urlConnection.inputStream
        val s = Scanner(ip)
        val sb: StringBuilder = StringBuilder()
        do {
            sb.append(s.nextLine())
        } while (s.hasNextLine())
        urlConnection.disconnect()
        return sb
    }

    private fun handleUI(sb: java.lang.StringBuilder) {
        // YOu now switched to main thread to post the results.
        progressBar.visibility = ProgressBar.INVISIBLE
        // JSON Parsing
        // handle the error
        val obj: JSONObject = JSONObject(sb.toString())
        val error:Boolean = obj.has("error")
        if(error){
            Snackbar.make(findViewById(R.id.main),"Verse not found, Try Again",Snackbar.LENGTH_LONG).show()
            return
        }
        scrollview.visibility = ScrollView.VISIBLE
        val verse = obj.getString("verse")
        (findViewById<TextView>(R.id.verse)).text = verse
        val translation = obj.getString("translation")
        (findViewById<TextView>(R.id.translation)).text = translation
        val purport = obj.getJSONArray("purport")
        val purportText = StringBuilder()
        for (i in 0 until purport.length()) {
            purportText.append("${i + 1}.${purport.getString(i)}\n\n\n")
        }
        (findViewById<TextView>(R.id.purport)).text = purportText.toString()

        // Work with playing audio from the url
        audio_link = obj.getString("audio_link")
        playAudio(audio_link)
    }
    
    fun getMobileName(): String {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                // For Android R and above
                Settings.Global.getString(
                    applicationContext.contentResolver,
                    Settings.Global.DEVICE_NAME
                ) ?: "Unknown"
            } else {
                // For older Android versions
                Build.MODEL
            }
        } catch (e: Exception) {
            "Unknown"
        }
    }
}