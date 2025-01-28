package com.nareshtech.fragcommunication

import SharedViewModel
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputLayout


class FragmentA : Fragment() {

  private lateinit var sharedViewModel: SharedViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
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
            val data = til.editText?.text.toString()
            sharedViewModel.setData(data)
        }

        return v
    }


}