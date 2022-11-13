package ru.korolenkoe.lab1effective.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url
import ru.korolenkoe.lab1effective.models.Character
import ru.korolenkoe.lab1effective.models.Response

interface RetrofitApi {
//
//    @GET("/v1/public/characters")
//    suspend fun getListHeroes(
//        @Query("apikey")apikey:String = Constant.API_KEY,
//        @Query("ts")ts:String = Constant.ts,
//        @Query("hash")hash:String = Constant.md5(),
//    ): Response

//
//    @GET("/v1/public/characters/?ts=1668014292&apikey=f222f067928c0d48f7c8bcb401fa04a7&hash=b6900c48887e2ef1543293bb64d045c5")
//    fun getListHeroes(
////        @Query("apikey")apikey:String = Constant.API_KEY,
////        @Query("ts")ts:String = Constant.ts,
////        @Query("hash")hash:String = Constant.md5(),
//    ): Call<Response>


    @GET
    suspend fun getListHeroes(
        @Url url: String
    ): Response

    @GET
    suspend fun getHero(@Url url: String): Response
}