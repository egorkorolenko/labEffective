package ru.korolenkoe.labeffective.entities

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class Response(
    @Json(name = "code")
    val code: Int,
    @Json(name = "data")
    val data: Data
)
