package ru.korolenkoe.lab1effective

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import ru.korolenkoe.lab1effective.cards.HeroCard
import ru.korolenkoe.lab1effective.models.HeroItem

internal val listHeroes = listOf(
    HeroItem(R.string.deadpool, R.drawable.dedpool),
    HeroItem(R.string.captain_america, R.drawable.capitan),
    HeroItem(R.string.doctor_strange, R.drawable.doctor),
    HeroItem(R.string.hulk, R.drawable.hulk),
    HeroItem(R.string.thor, R.drawable.thor),
    HeroItem(R.string.iron_man, R.drawable.ironman),
    HeroItem(R.string.red_widow, R.drawable.red),
    HeroItem(R.string.spider_man, R.drawable.spider),
    HeroItem(R.string.thanos, R.drawable.thanos),
)

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun LazyRowHeroes(navController: NavController?) {
    val lazyListHeroes = rememberLazyListState()

    LazyRow(
        state = lazyListHeroes,
        flingBehavior = rememberSnapperFlingBehavior(lazyListHeroes),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(40.dp),
        modifier = Modifier.fillMaxWidth(),
    ) {
        items(listHeroes) { index ->
            HeroCard(hero = index, navController)
        }
    }
}
