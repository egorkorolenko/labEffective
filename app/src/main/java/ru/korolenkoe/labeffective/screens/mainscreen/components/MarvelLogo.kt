package ru.korolenkoe.labeffective.screens.mainscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.korolenkoe.labeffective.R


@Composable
fun MarvelLogo() {
    Box(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .statusBarsPadding(),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.marvel), contentDescription = "Marvel logo")
    }
}

@Composable
@Preview(locale = "ar", showBackground = true)
fun PreviewLogo(){
    MarvelLogo()
}