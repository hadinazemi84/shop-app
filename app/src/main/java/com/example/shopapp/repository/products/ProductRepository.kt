package com.example.shopapp.repository.products

import com.example.shopapp.api.products.ProductApi
import com.example.shopapp.model.ApiResponse
import com.example.shopapp.model.products.Product
import com.example.shopapp.repository.base.BaseRepository
import jakarta.inject.Inject

class ProductRepository @Inject constructor(private val api: ProductApi) : BaseRepository() {

    suspend fun getProducts(pageIndex: Int, pageSize: Int): ApiResponse<Product> = safeApiCall {
        api.getProducts(pageIndex, pageSize)
    }

    suspend fun getProductById(id: Long): ApiResponse<Product> =
        safeApiCall { api.getProductById(id) }


    suspend fun getProductByCategoryId(
        id: Long, pageIndex: Int, pageSize: Int
    ): ApiResponse<Product> = safeApiCall {
        api.getProductByCategoryId(id, pageIndex, pageSize)
    }

    suspend fun getNewProducts(): ApiResponse<Product> = safeApiCall {
        api.getNewProducts()
    }

    suspend fun getPopularProducts(): ApiResponse<Product> = safeApiCall {
        api.getPopularProducts()
    }


}