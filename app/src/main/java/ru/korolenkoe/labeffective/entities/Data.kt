package ru.korolenkoe.labeffective.entities

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "results")
    val results: List<Character>
)
