package ru.korolenkoe.labeffective.screens.mainscreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.korolenkoe.labeffective.R


@Composable
fun ChooseYourHeroText() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.choose_your_hero),
            fontSize = 30.sp,
            fontFamily = FontFamily.Serif
        )
    }
}


@Composable
@Preview(locale = "ar")
fun PreviewText(){
    ChooseYourHeroText()
}