package com.example.shopapp.api.customer

import com.example.shopapp.model.ApiResponse
import com.example.shopapp.model.customer.User
import com.example.shopapp.model.customer.UserDto
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface UserApi {
    @PUT("user/changePassword")
    suspend fun getUserInfo(
        @Header("Authorization") token: String
    ): ApiResponse<User>

    @PUT("user/changePassword")
    suspend fun changePassword(
        @Body user: UserDto, @Header("Authorization") token: String
    ): ApiResponse<User>


    @POST("user/login")
    suspend fun login(@Body user: UserDto): ApiResponse<UserDto>

    @POST("user/register")
    suspend fun register(@Body user: UserDto): ApiResponse<User>

    @PUT("user/update")
    suspend fun update(@Body user: UserDto,@Header("Authorization") token: String): ApiResponse<User>
}