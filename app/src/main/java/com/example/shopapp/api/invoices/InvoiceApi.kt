package com.example.shopapp.api.invoices

import com.example.shopapp.model.ApiResponse
import com.example.shopapp.model.invoices.Invoice
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface InvoiceApi {
    @GET("invoice/{id}")
    suspend fun getInvoiceById(@Path("id") id: Long): ApiResponse<Invoice>

    @GET("invoice/{userId}")
    suspend fun getInvoicesByUserId(
        @Path("userId") userId: Long,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int,
        @Header("Authorization") token: String
    ): ApiResponse<Invoice>

}