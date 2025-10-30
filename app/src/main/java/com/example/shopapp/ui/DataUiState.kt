package com.example.shopapp.ui

data class DataUiState<T>(
    val data: List<T>? = null,
    val isLoading: Boolean? = false,
    val error: String? = null
)