package com.example.cse225_android_kotlin_2022

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.RemoteViews
import androidx.appcompat.app.AppCompatActivity

class NotificationManagerExample: AppCompatActivity() {
    lateinit var notificationManager : NotificationManager
    lateinit var notificationChannel : NotificationChannel
    lateinit var builder : Notification.Builder
    lateinit var btnNotify: Button
    lateinit var remoteCollapsedViews: RemoteViews
    lateinit var remoteExpandedViews:RemoteViews
    lateinit var pendingIntent: PendingIntent
    private val channelId = "My Channel Id"
    private val description = "Test Notification"
    private val title = "Notification"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_manager_example)
        btnNotify = findViewById(R.id.btnNotify)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btnNotify.setOnClickListener {
            val intent = Intent(this,NotificationViewExample::class.java)
            pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)


            remoteCollapsedViews = RemoteViews(packageName, R.layout.activity_splash_screen_example)
            remoteExpandedViews = RemoteViews(packageName, R.layout.activity_splash_screen_example_main)
            myNotificationChannel()


        }
    }

    private fun myNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            //notificationChannel.setSound()
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this,channelId)
                .setSmallIcon(R.drawable.ic_baseline_announcement)
                .setContentTitle(title)
                .setContentText(description)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.logo_toolbar))
                .setContentIntent(pendingIntent)
                /*  .setStyle(
                      Notification.BigTextStyle()
                          .bigText("Welcome to tutlane, it provides a tutorials related to all technologies in software industry. Here we covered complete tutorials from basic to adavanced topics from all technologies"))*/
                .setStyle(Notification.BigPictureStyle()
                    .bigPicture(BitmapFactory.decodeResource(this.resources,R.drawable.logo_toolbar))
                    .bigLargeIcon(null as Icon?))
                // .setCustomContentView(remoteCollapsedViews)
                //  .setCustomBigContentView(remoteExpandedViews)
                // .setStyle(NotificationCompat.DecoratedCustomViewStyle() as Notification.Style)
                .setAutoCancel(true)

        }else{

            builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_baseline_announcement)
                .setContentTitle(title)
                .setContentText(description)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.logo_toolbar))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }
        notificationManager.notify(1234,builder.build())
    }

}