package com.example.shopapp.repository.invoice

import com.example.shopapp.api.invoices.TransactionApi
import com.example.shopapp.model.ApiResponse
import com.example.shopapp.model.invoices.PaymentTransaction
import com.example.shopapp.repository.base.BaseRepository
import jakarta.inject.Inject
import retrofit2.http.Body

class TransactionRepository @Inject constructor(private val api: TransactionApi) :
    BaseRepository() {
    suspend fun goToPayment(data: PaymentTransaction): ApiResponse<String> =
        safeApiCall { api.goToPayment(data) }
}