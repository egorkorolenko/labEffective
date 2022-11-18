package ru.korolenkoe.lab1effective

import android.annotation.SuppressLint
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
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.launch
import ru.korolenkoe.lab1effective.cards.ErrorCard
import ru.korolenkoe.lab1effective.cards.HeroCard
import ru.korolenkoe.lab1effective.db.CharacterApplication
import ru.korolenkoe.lab1effective.db.CharacterDatabase
import ru.korolenkoe.lab1effective.db.CharacterViewModel
import ru.korolenkoe.lab1effective.models.Character
import ru.korolenkoe.lab1effective.network.ViewModelHeroes
import ru.korolenkoe.lab1effective.repository.CharacterRepository


@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalSnapperApi::class)
@Composable
fun LazyRowHeroes(
    navController: NavController?,
    viewModel: ViewModelHeroes,
    characterViewModel: CharacterViewModel
) {
    val lazyListHeroes = rememberLazyListState()
    val heroes = viewModel.heroes.collectAsState().value
    val status = viewModel.status.collectAsState()

   characterViewModel.insertAllCharacters(heroes)

    Box(
        Modifier
            .fillMaxWidth()
            .padding(30.dp), contentAlignment = Alignment.Center
    ) {
        if (status.value.name == "LOADING")
            Indicator()
    }
    if (status.value.name == "ERROR")
        ErrorCard()


    LazyRow(
        state = lazyListHeroes,
        flingBehavior = rememberSnapperFlingBehavior(lazyListHeroes),
        horizontalArrangement = Arrangement.spacedBy(40.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(heroes) { index ->
            HeroCard(hero = index, navController)
        }
    }
}
