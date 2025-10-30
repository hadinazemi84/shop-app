package com.example.shopapp.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shopapp.model.products.ProductCategory
import com.example.shopapp.vm.HomeViewModel

@Composable
fun CategoriesRow(vm: HomeViewModel, navController: NavHostController) {
    ShowViewByState(vm.categories) { productCategories ->
        LazyRow(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            itemsIndexed(productCategories) { index, item ->
                AnimateSlideIn(index * 100) {
                    CategoryItem(item) {
                        navController.navigate("products/${item.id}/${item.title}")
                    }
                }
            }
        }
    }
}

@Composable
fun CategoryItem(item: ProductCategory, onClick: () -> Unit) {
    CostumeCard(
        modifier = Modifier.size(150.dp, 200.dp),
        title = item.title,
        image = item.image,
        onclick = { onClick() })
}

