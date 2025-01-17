package com.nareshtech.retrofit

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nareshtech.retrofit.dataclasses.FakeGet
import com.nareshtech.retrofit.dataclasses.PostRequest
import com.nareshtech.retrofit.dataclasses.PostResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {
    // You require an access to the interface (JsonPlaceHolderInterface.kt)
    var jsonPlaceHolderInterface:JsonPlaceHolderInterface? = null
    lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textView = findViewById(R.id.textView)

        // Inorder to initialize jsonPlaceHolderInterface, you will need a retrofit instance.
        val retrofit:Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Initalize the interface object - so that, you can direct the requests to an appropriate channel (path).
        jsonPlaceHolderInterface = retrofit.create(JsonPlaceHolderInterface::class.java)
    }

    fun getRequest(view: View) {
        jsonPlaceHolderInterface?.getData()?.enqueue(object: Callback<FakeGet>{
            override fun onResponse(call: Call<FakeGet>, response: Response<FakeGet>) {
                // We are supposed to show data on the textView.
                val fakeData: FakeGet? = response.body()
                textView.setText(fakeData?.body)
            }

            override fun onFailure(call: Call<FakeGet>, t: Throwable) {

            }
        })
    }

    fun postRequest(view: View) {
        jsonPlaceHolderInterface?.postData(PostRequest("Pavan","is at home",12))?.enqueue(object: Callback<PostResponse>{
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                val postResponse:PostResponse? = response.body()
                textView.setText("${postResponse?.id}\n${postResponse?.body}")
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}