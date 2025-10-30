package com.example.shopapp.model.invoices

import com.example.shopapp.model.customer.User
import com.example.shopapp.model.products.Product


data class Invoice(
    var id: Long?,
    var addDate: String?,
    var items: List<InvoiceItem>? = emptyList(),
    var paymentDate: String?,
    var status: String?,
    var user: User? = null,
)