package com.example.shopapp.model.customer

import androidx.room.Entity

@Entity
data class User(
    var id: Long?,
    var username: String?,
    var password: String?,
    val customer: Customer? = null
)