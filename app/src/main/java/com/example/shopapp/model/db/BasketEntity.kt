package com.example.shopapp.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BasketEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productId: Long,
    val sizeId: Long,
    val colorId: Long,
    val quantity: Int,
    val image: String?,
    val title: String?,
    val price: Long?,
    val size: String?,
    val colorHex: String?
)