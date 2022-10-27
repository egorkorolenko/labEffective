package ru.korolenkoe.lab1effective.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ru.korolenkoe.lab1effective.R
import ru.korolenkoe.lab1effective.models.HeroItem

@Composable
fun HeroScreen(navController: NavController?, heroItem: HeroItem) {
    val colorMatrix = ColorMatrix()
    colorMatrix.setToSaturation(0f)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = heroItem.image),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.colorMatrix(colorMatrix),
                alpha = 0.3f
            )
    ) {
        BackButton(navController)

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            HeroLogo(heroItem.urlLogo)
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column {
                HeroName(stringResource(heroItem.text))
                HeroDescription(stringResource(heroItem.text))
            }
        }
    }
}

@Composable
fun BackButton(navController: NavController?) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .padding(10.dp)
            .clickable { navController?.popBackStack() }
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
            contentDescription = "back_button",
        )
    }
}

@Composable
fun HeroLogo(urlLogo: String) {
    Box(
        modifier = Modifier
            .fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(urlLogo)
                .build(),
            placeholder = painterResource(id = R.drawable.placeholder),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.border(BorderStroke(4.dp, Color.Red))
        )
    }
}

@Composable
fun HeroName(text: String) {
    Text(
        text = text,
        fontSize = 30.sp,
        fontFamily = FontFamily.Serif,
        color = Color.White,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun HeroDescription(text: String) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        color = Color.White,
        fontWeight = FontWeight.Bold,
    )
}


@Preview
@Composable
fun HeroScreenPreview() {
    HeroScreen(null, HeroItem(R.string.thor, R.drawable.thor,"\"https://www.tailorbrands.com/wp-content/uploads/2020/03/Captain-America.jpg\""))
}