package com.example.urbvanmobiletest.modules.transmitter.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.urbvanmobiletest.R

/**
 * A simple [Fragment] subclass.
 */
class TransmitterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transmitter, container, false)
    }


}
