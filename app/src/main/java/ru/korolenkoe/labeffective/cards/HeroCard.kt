@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package ru.korolenkoe.labeffective.cards

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.korolenkoe.labeffective.R
import ru.korolenkoe.labeffective.entities.Character
import ru.korolenkoe.labeffective.entities.Thumbnail
import ru.korolenkoe.labeffective.navigation.Screen
import ru.korolenkoe.labeffective.network.ConnectionState
import ru.korolenkoe.labeffective.network.currentConnectivityState


@Composable
fun HeroCard(
    hero: Character,
    navController: NavController?,
) {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .padding(20.dp)
            .border(BorderStroke(4.dp, Color.Red))
            .clickable { navController?.navigate("${Screen.HeroScreen.route}/${hero.id}") },
        shape = RoundedCornerShape(16.dp),
        elevation = 15.dp
    ) {
        Box(Modifier.width(350.dp), contentAlignment = Alignment.Center) {
            if (context.currentConnectivityState == ConnectionState.Available) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(hero.thumbnail!!.pathSec)
                        .build(),
                    placeholder = painterResource(id = R.drawable.placeholder),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                )
            } else {
                val bitmapString = hero.thumbnail?.path!!
                val imageBytes = Base64.decode(bitmapString, 0)
                val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                Image(bitmap =bitmap.asImageBitmap(), contentDescription ="")
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = hero.name,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview(widthDp = 300, heightDp = 550)
@Composable
fun HeroCardPreview(
    navController: NavController?
) {
    HeroCard(
        Character(1, "Heto", "Desc", Thumbnail("drawable/capitan", ".jpg")),
        navController
        )
}
