package ru.korolenkoe.labeffective.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import ru.korolenkoe.labeffective.notification.PushService

lateinit var broadcastReceiver: BroadcastReceiver

class BroadCast {

    @Composable
    fun cas(context: Context): Int {
        val id = remember {
            mutableStateOf(0)
        }

        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                val extras = intent?.extras?.getString("id")
                id.value = extras!!.toInt()
            }
        }

        val intentFilter = IntentFilter()
        intentFilter.addAction(PushService.INTENT_FILTER)

        context.registerReceiver(broadcastReceiver, intentFilter)
        return id.value
    }
}
