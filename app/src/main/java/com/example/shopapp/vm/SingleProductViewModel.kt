package com.example.shopapp.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.shopapp.model.products.Product
import com.example.shopapp.model.products.ProductColor
import com.example.shopapp.model.products.ProductSize
import com.example.shopapp.repository.products.ProductRepository
import com.example.shopapp.ui.DataUiState
import com.example.shopapp.vm.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class SingleProductViewModel @Inject constructor(private val productRepository: ProductRepository) :
    BaseViewModel() {

    var product by mutableStateOf<Product?>(null)
        private set
    var selectedColor by mutableStateOf<ProductColor?>(null)

    var selectedSize by mutableStateOf<ProductSize?>(null)


    fun loadProductById(id: Long) {
        loadDate(apiResponse = { productRepository.getProductById(id) }) { productsState: DataUiState<Product> ->
            product = productsState.data?.firstOrNull()
        }
    }

}
