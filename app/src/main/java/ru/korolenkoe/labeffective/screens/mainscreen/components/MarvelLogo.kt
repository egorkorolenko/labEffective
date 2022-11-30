package ru.korolenkoe.labeffective.screens.mainscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.korolenkoe.labeffective.R


@Composable
fun MarvelLogo() {
    Box(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .padding(0.dp, 20.dp, 0.dp, 0.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(id = R.drawable.marvel), contentDescription = "Marvel logo")
    }
}
