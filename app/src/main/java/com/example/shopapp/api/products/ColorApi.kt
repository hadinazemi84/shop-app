package com.example.shopapp.api.products

import com.example.shopapp.model.ApiResponse
import com.example.shopapp.model.products.ProductColor
import retrofit2.http.GET

interface ColorApi {
    @GET("color")
    suspend fun getColors(): ApiResponse<ProductColor>
}