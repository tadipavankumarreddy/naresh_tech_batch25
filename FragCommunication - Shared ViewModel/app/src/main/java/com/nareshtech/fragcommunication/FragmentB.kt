package com.nareshtech.fragcommunication

import SharedViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider


class FragmentB : Fragment() {

    lateinit var textView: TextView
    // This is the receiver

    private lateinit var sharedViewModel: SharedViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_b, container, false)
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        textView = v.findViewById(R.id.receivedText)

        sharedViewModel.sharedData.observe(viewLifecycleOwner) { data:String ->
            textView.text = data
        }

        return v
    }



}