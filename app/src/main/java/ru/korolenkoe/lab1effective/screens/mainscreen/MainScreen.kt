package ru.korolenkoe.lab1effective.screens.mainscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ru.korolenkoe.lab1effective.screens.mainscreen.viewmodels.CharacterDBViewModel
import ru.korolenkoe.lab1effective.screens.mainscreen.viewmodels.ViewModelGetHeroesApi
import ru.korolenkoe.lab1effective.screens.mainscreen.components.ChooseYourHeroText
import ru.korolenkoe.lab1effective.screens.mainscreen.components.LazyRowHeroes
import ru.korolenkoe.lab1effective.screens.mainscreen.components.MarvelLogo

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

