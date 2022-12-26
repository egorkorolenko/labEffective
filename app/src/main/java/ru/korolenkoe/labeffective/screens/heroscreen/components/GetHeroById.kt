package ru.korolenkoe.labeffective.screens.heroscreen.components

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import ru.korolenkoe.labeffective.cards.ErrorCard
import ru.korolenkoe.labeffective.screens.mainscreen.viewmodels.CharacterDBViewModel
import ru.korolenkoe.labeffective.entities.Character
import ru.korolenkoe.labeffective.network.ConnectionState
import ru.korolenkoe.labeffective.network.currentConnectivityState
import ru.korolenkoe.labeffective.screens.heroscreen.ViewModelGetHeroApi


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun GetHeroById(
    id: Int,
    viewModel: ViewModelGetHeroApi,
    characterDBViewModel: CharacterDBViewModel
): Character {
    val status = viewModel.status.collectAsState().value
    val context = LocalContext.current

    val character: Character = if (context.currentConnectivityState == ConnectionState.Available) {
        if (status.name == "ERROR") {
            ErrorCard()
        }
        viewModel.getHero(id)
        viewModel.hero.collectAsState().value!!
    } else {
        characterDBViewModel.getCharacterById(id)
        characterDBViewModel.hero.collectAsState().value!!
    }

    return character
}
