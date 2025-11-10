package com.example.shopapp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.shopapp.R
import com.example.shopapp.model.db.BasketEntity
import com.example.shopapp.vm.BasketViewmodel

@Composable
fun BasketItemsRow(
    basketItems: List<BasketEntity>,
    vm: BasketViewmodel
) {
    var showAlert by remember { mutableStateOf(false) }
    var basketItem by remember { mutableStateOf<BasketEntity?>(null) }

    Column(
        Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text("Cart", fontSize = 22.sp)
        Spacer(Modifier.height(5.dp))
        LazyColumn(
            Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(bottom = 120.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            itemsIndexed(basketItems) { index, basketEntity ->
                BasketItem(
                    item = basketEntity,
                    onIncrement = { item -> vm.incrementQuantity(item) },
                    onDecrement = { item -> vm.decrementQuantity(item) },
                    onRemove = {
                        showAlert = true
                        basketItem = it
                    }
                )
                if (basketItems.size != index + 1)
                    HorizontalDivider(thickness = 1.5.dp)


            }


        }
        if (showAlert) {
            AlertDialog(
                onDismissRequest = { showAlert = false },
                dismissButton = {
                    TextButton(onClick = { showAlert = false }) { Text("Cancel") }
                },
                confirmButton = {
                    TextButton(onClick = {
                        vm.deleteItemBasket(basketItem!!);
                        showAlert = false
                    }){
                        Text("Delete")
                    }
                },
                title = { Text("Delete item: ${basketItem?.title}") },
                text = { Text("Delete Iteml;lds;slda") }
            )
        }
    }
}

@Composable
fun BasketItem(
    item: BasketEntity,
    onIncrement: (item: BasketEntity) -> Unit,
    onDecrement: (item: BasketEntity) -> Unit,
    onRemove: (item: BasketEntity) -> Unit
) {
    Column {
        Row(Modifier.fillMaxWidth()) {
            AppImage(
                model = "${item.image}",
                description = "${item.title}",
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Spacer(Modifier.width(6.dp))
            Column {
                Text("${item.title}")
                PriceText(
                    item.price ?: 0L,
                    color = Color.Black,
                    fontSize = 18.sp,
                    isColorDark = true
                )
            }
            Spacer(Modifier.weight(1F))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Size:${item.size}", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Box(
                    Modifier
                        .size(50.dp, 25.dp)
                        .background(
                            Color("#${item.colorHex}".toColorInt()), RoundedCornerShape(20.dp)
                        )
                )
            }
        }

        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { onDecrement(item) }) {
                Icon(painterResource(R.drawable.ic_arrow_down), "decrement")
            }
            Text("${item.quantity}")
            IconButton(onClick = { onIncrement(item) }) {
                Icon(painterResource(R.drawable.ic_arrow_up), "increment")
            }
            Spacer(Modifier.weight(1F))
            IconButton(onClick = { onRemove(item) }) {
                Icon(painterResource(R.drawable.ic_close), "delete", tint = Color.Red)
            }

        }

    }
}
