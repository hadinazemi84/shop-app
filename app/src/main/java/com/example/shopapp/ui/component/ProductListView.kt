package com.example.shopapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shopapp.model.enums.FilterType
import com.example.shopapp.vm.HomeViewModel

@Composable
fun ProductsView(vm: HomeViewModel, navController: NavHostController) {
    val filters = FilterType.entries.map { it.name }
    Column(Modifier.fillMaxWidth()) {
        ProductFiltersRow(filters, vm.selectedFilter) {
            vm.selectedFilter = it
            vm.loadProductsByFilter(vm.selectedFilter)
        }
        Spacer(Modifier.height(10.dp))
        ProductListView(vm, navController)
    }
}

@Composable
fun ProductListView(vm: HomeViewModel, navController: NavHostController) {
    ShowViewByState(vm.products) { products ->
        Column(Modifier.fillMaxWidth(), Arrangement.spacedBy(10.dp)) {
            products.forEachIndexed { index, product ->
                AnimatedSlideIn(index * 100) {
                    CostumeCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        image = product.image,
                        title = product.title,
                        onclick = { navController.navigate("product/${product.id}") })
                }
            }
        }
    }

}

@Composable
fun ProductFiltersRow(
    filters: List<String>, selectedFilter: String, onClick: (filterName: String) -> Unit
) {
    LazyRow(Modifier.fillMaxWidth()) {
        items(filters) { filterName ->
            FilterItem(filterName, selectedFilter == filterName) {
                onClick(filterName)
            }
        }
    }
}


@Composable
fun FilterItem(filterName: String, selected: Boolean, onClick: () -> Unit) {
    val backGroundColor = if (selected) Color.LightGray else Color.Transparent
    Card(
        modifier = Modifier.clickable { onClick() },
        colors = CardDefaults.cardColors(backGroundColor),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            filterName,
            Modifier.padding(horizontal = 22.dp, vertical = 5.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}