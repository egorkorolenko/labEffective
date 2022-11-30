package ru.korolenkoe.labeffective.screens.heroscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.korolenkoe.labeffective.R


@Composable
fun BackButton(navController: NavController?) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(10.dp)
    ) {
        Button(
            onClick = {
                navController?.popBackStack()
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                contentDescription = "back_button",
                modifier = Modifier.size(50.dp)
            )
        }
    }
}

