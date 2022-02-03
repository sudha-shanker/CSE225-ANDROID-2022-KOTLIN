package com.example.cse225_android_kotlin_2022

import android.content.BroadcastReceiver
import android.content.Context

import android.content.Intent
import android.media.MediaPlayer


import android.util.Log
import android.widget.Toast



class AlarmManagerBroadcast : BroadcastReceiver() {



    override fun onReceive(context: Context, intent: Intent) {
        var mp = MediaPlayer.create(context, R.raw.alarm)
        Log.d("Hello","repeating alarm")
        mp.start()
        Toast.makeText(context, "Alarm Ringing", Toast.LENGTH_LONG).show()
    }

}