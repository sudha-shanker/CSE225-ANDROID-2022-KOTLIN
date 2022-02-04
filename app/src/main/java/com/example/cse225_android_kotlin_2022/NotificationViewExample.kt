package com.example.cse225_android_kotlin_2022

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class NotificationViewExample : AppCompatActivity() {
    lateinit var notificationManager : NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_view_example)

        var txtView = findViewById<TextView>(R.id.tv)

        var inp = NotificationManagerExample()

        var bundle: Bundle = RemoteInput.getResultsFromIntent(intent)
        if(bundle != null)
            txtView.setText(bundle.getString(inp.myKey))

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.cancel(inp.notificationId)

    }
}
