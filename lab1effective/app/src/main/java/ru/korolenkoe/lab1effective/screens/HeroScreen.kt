package ru.korolenkoe.lab1effective.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ru.korolenkoe.lab1effective.R
import ru.korolenkoe.lab1effective.models.HeroItem
import ru.korolenkoe.lab1effective.navigation.Screen

@Composable
fun HeroScreen(navController: NavController?, heroItem: HeroItem) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BackButton(navController)

        Box {
            HeroLogo()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Column {
                HeroName(stringResource(heroItem.text))
                HeroDescription(stringResource(heroItem.text))
            }
        }
    }
}

@Composable
fun BackButton(navController: NavController?) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .padding(10.dp)
            .clickable { navController?.navigate(Screen.MainScreen.route) }) {
        Image(painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24), contentDescription = "back_button")
    }
}

@Composable
fun HeroLogo() {

}

@Composable
fun HeroName(text: String) {
    Text(
        text = text,
        fontSize = 30.sp,
        fontFamily = FontFamily.Serif,
        color = Color.White,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun HeroDescription(text: String) {
    Text(
        text = text,
        fontSize = 20.sp,
        fontFamily = FontFamily.Serif,
        color = Color.White,
        fontWeight = FontWeight.Bold,
    )
}


@Preview
@Composable
fun HeroScreenPreview() {
    HeroScreen(null, HeroItem(R.string.thor, R.drawable.thor))
}