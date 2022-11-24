package ru.korolenkoe.lab1effective

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
import ru.korolenkoe.lab1effective.cards.ErrorCard
import ru.korolenkoe.lab1effective.cards.HeroCard
import ru.korolenkoe.lab1effective.db.CharacterDBViewModel
import ru.korolenkoe.lab1effective.models.Character
import ru.korolenkoe.lab1effective.network.ConnectionState
import ru.korolenkoe.lab1effective.network.ViewModelHeroes
import ru.korolenkoe.lab1effective.network.currentConnectivityState


@Composable
fun LazyRowHeroes(
    navController: NavController?,
    viewModel: ViewModelHeroes,
    characterDBViewModel: CharacterDBViewModel
) {
    val context = LocalContext.current

    if (context.currentConnectivityState == ConnectionState.Available) {
        val heroes = viewModel.heroes.collectAsState().value
        val status = viewModel.status.collectAsState()
        if (status.value.name == "DONE")
            characterDBViewModel.insertAllCharacters(heroes)
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
            getHereLazyRow(characters = heroes, navController = navController)
        }
    } else {
        val list = characterDBViewModel.readAll.collectAsState(emptyList())
        getHereLazyRow(characters = list.value, navController = navController)
    }
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun getHereLazyRow(characters: List<Character>, navController: NavController?) {
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
