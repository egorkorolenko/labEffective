package ru.korolenkoe.labeffective.notification

import com.google.firebase.messaging.FirebaseMessagingService

class PushService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}app/google-services.json