package com.example.shopapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage

/*
@Composable
fun AppImage(model: String, description: String) {

    SubcomposeAsyncImage(
        modifier = Modifier.fillMaxSize(),
        model = model,
        contentDescription = description,
        loading = {
            CircularProgressIndicator()
        },
        contentScale = ContentScale.Crop
    )

}
*/

@Composable
fun AppImage(model: String, description: String, modifier: Modifier = Modifier) {


    SubcomposeAsyncImage(
        modifier = modifier.fillMaxSize(),
        model = model,
        contentDescription = description,
        contentScale = ContentScale.Crop,
        loading = {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray.copy(alpha = 0.4f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color.White
                )
            }
        }
    )
}