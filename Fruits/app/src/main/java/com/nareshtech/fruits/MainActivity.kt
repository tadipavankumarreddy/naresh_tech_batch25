package com.nareshtech.fruits

import android.content.Context
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val fruits = arrayOf(
            "Apple",
            "Banana",
            "Orange",
            "Strawberry",
            "Grape",
            "Watermelon",
            "Pineapple",
            "Mango",
            "Kiwi",
            "Blueberry"
        )
        val fruit_images = arrayOf<Int>(R.drawable.apple,
            R.drawable.banana,
            R.drawable.orange,
            R.drawable.strawberry,
            R.drawable.grape,
            R.drawable.watermelon,
            R.drawable.pineapple,
            R.drawable.mango,
            R.drawable.kiwi,
            R.drawable.blueberry)

        val listView:ListView = findViewById(R.id.fruits_list)
        // You will need an array adapter to populate each item of the array on the listview.
        /*val adapter:ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1,fruits)
        listView.adapter = adapter*/

        val fruitsAdapter = FruitsAdapter(this, fruits, fruit_images)
        listView.adapter = fruitsAdapter

        /*listView.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                TODO("Not yet implemented")
            }

        })*/
        listView.setOnItemClickListener { adapterView, view, i, l ->
            Snackbar.make(view,fruits.get(i),Snackbar.LENGTH_LONG).show()
        }
    }

    class FruitsAdapter(val context:Context,val fruits:Array<String>, val fruits_images:Array<Int>):BaseAdapter(){
        override fun getCount(): Int {
            return fruits.size
        }

        override fun getItem(p0: Int): Any {
            return p0
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val v:View = LayoutInflater.from(context).inflate(R.layout.fruit_item,p2,false)
            val image:ImageView = v.findViewById(R.id.fruits_image_view)
            val tv :TextView = v.findViewById(R.id.fruit_name_view)
            image.setImageResource(fruits_images.get(p0))
            tv.setText(fruits.get(p0))
            return v
        }

    }
}