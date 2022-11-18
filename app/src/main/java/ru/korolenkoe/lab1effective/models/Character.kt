package ru.korolenkoe.lab1effective.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.korolenkoe.lab1effective.ThumbnailConverter

@JsonClass(generateAdapter = true)
@Entity(tableName = "characters")
data class Character(
    @Json(name = "id")
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int,
    @Json(name = "name")
    @ColumnInfo(name = "name")
    val name: String,
    @Json(name = "description")
    @ColumnInfo(name = "description")
    val description: String,
    @Json(name = "thumbnail")
    @ColumnInfo(name = "thumbnail")
    @TypeConverters(ThumbnailConverter::class)
    val thumbnail: Thumbnail?
)

@JsonClass(generateAdapter = true)
//@Entity
data class Thumbnail(
    @Json(name = "path")
//    @ColumnInfo(name = "path")
    val path: String,
    @Json(name = "extension")
//    @ColumnInfo(name = "extension")
    val extension: String
) {
    val pathSec: String
        get() = "$path.$extension"
}

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

@JsonClass(generateAdapter = true)
@Entity(tableName = "Data")
data class Data(
    @Json(name = "results")
    @ColumnInfo(name = "results")
    val results: List<Character>
)
