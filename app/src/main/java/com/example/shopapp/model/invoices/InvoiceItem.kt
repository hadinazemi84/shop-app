package com.example.shopapp.model.invoices

import com.example.shopapp.model.products.Product

data class InvoiceItem(
    var id: Long?,
    var product: Product? = null,
    var quantity: Int?,
    var unitPrice: Long?
)
