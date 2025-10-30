package com.example.shopapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.shopapp.model.products.Product
import com.example.shopapp.ui.component.AnimateSlideIn
import com.example.shopapp.ui.component.CostumeCard
import com.example.shopapp.ui.component.ShowViewByState
import com.example.shopapp.vm.ProductsViewModel

@Composable
fun ProductsScreen(
    catId: Long,
    title: String,
    navController: NavHostController,
    vm: ProductsViewModel = hiltViewModel()
) {
    LaunchedEffect(catId) { vm.loadProducts(catId) }
    val listState = rememberLazyListState()
    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisible = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val totalItemsCount = listState.layoutInfo.totalItemsCount
            lastVisible >= totalItemsCount - 1
        }
    }
    LaunchedEffect(shouldLoadMore) { vm.loadProducts(catId) }
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 26.sp)
        ShowViewByState(vm.products) { products: List<Product> ->
            LazyColumn(
                state = listState,
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                itemsIndexed(products) { index, product ->
                    AnimateSlideIn(index * 100) {
                        CostumeCard(
                            Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            product.image,
                            product.title
                        )
                    }
                }
            }
        }

    }
}