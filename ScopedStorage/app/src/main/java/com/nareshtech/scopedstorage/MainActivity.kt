package com.nareshtech.scopedstorage

import android.content.ContentUris
import android.content.ContentValues
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.MediaStore.Audio.Media
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    lateinit var textInputLayout: TextInputLayout
    lateinit var resultTextView:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textInputLayout = findViewById(R.id.textInputLayout)
        resultTextView = findViewById(R.id.textViewResult)
    }

    fun readFile(view: View) {
        // TODO 2: Read data from the same text file
        val resolver = contentResolver
        val uri = MediaStore.Files.getContentUri("external")

        val cursor = resolver.query(uri, arrayOf(MediaStore.Files.FileColumns._ID),
            "${MediaStore.Files.FileColumns.DISPLAY_NAME} = ?", arrayOf("myFirstFile.txt"), null)

        cursor?.use { it->
            if(it.moveToFirst()){
                val id = it.getLong(0)
                val fileUri = ContentUris.withAppendedId(uri,id)
                val data = resolver.openInputStream(fileUri)?.bufferedReader().use { reader -> reader?.readText() }
                resultTextView.text = data
            }
        }
    }

    fun writeFile(view: View) {
        val data = textInputLayout.editText?.text.toString()
        // TODO 1: Create a file if it is not already existing and write data to it.
        val resolver = contentResolver

        val values = ContentValues().apply {
            put(MediaStore.Files.FileColumns.DISPLAY_NAME, "myFirstFile.txt")
            put(MediaStore.Files.FileColumns.MIME_TYPE,"text/plain")
            put(MediaStore.Files.FileColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
        }

        val uri = resolver.insert(MediaStore.Files.getContentUri("external"), values)

        uri?.let {
            resolver.openOutputStream(it)?.use {
                outputstream->
                outputstream.write(data.toByteArray())
            }
        }
    }
}