package com.example.shopapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.shopapp.ui.component.CategoriesRow
import com.example.shopapp.ui.component.ProductsView
import com.example.shopapp.ui.component.SlidersRow
import com.example.shopapp.vm.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    vm: HomeViewModel = hiltViewModel()
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SlidersRow(vm)
        Spacer(Modifier.height(10.dp))
        CategoriesRow(vm, navController)
        Spacer(Modifier.height(10.dp))
        ProductsView(vm)
    }
}