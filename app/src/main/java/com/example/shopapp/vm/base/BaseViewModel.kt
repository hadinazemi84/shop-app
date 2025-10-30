package com.example.shopapp.vm.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.model.ApiResponse
import com.example.shopapp.ui.DataUiState
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel() {
    protected fun <T> loadDate(
        apiResponse: suspend () -> ApiResponse<T>, stateSetter: (DataUiState<T>) -> Unit
    ) {
        stateSetter(DataUiState(isLoading = true))
        viewModelScope.launch {
            try {
                val response = apiResponse()
                if (response.status != "OK") {
                    throw Exception("${response.message}")
                }
                stateSetter(DataUiState(response.data))
            } catch (e: Exception) {
                stateSetter(DataUiState(error = "${e.message}"))
            }
        }
    }
}