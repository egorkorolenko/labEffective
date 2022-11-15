package ru.korolenkoe.lab1effective.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorMatrix
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
import ru.korolenkoe.lab1effective.Indicator
import ru.korolenkoe.lab1effective.R
import ru.korolenkoe.lab1effective.cards.ErrorCard
import ru.korolenkoe.lab1effective.models.Character
import ru.korolenkoe.lab1effective.network.ViewModelGetHero


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HeroScreen(navController: NavController?, id: Int, viewModel2: ViewModelGetHero) {

    val hero = getHeroById(id, viewModel2)
    val colorMatrix = ColorMatrix()

    Box(
        Modifier
            .fillMaxWidth()
            .padding(130.dp), contentAlignment = Alignment.Center) {
        if (viewModel2.status.value.name == "LOADING")
            Indicator()
    }


    colorMatrix.setToSaturation(0f)
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackButton(navController)

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            HeroLogo(hero!!.thumbnail!!.pathSec)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Column {
            HeroName(hero!!.name)
            HeroDescription(hero.description)
        }
    }
}


@Composable
fun getHeroById(id: Int, viewModel: ViewModelGetHero): Character? {
    viewModel.getHero(id)
    if(viewModel.status.collectAsState().value.name=="ERROR"){
        ErrorCard()
    }
    return viewModel.hero.collectAsState().value
}

@Composable
fun BackButton(navController: NavController?) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(10.dp)
    ) {
        Button(
            onClick = { navController?.popBackStack() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                contentDescription = "back_button",
                modifier = Modifier.size(50.dp)
            )
        }
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
//    HeroScreen(null, listHeroes[1].id,null)
}
