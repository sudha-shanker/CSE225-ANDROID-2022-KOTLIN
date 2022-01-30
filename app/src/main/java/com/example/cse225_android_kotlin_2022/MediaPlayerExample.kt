package com.example.cse225_android_kotlin_2022

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast

class MediaPlayerExample : AppCompatActivity() {
    lateinit var mp: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player_example)

        var sb= findViewById<SeekBar>(R.id.seekBar)
        var play= findViewById<ImageView>(R.id.play)
        var playing:Boolean = false
        mp=MediaPlayer.create(this, R.raw.aarti)

        sb.max=mp.duration
        sb.progress=0
        play.setOnClickListener {
            play.setImageResource(R.drawable.pause)
            if (!playing) {
                playing= true
                mp.start()
                Thread(Runnable {
                    var currentPosition = mp.currentPosition
                    val total = mp.duration
                    while (mp.isPlaying && currentPosition < total) {
                        currentPosition = try {
                            Thread.sleep(1000)
                            mp.currentPosition
                        } catch (e: InterruptedException) {
                            return@Runnable
                        } catch (e: Exception) {
                            return@Runnable
                        }
                        sb.setProgress(currentPosition)

                    }
                }).start()
            }
            else
            {
                mp.pause()
                playing=false
                play.setImageResource(R.drawable.play)
            }

        }
    }

    override fun onStop() {
        super.onStop()
        mp.pause()

    }
}