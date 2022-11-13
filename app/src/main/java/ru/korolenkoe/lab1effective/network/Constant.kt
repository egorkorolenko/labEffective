package ru.korolenkoe.lab1effective.network

import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

class Constant {

    companion object {
        val ts = (Calendar.getInstance(
            TimeZone.getTimeZone("UTC")
        ).timeInMillis / 1000L).toString()
        const val API_KEY = "f222f067928c0d48f7c8bcb401fa04a7"
        const val PRIVATE_API_KEY = "76f73570542abbe7aec661b408e882f8c7a87e2b"

        fun md5(): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest("${ts}${PRIVATE_API_KEY}${API_KEY}".toByteArray()))
                .toString(16)
                .padStart(32, '0')
        }
    }
}