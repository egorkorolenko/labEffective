package ru.korolenkoe.lab1effective.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import ru.korolenkoe.lab1effective.R
import ru.korolenkoe.lab1effective.models.HeroItem
import java.io.Serializable

@Composable
fun HeroScreen(navController: NavController?, heroItem: HeroItem) {
    HeroName(stringResource(heroItem.text))
}

@Composable
fun HeroName(text: String) {
    Text(text)
}

@Preview
@Composable
fun HeroScreenPreview() {
    HeroScreen(null, HeroItem(R.string.thor, R.drawable.thor))
}