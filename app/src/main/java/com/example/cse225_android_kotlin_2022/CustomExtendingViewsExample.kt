package com.example.cse225_android_kotlin_2022

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class CustomExtendingViewsExample : AppCompatActivity() {
    lateinit var customViewFan: CustomViewFan
    lateinit var customViewDrawing: CustomViewDrawing
    lateinit var customDrawing: CustomDrawing

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_extending_views_example)

      //   customViewFan =  CustomViewFan(this)
      //   setContentView(customViewFan)

       // customDrawing = CustomDrawing(this)
       // setContentView(customDrawing)




     // customViewDrawing = CustomViewDrawing(this)
     // setContentView(customViewDrawing)


         val cb = findViewById<Button>(R.id.clear)
        customViewDrawing = findViewById<View>(R.id.cd) as CustomViewDrawing

          cb.setOnClickListener {
              customViewDrawing.clear()
          }
    }
}