package com.example.cse225_android_kotlin_2022

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class MessageFragment: Fragment() {
    lateinit var v: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?  ): View? {

        v = inflater.inflate(R.layout.fragment_message,container,false)
        return  v
    }
}