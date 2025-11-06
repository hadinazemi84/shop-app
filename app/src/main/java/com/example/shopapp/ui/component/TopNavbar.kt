package com.example.shopapp.ui.component

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.shopapp.R
import com.example.shopapp.vm.BasketViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavbar(navController: NavHostController, basketVm: BasketViewmodel = hiltViewModel()) {
    val basket = basketVm.basketItems.observeAsState()
    TopAppBar(
        title = {
            Text(
                text = "OnlineShop",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Companion.Serif
            )
        },
        actions = {

            IconButton(onClick = {navController.navigate("basket")}) {
                BadgedBox(badge = {
                    Badge {
                        Text("${basket.value?.size ?: 0}")
                    }
                }) {
                    Icon(painterResource(R.drawable.ic_shopping_cart), "User")
                }
            }

            IconButton(onClick = {}) {
                Icon(painterResource(R.drawable.ic_person), "User")
            }

        },
    )
}