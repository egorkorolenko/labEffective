package ru.korolenkoe.labeffective.screens.mainscreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import ru.korolenkoe.labeffective.cards.ErrorCard
import ru.korolenkoe.labeffective.cards.HeroCard
import ru.korolenkoe.labeffective.entities.Character
import ru.korolenkoe.labeffective.network.ConnectionState
import ru.korolenkoe.labeffective.network.currentConnectivityState
import ru.korolenkoe.labeffective.screens.mainscreen.viewmodels.CharacterDBViewModel
import ru.korolenkoe.labeffective.screens.mainscreen.viewmodels.ViewModelGetHeroesApi
import ru.korolenkoe.labeffective.utils.Indicator


@Composable
fun LazyRowHeroes(
    navController: NavController?,
    viewModel: ViewModelGetHeroesApi,
    characterDBViewModel: CharacterDBViewModel
) {
    val context = LocalContext.current

    if (context.currentConnectivityState == ConnectionState.Available) {
        val heroes = viewModel.heroes.collectAsState()
        val status = viewModel.status.collectAsState()
        if (status.value.name == "DONE")
            characterDBViewModel.insertAllCharacters(heroes.value)
        Box(
            Modifier
                .fillMaxWidth()
                .padding(30.dp), contentAlignment = Alignment.Center
        ) {
            if (status.value.name == "LOADING")
                Indicator()
        }
        if (status.value.name == "ERROR") {
            ErrorCard()
        } else {
            getHeroLazyRow(characters = heroes.value, navController = navController)
        }
    } else {
        val list = characterDBViewModel.readAll.collectAsState(emptyList())
        getHeroLazyRow(characters = list.value, navController = navController)
    }
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun getHeroLazyRow(characters: List<Character>, navController: NavController?) {
    val lazyListHeroes = rememberLazyListState()

    LazyRow(
        state = lazyListHeroes,
        flingBehavior = rememberSnapperFlingBehavior(lazyListHeroes),
        horizontalArrangement = Arrangement.spacedBy(40.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(characters) { index ->
            HeroCard(hero = index, navController)
        }
    }
}
