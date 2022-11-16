package ru.korolenkoe.lab1effective.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.korolenkoe.lab1effective.R


@Composable
fun ErrorCard() {
    Card(
        modifier = Modifier
            .padding(20.dp,120.dp).size(350.dp,150.dp)
            .border(BorderStroke(4.dp, Color.Red))
    ) {
        Box(Modifier.width(350.dp), contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = R.drawable.placeholder),
                contentDescription = "placeholder")
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
            ) {
                Text(
                    text = "Не удалось загрузить данные:(",
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Serif,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Preview(widthDp = 300, heightDp = 550)
@Composable
fun ErrorCardPreview(
) {
    ErrorCard()
}

