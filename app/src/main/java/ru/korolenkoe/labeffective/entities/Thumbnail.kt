package ru.korolenkoe.labeffective.entities

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Keep
data class Thumbnail(
    @Json(name = "path")
    val path: String,
    @Json(name = "extension")
    val extension: String
) {
    val pathSec: String
        get() = "$path.$extension"
}
