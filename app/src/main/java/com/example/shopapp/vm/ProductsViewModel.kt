package com.example.shopapp.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.shopapp.model.products.Product
import com.example.shopapp.repository.products.ProductRepository
import com.example.shopapp.ui.DataUiState
import com.example.shopapp.vm.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(private val productRepository: ProductRepository) :
    BaseViewModel() {
    var products by mutableStateOf<DataUiState<Product>>(DataUiState())
        private set
    private var pageIndex = 0
    private val pageSize = 5
    private var endReached = false

    fun loadProducts(categoryId: Long) {
        if (products.isLoading || endReached) return

        loadDate(apiResponse = {
            productRepository.getProductsByCategoryId(categoryId, pageIndex, pageSize)
        }, stateSetter = { productsState: DataUiState<Product> ->
            if (productsState.isLoading) {
                products = products.copy(isLoading = true)
            } else {
                if (!productsState.data.isNullOrEmpty()) {
                    pageIndex++
                    val current = products.data?.toMutableList() ?: mutableListOf()
                    current.addAll(productsState.data)
                    products = products.copy(data = current, isLoading = false)
                } else {
                    endReached = true
                }
            }

        })


    }
}
