package ru.korolenkoe.labeffective.screens.heroscreen.components

import android.content.res.Configuration
import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.korolenkoe.labeffective.R
import ru.korolenkoe.labeffective.entities.Character
import ru.korolenkoe.labeffective.network.ConnectionState
import ru.korolenkoe.labeffective.network.currentConnectivityState

@Composable
fun HeroLogo(character: Character) {
    val context = LocalContext.current

    val orientation = LocalConfiguration.current.orientation

    Box(
        modifier = if (orientation == Configuration.ORIENTATION_PORTRAIT) Modifier
            .fillMaxSize()
            .padding(0.dp, 200.dp) else Modifier
            .size(200.dp)
            .fillMaxSize()
            .padding(0.dp, 10.dp, 0.dp, 0.dp),
        contentAlignment = Alignment.Center
    ) {
        if (context.currentConnectivityState == ConnectionState.Available) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(character.thumbnail?.pathSec)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.logo_should_be_here),
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
