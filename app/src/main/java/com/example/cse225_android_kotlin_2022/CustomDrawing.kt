package com.example.cse225_android_kotlin_2022

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View


class CustomDrawing(context: Context?) : View(context) {
    lateinit var p:Paint
    var x:Int = 100
    init {
        init()
    }

    private fun init() {
        p=Paint()
    }

    override fun onDraw(canvas: Canvas) {


            canvas.drawColor(Color.BLUE)


        p.color = Color.RED

            canvas.drawRect(100f, 100f, 500f, 500f, p)
        canvas.drawArc(500f, 500f, 800f, 800f, x.toFloat(), 30f,
            true, p )



    }
}