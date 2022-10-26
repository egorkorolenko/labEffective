@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package ru.korolenkoe.lab1effective.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.korolenkoe.lab1effective.R
import ru.korolenkoe.lab1effective.models.HeroItem
import ru.korolenkoe.lab1effective.navigation.Screen
import ru.korolenkoe.lab1effective.screens.HeroScreen
import ru.korolenkoe.lab1effective.screens.MainScreen

private class HeroCardProvider : PreviewParameterProvider<HeroItem> {
    override val values = sequenceOf(HeroItem(R.string.thor, R.drawable.thor))
}

@Preview(widthDp = 300, heightDp = 550)
@Composable
fun HeroCard(
    @PreviewParameter(HeroCardProvider::class, 1) hero: HeroItem,
    navController: NavController?
) {
    Card(
        modifier = Modifier
            .padding(20.dp)
            .clickable { navController?.navigate(Screen.HeroScreen.route) },
        shape = RoundedCornerShape(16.dp),
        elevation = 15.dp
    ) {
        Box(Modifier.width(350.dp)) {
            Image(
                painter = painterResource(hero.image),
                contentDescription = "Marvel logo",
                modifier = Modifier.border(BorderStroke(4.dp, Color.Red)),
                contentScale = ContentScale.Crop
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = stringResource(id = hero.text),
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
