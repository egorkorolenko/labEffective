package ru.korolenkoe.lab1effective.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import ru.korolenkoe.lab1effective.entities.Response
import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

private var retrofit: Retrofit? = null
private var moshi: Moshi? = null

interface MarvelApi {
    @GET("characters")
    suspend fun getListHeroes(
    ): Response

    @GET("characters/{id}")
    suspend fun getHero(
        @Path("id") id: Int
    ): Response

    companion object {
        private const val API_KEY = "3ca8e60692cb647d4ae4fdc47b7786bb"
        private const val PRIVATE_KEY = "7298dd11ff1484e98616c33caf7c4aa5977936b5"
        private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

        fun getService(): MarvelApi {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url
                val ts = (Calendar.getInstance(
                    TimeZone.getTimeZone("UTC")
                ).timeInMillis / 1000L).toString()

                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apikey", API_KEY)
                    .addQueryParameter("ts", ts)
                    .addQueryParameter(
                        "hash", md5("$ts$PRIVATE_KEY$API_KEY")
                    )
                    .build()

                chain.proceed(
                    original.newBuilder().url(url).build()
                )
            }

            if (moshi == null)
            moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

            if (retrofit == null)
                retrofit = Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create(moshi!!))
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .build()

            return retrofit!!.create(MarvelApi::class.java)
        }

        private fun md5(input: String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray()))
                .toString(16)
                .padStart(32, '0')
        }
    }
}
