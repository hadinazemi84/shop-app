package com.example.shopapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopapp.vm.SingleProductViewModel

@Composable
fun ProductSizesRow(vm: SingleProductViewModel) {

    AnimatedSlideIn(800) {
        Text(
            "Size",
            fontWeight = FontWeight.Medium,
            fontSize = 23.sp,
            color = Color.White
        )
    }
    Spacer(Modifier.height(5.dp))
    LazyRow(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        itemsIndexed(vm.product?.sizes ?: listOf()) { index, size ->
            if (index == 0) vm.selectedSize = size
            AnimatedSlideIn(800 + (index * 100)) {
                ItemSize("${size.title}", vm.selectedSize == size) {
                    vm.selectedSize = size
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