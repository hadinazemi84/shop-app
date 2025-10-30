package com.example.shopapp.api.products

import com.example.shopapp.model.ApiResponse
import com.example.shopapp.model.products.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("product")
    suspend fun getProducts(
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int
    ): ApiResponse<Product>

    @GET("product/{id}")
    suspend fun getProductById(@Path("id") id: Long): ApiResponse<Product>

    @GET("product/cat/{id}")
    suspend fun getProductByCategoryId(
        @Path("id") id: Long,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int
    ): ApiResponse<Product>

    @GET("product/new")
    suspend fun getNewProducts(): ApiResponse<Product>

    @GET("product/popular")
    suspend fun getPopularProducts(): ApiResponse<Product>

}