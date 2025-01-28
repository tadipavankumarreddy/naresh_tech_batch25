package com.nareshtech.fragcommunication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class FragmentB : Fragment() {

    lateinit var textView: TextView
    // This is the receiver
    // TODO 5: Receive the data from FragmentA & Show it on TextView
    fun updateData(data:String){
        textView.text = data
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_b, container, false)

        textView = v.findViewById(R.id.receivedText)

        return v
    }



}