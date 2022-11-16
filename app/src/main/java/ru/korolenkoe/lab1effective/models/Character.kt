package ru.korolenkoe.lab1effective.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Character(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail?
)

@JsonClass(generateAdapter = true)
data class Thumbnail(
    @Json(name = "path")
    val path: String,
    @Json(name = "extension")
        val extension: String
) {
    val pathSec: String
        get() = "$path.$extension"
}

@JsonClass(generateAdapter = true)
data class Response(
    @Json(name = "code")
    val code: Int,
    @Json(name = "data")
    val data: Data
    )

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "results")
    val results: List<Character>
)
