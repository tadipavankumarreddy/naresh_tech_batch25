package com.nareshtech.fragcommunication

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.google.android.material.textfield.TextInputLayout


class FragmentA : Fragment() {

    // This is the sender
    // TODO 1: Create an Interface to communicate with other fragment.
    interface OnDataPassListener{
        fun onDataPass(data:String)
    }

    // TODO 2: Create a variable of OnDataPassListener Type
    private lateinit var dataPassListener: OnDataPassListener

    // TODO 3: This variable has to be initialized
    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPassListener = context as OnDataPassListener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // TODO 4: Use the interface to send the data from editText Box when the button is clicked.
        val v = inflater.inflate(R.layout.fragment_a, container, false)

        val til = v.findViewById<TextInputLayout>(R.id.textInputLayout)
        val b = v.findViewById<Button>(R.id.submitButton)

        b.setOnClickListener {
            val content:String = til.editText?.text.toString()
            dataPassListener.onDataPass(content)
        }

        return v
    }


}