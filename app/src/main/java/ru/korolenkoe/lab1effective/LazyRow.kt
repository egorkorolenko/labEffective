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
    HeroItem(0,R.string.deadpool,R.string.deadpool, R.drawable.dedpool,
        "https://www.tailorbrands.com/wp-content/uploads/2020/03/Deadpool-.jpg"),
    HeroItem(1,R.string.captain_america,R.string.captain_america, R.drawable.capitan,
        "https://www.tailorbrands.com/wp-content/uploads/2020/03/Captain-America.jpg"),
    HeroItem(2,R.string.doctor_strange, R.string.deadpool,R.drawable.doctor,
        "https://d1yjjnpx0p53s8.cloudfront.net/styles/" +
                "logo-original-577x577/s3/122021/dr.strange_logo.png?BXKN4JxWvKVTDUw.226mVXRGhhXzqCk.&itok=pgT7oBMX"),
    HeroItem(3,R.string.hulk, R.string.hulk, R.drawable.hulk,
        "https://as2.ftcdn.net/v2/jpg/02/66/58/31/1000_F_266583146_oPl3TiJqFRC2i8YHZDII6526sM7jwN9b.jpg"),
    HeroItem(4,R.string.thor,R.string.thor,  R.drawable.thor,
        "https://as2.ftcdn.net/v2/jpg/01/92/79/43/1000_F_192794344_7Rrw58CvHjxFWPANRTZuSFnTuNts5VZ2.jpg"),
    HeroItem(5,R.string.iron_man,R.string.iron_man, R.drawable.ironman,
        "https://www.tailorbrands.com/wp-content/uploads/2020/03/Iron-Man.jpg"),
    HeroItem(6,R.string.red_widow,R.string.red_widow, R.drawable.red,
        "https://www.kindpng.com/picc/m/73-733559_scarlet-witch-symbol-png-transparent-png.png"),
    HeroItem(7,R.string.spider_man,R.string.spider_man, R.drawable.spider,
        "https://www.tailorbrands.com/wp-content/uploads/2020/03/Spiderman.jpg"),
    HeroItem(8,R.string.thanos,R.string.thanos, R.drawable.thanos,
        "https://www.kindpng.com/picc/m/" +
                "112-1129236_infinity-gauntlet-popsocket-png-download-thanos-infinity-gauntlet.png"),
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
