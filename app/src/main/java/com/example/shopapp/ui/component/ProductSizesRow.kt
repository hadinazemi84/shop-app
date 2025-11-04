package com.example.shopapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shopapp.vm.SingleProductViewModel

@Composable
fun ProductSizesRow(vm: SingleProductViewModel) {
    var sizeIndex by remember { mutableIntStateOf(0) }
    LazyRow(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        itemsIndexed(vm.product?.sizes ?: listOf()) { index, size ->
            AnimatedSlideIn(800 + (index * 100)) {
                ItemSize(size.title, index == sizeIndex) {
                    sizeIndex = index
                }
            }

        }
    }
}


@Composable
fun ItemSize(title: String, selected: Boolean, onclick: () -> Unit) {
    val backgroundColor = if (selected) Color.White else Color.Transparent
    val textColor = if (selected) Color.Black else Color.White
    TextButton(
        onClick = { onclick() },
        shape = RoundedCornerShape(16.dp),
        contentPadding = PaddingValues(6.dp, 3.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor)
    ) {
        Text(title, color = textColor)
    }
}