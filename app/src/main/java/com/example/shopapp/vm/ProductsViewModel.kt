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
    private var pageIndex = 0
    private val pageSize = 4
    private var endReached = false

    fun loadProducts(categoryId: Long) {
        if (products.isLoading == true || endReached) return
        loadDate({
            productRepository.getProductByCategoryId(
                categoryId, pageIndex, pageSize
            )
        }) { productState ->
            if (!productState.isLoading!!) {
                if (productState.data?.isEmpty() == true) {
                    endReached = true
                } else {
                    pageIndex++
                    val current = products.data?.toMutableList() ?: mutableListOf()
                    current.addAll(productState.data ?: emptyList())
                    products = DataUiState(data = current)
                }
            }
        }
    }
}
