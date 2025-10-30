package com.example.shopapp.repository.customer

import com.example.shopapp.api.customer.UserApi
import com.example.shopapp.model.ApiResponse
import com.example.shopapp.model.customer.User
import com.example.shopapp.model.customer.UserDto
import com.example.shopapp.repository.base.BaseRepository
import jakarta.inject.Inject

class UserRepository @Inject constructor(private val api: UserApi) : BaseRepository() {
    suspend fun getUserInfo(token: String): ApiResponse<User> =
        safeApiCall { api.getUserInfo(prepareToken(token)) }

    suspend fun changePassword(
        user: UserDto, token: String
    ): ApiResponse<User> = safeApiCall { api.changePassword(user, prepareToken(token)) }

    suspend fun login(user: UserDto): ApiResponse<UserDto> = safeApiCall { api.login(user) }

    suspend fun register(user: UserDto): ApiResponse<User> = safeApiCall { api.register(user) }

    suspend fun update(user: UserDto, token: String): ApiResponse<User> =
        safeApiCall { api.update(user, prepareToken(token)) }
}