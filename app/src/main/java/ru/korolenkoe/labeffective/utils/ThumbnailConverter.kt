package ru.korolenkoe.labeffective.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.room.TypeConverter
import ru.korolenkoe.labeffective.entities.Thumbnail
import java.io.ByteArrayOutputStream
import java.net.URL


class ThumbnailConverter {

    @TypeConverter
    fun fromThumbnail(thumbnail: Thumbnail): ByteArray {
        val url = URL(thumbnail.pathSec)
        val quality = 100
        val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        val outputStream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, quality, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toThumbnail(byteArray: ByteArray): Thumbnail {
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

        val quality = 100
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)
        val b = baos.toByteArray()

        return Thumbnail(Base64.encodeToString(b, Base64.DEFAULT), "")
    }
}
