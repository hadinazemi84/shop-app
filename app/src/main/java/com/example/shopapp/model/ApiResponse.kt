package com.example.shopapp.model

data class ApiResponse<T>(
    val data: List<T>? = emptyList(),
    val message: String? = null,
    val status: String? = null,
    val totalCount: Int? = null
)