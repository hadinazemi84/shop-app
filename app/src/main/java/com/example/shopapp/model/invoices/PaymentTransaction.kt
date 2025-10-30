package com.example.shopapp.model.invoices

import com.example.shopapp.model.customer.UserDto

data class PaymentTransaction(
    var invoiceItems: List<InvoiceItem>? = emptyList(),
    var userDto: UserDto? = null,
)