package com.example.shopapp.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.shopapp.ui.utility.formatPrice

@Composable
fun PriceText(price: Long, fontSize: TextUnit = 22.sp, color: Color = Color.White) {

    val price = buildAnnotatedString {
        withStyle(SpanStyle(fontSize = fontSize, color = color)) {
            append("${formatPrice(price)} ")
        }

        withStyle(SpanStyle(fontSize = (fontSize / 1.5), color = Color.DarkGray)) {
            append("Toman")
        }
    }

    Text(price)
}