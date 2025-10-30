package com.example.shopapp.repository.base

import com.example.shopapp.model.ApiResponse

open class BaseRepository {

    suspend fun <T> safeApiCall(apiCall: suspend () -> ApiResponse<T>): ApiResponse<T> {
        return try {
            apiCall()
        } catch (e: Exception) {
            ApiResponse(status = "Exception", message = e.message)
        }
    }

    fun prepareToken(token: String): String {
        var result = token
        if (token.lowercase().startsWith("bearer")) {
            result
        } else {
            result = "Bearer $token"
        }
        return result
    }
}