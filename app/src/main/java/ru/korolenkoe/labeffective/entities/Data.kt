package ru.korolenkoe.labeffective.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "Data")
data class Data(
    @Json(name = "results")
    @ColumnInfo(name = "results")
    val results: List<Character>
)
