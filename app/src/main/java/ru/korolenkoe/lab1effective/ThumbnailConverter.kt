package ru.korolenkoe.lab1effective

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.room.TypeConverter
import ru.korolenkoe.lab1effective.models.Thumbnail
import java.io.ByteArrayOutputStream
import java.net.URL


class ThumbnailConverter {

    @TypeConverter
    fun fromThumbnail(thumbnail: Thumbnail): ByteArray {
        val url = URL(thumbnail.pathSec)
        val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        val outputStream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toThumbnail(byteArray: ByteArray): Thumbnail {
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()

//        return Thumbnail("$bitmap", "")
        return Thumbnail(Base64.encodeToString(b,Base64.DEFAULT), "")
    }
}
