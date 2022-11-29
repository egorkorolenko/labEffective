package ru.korolenkoe.lab1effective.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.korolenkoe.lab1effective.LazyRowHeroes
import ru.korolenkoe.lab1effective.R
import ru.korolenkoe.lab1effective.db.CharacterDBViewModel
import ru.korolenkoe.lab1effective.network.ViewModelGetHeroesApi

@Composable
fun MainScreen(
    navController: NavController?,
    viewModel: ViewModelGetHeroesApi,
    characterDBViewModel: CharacterDBViewModel
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        MarvelLogo()
        ChooseYourHeroText()
        LazyRowHeroes(navController, viewModel, characterDBViewModel)
    }
}

@Composable
fun MarvelLogo() {
    Box(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .padding(0.dp, 20.dp, 0.dp, 0.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.marvel), contentDescription = "Marvel logo")
    }
}

@Composable
fun ChooseYourHeroText() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 10.dp, 0.dp, 0.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.choose_your_hero),
            fontSize = 30.sp,
            fontFamily = FontFamily.Serif
        )
    }
}
