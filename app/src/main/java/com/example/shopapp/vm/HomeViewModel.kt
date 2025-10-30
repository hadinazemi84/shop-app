package com.example.shopapp.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.shopapp.model.enums.FilterType
import com.example.shopapp.model.products.Product
import com.example.shopapp.model.products.ProductCategory
import com.example.shopapp.model.site.Slider
import com.example.shopapp.repository.products.ProductCategoryRepository
import com.example.shopapp.repository.products.ProductRepository
import com.example.shopapp.repository.site.SliderRepository
import com.example.shopapp.ui.DataUiState
import com.example.shopapp.vm.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sliderRepository: SliderRepository,
    private val categoryRepository: ProductCategoryRepository,
    private val productRepository: ProductRepository
) : BaseViewModel() {

     var selectedFilter by mutableStateOf(FilterType.All.name)
    var sliders by mutableStateOf<DataUiState<Slider>>(DataUiState())
        private set
    var categories by mutableStateOf<DataUiState<ProductCategory>>(DataUiState())
        private set

    var products by mutableStateOf<DataUiState<Product>>(DataUiState())
        private set

    fun loadCategories() = loadDate({ categoryRepository.getCategories() }) { categories = it }
    fun loadSliders() = loadDate({ sliderRepository.getSliders() }) { sliders = it }

    fun loadProductsByFilter(filter: String = selectedFilter) {
        when (filter) {
            FilterType.All.name -> {
                loadDate({ productRepository.getProducts(0, 6) }) { products = it }
            }

            FilterType.Popular.name -> {
                loadDate({ productRepository.getPopularProducts() }) { products = it }
            }

            FilterType.New.name -> {
                loadDate({ productRepository.getNewProducts() }) { products = it }
            }
        }
    }

    init {
        loadSliders()
        loadCategories()
        loadProductsByFilter()
    }


}