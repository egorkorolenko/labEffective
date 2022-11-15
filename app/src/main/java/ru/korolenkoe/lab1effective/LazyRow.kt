package ru.korolenkoe.lab1effective

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import ru.korolenkoe.lab1effective.cards.HeroCard
import ru.korolenkoe.lab1effective.network.ViewModelHeroes


@OptIn(ExperimentalSnapperApi::class)
@Composable
fun LazyRowHeroes(navController: NavController?, viewModel: ViewModelHeroes) {
    val lazyListHeroes = rememberLazyListState()
    val heroes = viewModel.heroes.collectAsState().value

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
