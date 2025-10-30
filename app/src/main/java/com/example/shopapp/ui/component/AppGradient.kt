package com.example.shopapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun AppGradient() {
    val colors = listOf(
        Color.Transparent,
        Color.Black.copy(alpha = 0.5f),
        Color.Black.copy(alpha = 0.85f)
    )
    Box(
        Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors))
    )
}