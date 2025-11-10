package com.example.shopapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.shopapp.ui.component.AnimatedSlideIn
import com.example.shopapp.ui.component.AppGradient
import com.example.shopapp.ui.component.AppImage
import com.example.shopapp.ui.component.ProductColorsRow
import com.example.shopapp.ui.component.ProductSizesRow
import com.example.shopapp.ui.utility.formatPrice
import com.example.shopapp.vm.SingleProductViewModel
import com.example.shopapp.R
import com.example.shopapp.ui.component.PriceText
import com.example.shopapp.vm.BasketViewmodel

@Composable
fun SingleProductScreen(
    productId: Long,
    innerPadding: PaddingValues,
    navController: NavHostController,
    vm: SingleProductViewModel = hiltViewModel(),
    basketViewModel: BasketViewmodel
) {


    LaunchedEffect(productId) { vm.loadProductById(productId) }
    Box(modifier = Modifier.fillMaxSize()) {

        AppImage("${vm.product?.image}", "${vm.product?.title}")
        AppGradient(Modifier.align(Alignment.BottomCenter))
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(painterResource(R.drawable.ic_arrow_back), "")
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                AnimatedSlideIn(300) {
                    Text(
                        "${vm.product?.title}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = Color.White
                    )
                }
                Spacer(Modifier.height(30.dp))
                AnimatedSlideIn(600) { PriceText(vm.product?.price ?: 0L) }
                Spacer(Modifier.height(30.dp))
                Spacer(Modifier.height(30.dp))
                ProductSizesRow(vm)
                Spacer(Modifier.height(30.dp))
                ProductColorsRow(vm)
                Spacer(Modifier.height(30.dp))
                AnimatedSlideIn(1800) {
                    Text(
                        "${vm.product?.description}",
                        fontSize = 15.sp,
                        color = Color.LightGray
                    )
                }
                AnimatedSlideIn(2300) {
                    Button(
                        onClick = {
                            basketViewModel.addToBasket(
                                vm.product,
                                vm.selectedColor,
                                vm.selectedSize
                            )
                            navController.navigate("basket")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text("Add to basket")
                    }
                }
            }
        }
    }
}