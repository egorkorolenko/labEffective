package ru.korolenkoe.labeffective.network

import okhttp3.Interceptor
import ru.korolenkoe.labeffective.BuildConfig
import ru.korolenkoe.labeffective.utils.GetMD5
import java.util.*

class MarvelApiInterceptor : Interceptor {

    private val _apikey = BuildConfig.API_KEY
    private val _privateApiKey = BuildConfig.PRIVATE_API_KEY
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
