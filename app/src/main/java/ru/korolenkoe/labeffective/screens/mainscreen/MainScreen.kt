package ru.korolenkoe.labeffective.screens.mainscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import ru.korolenkoe.labeffective.screens.mainscreen.components.ChooseYourHeroText
import ru.korolenkoe.labeffective.screens.mainscreen.components.LazyRowHeroes
import ru.korolenkoe.labeffective.screens.mainscreen.components.MarvelLogo
import ru.korolenkoe.labeffective.screens.mainscreen.viewmodels.CharacterDBViewModel
import ru.korolenkoe.labeffective.screens.mainscreen.viewmodels.ViewModelGetHeroesApi

@Composable
fun MainScreen(
    navController: NavController?,
    viewModel: ViewModelGetHeroesApi,
    characterDBViewModel: CharacterDBViewModel
) {
    Column {
        MarvelLogo()
        ChooseYourHeroText()
        LazyRowHeroes(navController, viewModel, characterDBViewModel)
    }
}

