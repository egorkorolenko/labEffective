package ru.korolenkoe.labeffective.network

import okhttp3.Interceptor
import ru.korolenkoe.labeffective.utils.GetMD5
import java.util.*

class MarvelApiInterceptor : Interceptor {

    private val _apikey = "3ca8e60692cb647d4ae4fdc47b7786bb"
    private val _privateApiKey = "7298dd11ff1484e98616c33caf7c4aa5977936b5"
    private val getMd5 = GetMD5()

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val original = chain.request()
        val originalHttpUrl = original.url
        val ts = (Calendar.getInstance(
            TimeZone.getTimeZone("UTC")
        ).timeInMillis / 1000L).toString()

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", _apikey)
            .addQueryParameter("ts", ts)
            .addQueryParameter(
                "hash", getMd5.md5("$ts${_privateApiKey}${_apikey}")
            )
            .build()

        return chain.proceed(
            original.newBuilder().url(url).build()
        )
    }

}