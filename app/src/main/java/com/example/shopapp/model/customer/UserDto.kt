package com.example.shopapp.model.customer

data class UserDto(
    var id: Long?,
    var username: String?,
    var firstName: String?,
    var lastName: String?,
    var address: String?,
    var password: String?,
    var oldPassword: String?,
    var repeatPassword: String?,
    var postalCode: String?,
    var phone: String?,
    var customerId: Long?,
    var token: String?
)