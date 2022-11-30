package ru.korolenkoe.labeffective.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "response")
data class Response(
    @Json(name = "code")
    @ColumnInfo(name = "code")
    val code: Int,
    @Json(name = "data")
    @ColumnInfo(name = "data")
    val data: Data
)