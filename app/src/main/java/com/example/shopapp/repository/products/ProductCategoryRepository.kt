package com.example.shopapp.repository.products

import com.example.shopapp.api.products.ProductCategoryApi
import com.example.shopapp.model.ApiResponse
import com.example.shopapp.model.products.ProductCategory
import com.example.shopapp.repository.base.BaseRepository
import jakarta.inject.Inject

class ProductCategoryRepository @Inject constructor(private val api: ProductCategoryApi) :
    BaseRepository() {

    suspend fun getCategories(): ApiResponse<ProductCategory> = safeApiCall { api.getCategories() }
}