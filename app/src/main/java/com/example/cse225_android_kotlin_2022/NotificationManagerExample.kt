package com.example.cse225_android_kotlin_2022

import android.app.*
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.Icon
import android.media.AudioAttributes
import android.net.Uri
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
    lateinit var soundUri: Uri
    lateinit var audioAttr: AudioAttributes
    lateinit var remoteInput: RemoteInput
    private val channelId = "My Channel Id"
    private val description = "Test Notification"
    private val title = "Notification"

    val myKey = "Remote Key"
    val notificationId = 1234

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_manager_example)
        btnNotify = findViewById(R.id.btnNotify)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btnNotify.setOnClickListener {
            val intent = Intent(this,NotificationViewExample::class.java)
            pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)

            soundUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"+
                    applicationContext.packageName+"/"+R.raw.ringtone)
            audioAttr = AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()

            remoteCollapsedViews = RemoteViews(packageName, R.layout.activity_splash_screen_example)
            remoteExpandedViews = RemoteViews(packageName, R.layout.activity_splash_screen_example_main)
            myNotificationChannel()


                remoteInput = RemoteInput.Builder(myKey).setLabel("Replying...")
                    .build()
                val action: Notification.Action =
                    Notification.Action.Builder(R.drawable.check, "Reply", pendingIntent)
                        .addRemoteInput(remoteInput).build()
                builder.addAction(action)

            notificationManager.notify(notificationId,builder.build())
        }
    }

    private fun myNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationChannel.setSound(soundUri,audioAttr)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(this,channelId)
                .setSmallIcon(R.drawable.ic_baseline_announcement)
                .setContentTitle(title)
                .setContentText(description)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.logo_toolbar))
                .setContentIntent(pendingIntent)

                /* .setStyle(
                      Notification.BigTextStyle()
                          .bigText("The road may be long, tortuous and wearied. But the resulting success is enduring, sure and sweet.
                           The fool abandons hope in the wearied journey of life. The wise gets going - holding firmly to the promise
                            of a better tomorrow. He that gives up too soon fails to understand that life rewards with success
                            only those who cling on to hope against hope. Those who hope when it is unfashionable to hope."))
                        */
                 /*  .setStyle(Notification.BigPictureStyle()
                    .bigPicture(BitmapFactory.decodeResource(this.resources,R.drawable.logo_toolbar))
                    .bigLargeIcon(null as Icon?)) */

               .setCustomContentView(remoteCollapsedViews)
               .setCustomBigContentView(remoteExpandedViews)

                 .setAutoCancel(true)

        }

        else{
            builder = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_baseline_announcement)
                .setContentTitle(title)
                .setContentText(description)
                .setLargeIcon(BitmapFactory.decodeResource(this.resources,R.drawable.logo_toolbar))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }
    }
}