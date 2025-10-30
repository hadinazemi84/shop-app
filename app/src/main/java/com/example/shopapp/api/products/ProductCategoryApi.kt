package com.example.shopapp.api.products

import com.example.shopapp.model.ApiResponse
import com.example.shopapp.model.products.ProductCategory
import retrofit2.http.GET


interface ProductCategoryApi {

    @GET("productCategory")
    suspend fun getCategories(): ApiResponse<ProductCategory>


}