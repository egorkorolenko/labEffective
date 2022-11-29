package ru.korolenkoe.lab1effective.screens.heroscreen.components

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.korolenkoe.lab1effective.entities.Character
import ru.korolenkoe.lab1effective.network.ConnectionState
import ru.korolenkoe.lab1effective.network.currentConnectivityState

@Composable
fun HeroLogo(character: Character) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        if (context.currentConnectivityState == ConnectionState.Available) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.thumbnail?.pathSec)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier.border(BorderStroke(4.dp, Color.Red))
            )
        } else {
            if (character.id != 1) {
                val bitmapString = character.thumbnail?.path!!
                val imageBytes = Base64.decode(bitmapString, 0)
                val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                Image(bitmap = bitmap.asImageBitmap(), contentDescription = "")
            }
        }
    }
}
