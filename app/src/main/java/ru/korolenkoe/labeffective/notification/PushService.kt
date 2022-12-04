package ru.korolenkoe.labeffective.notification

import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class PushService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val intent = Intent(INTENT_FILTER)
        message.data.forEach{ entity ->
            intent.putExtra(entity.key, entity.value)
        }
        sendBroadcast(intent)
    }

    companion object {
        const val INTENT_FILTER = "TAP_PUSH"
    }
}

