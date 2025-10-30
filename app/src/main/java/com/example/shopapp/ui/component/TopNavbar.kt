package com.example.shopapp.ui.component

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.shopapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavbar() {
    TopAppBar(
        title = {
            Text(
                text = "OnlineShop",
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Companion.Serif
            )
        },
        actions = {

            IconButton(onClick = {}) {
                BadgedBox(badge = {
                    Badge() {
                        Text("2")
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