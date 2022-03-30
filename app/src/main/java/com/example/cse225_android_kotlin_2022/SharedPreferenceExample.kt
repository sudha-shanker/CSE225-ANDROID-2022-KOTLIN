package com.example.cse225_android_kotlin_2022

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

// to find Shared Preference are saved go to
// Device File Explorer --> data --> data -->
// "Your app package name " (like my app project name is com.example.sharedprefrenceexample)--> shared_prefs-->myrefernce NAME(mypref)
class SharedPreferenceExample : AppCompatActivity() {

    lateinit var sharedpreferences: SharedPreferences
    lateinit var name: TextView
    lateinit var email: TextView
    val mypreference = "mypref"
    val Name = "nameKey"
    val Email = "emailKey"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference_example)

        name = findViewById(R.id.etName)
        email = findViewById(R.id.etEmail)
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE)
            name.text = sharedpreferences.getString(Name, "")
            email.text = sharedpreferences.getString(Email, "")

    }
        fun save(view: View?) {
            val n = name.text.toString()
            val e = email.text.toString()
            val editor = sharedpreferences.edit()
            editor.putString(Name, n)
            editor.putString(Email, e)
            editor.apply()
        }

        fun clear(view: View?) {
            name.text = ""
            email.text = ""
        }

        fun get(view: View)
        {
            sharedpreferences = getSharedPreferences(mypreference,Context.MODE_PRIVATE)

            name.text = sharedpreferences.getString(Name, "")
            email.text = sharedpreferences.getString(Email, "")

        }
}