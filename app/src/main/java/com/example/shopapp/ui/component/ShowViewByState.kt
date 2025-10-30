package com.example.shopapp.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.shopapp.ui.DataUiState

@Composable
fun <T> ShowViewByState(
    state: DataUiState<T>,
    loadingContent: @Composable () -> Unit = { Loading() },
    errorContent: @Composable () -> Unit = { Text(state.error ?: "Error") },
    successContent: @Composable (List<T>) -> Unit
) {
    when {
        state.isLoading == true -> loadingContent()
        state.data != null -> successContent(state.data)
        state.error != null -> errorContent()
    }
}