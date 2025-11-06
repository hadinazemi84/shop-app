package com.example.shopapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableLongStateOf
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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.shopapp.R
import com.example.shopapp.model.db.BasketEntity
import com.example.shopapp.ui.component.AppImage
import com.example.shopapp.ui.component.PriceText
import com.example.shopapp.vm.BasketViewmodel

@Composable
fun BasketScreen(navController: NavHostController, vm: BasketViewmodel = hiltViewModel()) {
    val basketItems = vm.basketItems.observeAsState()



    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        if (basketItems.value.isNullOrEmpty()) {
            Text("Basket list is empty", modifier = Modifier.align(Alignment.Center))
        } else {

                BasketListRow(basketItems.value ?: listOf(), vm)
                Row(Modifier.fillMaxWidth().align(Alignment.BottomCenter), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Total", fontWeight = FontWeight.Bold)
                    Text("")
                }


        }
    }
}

@Composable
fun BasketListRow(basketItems: List<BasketEntity>, vm: BasketViewmodel) {
    var showAlert by remember { mutableStateOf(false) }
    var itemBasket by remember { mutableStateOf<BasketEntity?>(null) }

    Text("Cart", fontWeight = FontWeight.Bold, fontSize = 30.sp)
    Spacer(Modifier.height(10.dp))
    LazyColumn(Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(5.dp)) {
        items(basketItems) { basketItem ->
            BasketItem(
                basketItem,
                onIncrement = { vm.incrementQuantity(it) },
                onDecrement = { vm.decrementQuantity(it) }) {
                showAlert = true
                itemBasket = it
            }
        }

    }

    if (showAlert) {
        AlertDialog(onDismissRequest = { showAlert = false }, confirmButton = {
            TextButton(onClick = {

                vm.deleteItemBasket(itemBasket!!)

            }) { Text("Delete", color = Color.Red) }
        }, dismissButton = {
            TextButton(onClick = { showAlert = false }) {
                Text(
                    "Cancel"
                )
            }
        }, title = { Text("Delete Item") }, text = { Text("do you delete item?") })
    }

}

@Composable
fun BasketItem(
    item: BasketEntity,
    onIncrement: (BasketEntity) -> Unit,
    onDecrement: (BasketEntity) -> Unit,
    onRemove: (BasketEntity) -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth()) {
            AppImage(
                "${item.image}", "", Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(15.dp))
            )
            Spacer(Modifier.width(5.dp))
            Column {
                Text("${item.title}", fontSize = 20.sp)
                PriceText((item.price ?: 0L) * item.quantity, 18.sp, Color.Black)
            }
            Spacer(Modifier.weight(1F))
            Column {
                Text("Size:${item.size}", fontSize = 14.sp)
                Box(
                    Modifier
                        .size(50.dp, 25.dp)
                        .background(
                            Color("#${item.colorHex}".toColorInt()), RoundedCornerShape(18.dp)
                        )

                )
            }
        }
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { onIncrement(item) }) {
                    Icon(painterResource(R.drawable.ic_arrow_up), "Increment")
                }
                Text("${item.quantity}")
                IconButton(onClick = { onDecrement(item) }) {
                    Icon(painterResource(R.drawable.ic_arrow_down), "Decrement")
                }
            }
            IconButton(onClick = { onRemove(item) }) {
                Icon(painterResource(R.drawable.ic_close), "Delete", tint = Color.Red)
            }

        }
        HorizontalDivider(thickness = 2.5.dp)

    }

}
