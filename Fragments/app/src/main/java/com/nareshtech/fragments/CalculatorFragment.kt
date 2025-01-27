package com.nareshtech.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CalculatorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalculatorFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v =  inflater.inflate(R.layout.fragment_calculator_frament, container, false)
        // I can write the logic
        val inputlayout1 = v.findViewById<TextInputLayout>(R.id.inputLayout1)
        val inputlayout2 = v.findViewById<TextInputLayout>(R.id.inputLayout2)
        val buttonAdd =  v.findViewById<Button>(R.id.buttonAdd)
        val buttonSubtract =  v.findViewById<Button>(R.id.buttonSubtract)
        val buttonMultiply =  v.findViewById<Button>(R.id.buttonMultiply)
        val buttonDivide =  v.findViewById<Button>(R.id.buttonDivide)
        val result = v.findViewById<TextView>(R.id.textViewResult)

        buttonAdd.setOnClickListener {
            val ip1 = inputlayout1?.editText?.text.toString().toInt()
            val ip2 = inputlayout2?.editText?.text.toString().toInt()
            result.text = "${ip1+ip2}"
        }

        buttonSubtract.setOnClickListener {
            val ip1 = inputlayout1?.editText?.text.toString().toInt()
            val ip2 = inputlayout2?.editText?.text.toString().toInt()
            result.text = "${ip1-ip2}"
        }

        buttonMultiply.setOnClickListener {
            val ip1 = inputlayout1?.editText?.text.toString().toInt()
            val ip2 = inputlayout2?.editText?.text.toString().toInt()
            result.text = "${ip1*ip2}"
        }

        buttonDivide.setOnClickListener {
            val ip1 = inputlayout1?.editText?.text.toString().toInt()
            val ip2 = inputlayout2?.editText?.text.toString().toInt()
            result.text = "${ip1/ip2}"
        }

        return v
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CalculatorFrament.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalculatorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}