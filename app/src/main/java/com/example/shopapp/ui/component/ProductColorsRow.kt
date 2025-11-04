package com.example.shopapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.shopapp.model.products.ProductColor
import com.example.shopapp.vm.SingleProductViewModel
import com.example.shopapp.R

@Composable
fun ProductColorsRow(vm: SingleProductViewModel) {
    var colorIndex by remember { mutableIntStateOf(0) }
    LazyRow(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        itemsIndexed(vm.product?.colors ?: listOf()) { index, color ->
            AnimatedSlideIn(1000 + (index * 100)) {
                ItemColor(color, index == colorIndex) {
                    colorIndex = index
                }
            }
        }
    }
}

@Composable
fun ItemColor(color: ProductColor, selected: Boolean, onClick: () -> Unit) {
    val borderColor = if (selected) Color.Red else Color.White
    Box(
        modifier = Modifier
            .size(40.dp)
            .clickable { onClick() }
            .border(2.dp, borderColor, CircleShape)
            .background(Color("#FF${color.hexValue}".toColorInt()), CircleShape),
        contentAlignment = Alignment.Center
    ) {
        if (selected) {
            Icon(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = "check",
                tint = Color.White

            )
        }
    }
}
