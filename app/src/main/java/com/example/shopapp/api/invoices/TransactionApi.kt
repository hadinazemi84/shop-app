package com.example.shopapp.api.invoices

import com.example.shopapp.model.ApiResponse
import com.example.shopapp.model.invoices.PaymentTransaction
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionApi {
    @POST("trx/gotoPayment")
    suspend fun goToPayment(@Body data: PaymentTransaction): ApiResponse<String>
}