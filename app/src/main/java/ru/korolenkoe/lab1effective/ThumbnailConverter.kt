package ru.korolenkoe.lab1effective

import androidx.room.TypeConverter
import ru.korolenkoe.lab1effective.models.Thumbnail

class ThumbnailConverter {

    @TypeConverter
    fun fromThumbnail(thumbnail: Thumbnail):String{
        return thumbnail.pathSec
    }

    @TypeConverter
    fun toThumbnail(string: String):Thumbnail{
        val path = string.split('.')
        return Thumbnail(path[0],path[1])
    }
}
