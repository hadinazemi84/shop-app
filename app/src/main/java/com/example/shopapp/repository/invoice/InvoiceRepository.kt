package com.example.shopapp.repository.invoice

import com.example.shopapp.api.invoices.InvoiceApi
import com.example.shopapp.model.ApiResponse
import com.example.shopapp.model.invoices.Invoice
import com.example.shopapp.repository.base.BaseRepository
import jakarta.inject.Inject

class InvoiceRepository @Inject constructor(private val api: InvoiceApi) : BaseRepository() {
    suspend fun getInvoiceById(id: Long): ApiResponse<Invoice> =
        safeApiCall { api.getInvoiceById(id) }

    suspend fun getInvoicesByUserId(
        userId: Long, pageIndex: Int, pageSize: Int, token: String
    ): ApiResponse<Invoice> = safeApiCall {
        api.getInvoicesByUserId(userId, pageIndex, pageSize, prepareToken(token))
    }

}

