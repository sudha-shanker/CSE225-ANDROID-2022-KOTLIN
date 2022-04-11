package com.example.cse225_android_kotlin_2022

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.IOException

class ExternalDetails : AppCompatActivity() {

    lateinit var fstream: FileInputStream
    private val filename = "SampleFile.txt"
    private val filepath = "MyFileStorage"
    lateinit var myExternalFile: File

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_external_details)
        val result = findViewById<TextView>(R.id.resultView)
        val back = findViewById<Button>(R.id.btnBack)
        try {
            myExternalFile = File(getExternalFilesDir(filepath), filename)
            fstream = FileInputStream(myExternalFile)
            val sbuffer = StringBuffer()
            var i: Int
            while (fstream.read().also { i = it } != -1) {
                sbuffer.append(i.toChar())
            }
            fstream.close()
            val details = sbuffer.toString().split("\n").toTypedArray()
            result.text = """
                Name: ${details[0]}
                Password: ${details[1]}
                """.trimIndent()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        back.setOnClickListener {
            intent = Intent(this, ExternalStorageExample::class.java)
            startActivity(intent)
        }
    }
}