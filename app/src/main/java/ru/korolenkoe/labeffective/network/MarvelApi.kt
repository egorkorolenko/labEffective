package ru.korolenkoe.labeffective.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import ru.korolenkoe.labeffective.entities.Response

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
        private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

        fun getService(): MarvelApi {
            val marvelApiInterceptor = MarvelApiInterceptor()

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)

            httpClient.addInterceptor { chain ->
                marvelApiInterceptor.intercept(chain = chain)
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
    }
}
