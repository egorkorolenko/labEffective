package ru.korolenkoe.labeffective.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ru.korolenkoe.labeffective.MainActivity
import ru.korolenkoe.labeffective.R

class PushService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        sendNotification(message)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun sendNotification(message: RemoteMessage) {
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val id: Int = message.data["id"]!!.toInt()

        val intent = Intent(
            Intent.ACTION_VIEW,
            "https://heroapp.com/${id}".toUri(),
            this,
            MainActivity::class.java
        ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val clickPendingIntent: PendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(1, PendingIntent.FLAG_IMMUTABLE)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "Global",
                "channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.description = "description"
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setChannelId("Global")
            .setContentTitle(message.data["title"])
            .setAutoCancel(true)
            .setContentIntent(clickPendingIntent)
            .build()

        notificationManager.notify(1, notificationBuilder)
    }


    companion object {
        const val INTENT_FILTER = "TAP_PUSH"
    }
}

