package ru.korolenkoe.lab1effective.screens

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.util.Base64
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
import ru.korolenkoe.lab1effective.R
import ru.korolenkoe.lab1effective.cards.ErrorCard
import ru.korolenkoe.lab1effective.db.CharacterDBViewModel
import ru.korolenkoe.lab1effective.models.Character
import ru.korolenkoe.lab1effective.network.ConnectionState
import ru.korolenkoe.lab1effective.network.ViewModelGetHero
import ru.korolenkoe.lab1effective.network.currentConnectivityState


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HeroScreen(
    navController: NavController?,
    id: Int,
    viewModel: ViewModelGetHero = ViewModelGetHero(),
    characterDBViewModel: CharacterDBViewModel
) {

    val hero = getHeroById(id, viewModel, characterDBViewModel)
    val colorMatrix = ColorMatrix()

    BackButton(navController)

    if (viewModel.status.collectAsState().value.name == "ERROR") {
        ErrorCard()
    } else {
        colorMatrix.setToSaturation(0f)
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                HeroLogo(hero)
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
}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun getHeroById(
    id: Int,
    viewModel: ViewModelGetHero,
    characterDBViewModel: CharacterDBViewModel
): Character {
    val status = viewModel.status.collectAsState().value
    val context = LocalContext.current

    val character: Character

    if (context.currentConnectivityState == ConnectionState.Available) {
        if (status.name == "ERROR") {
            ErrorCard()
        }
        viewModel.getHero(id)
        character = viewModel.hero.collectAsState().value!!
    } else {
        characterDBViewModel.getCharacterById(id)
        character = characterDBViewModel.hero.collectAsState().value!!
    }

    return character
}

@Composable
fun BackButton(navController: NavController?) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(10.dp)
    ) {
        Button(
            onClick = {
                navController?.popBackStack()
            },
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
//    ClearHeroScreen()
}
