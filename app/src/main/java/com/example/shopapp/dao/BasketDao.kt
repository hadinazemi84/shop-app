package com.example.shopapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.shopapp.model.db.BasketEntity

@Dao
interface BasketDao {
    @Delete
    suspend fun remove(item: BasketEntity)

    @Insert
    suspend fun addItemBasket(itemBasket: BasketEntity)

    @Query("SELECT * FROM basketentity")
    suspend fun getAll(): List<BasketEntity>

    @Query("UPDATE BasketEntity SET quantity = quantity+1 WHERE id = :id")
    suspend fun incrementQuantity(id: Long)

    @Query("SELECT * FROM BasketEntity WHERE productId = :productId AND colorId = :colorId AND sizeId =:sizeId LIMIT 1")
    suspend fun findItemBasket(productId: Long, colorId: Long, sizeId: Long): BasketEntity?

    @Query("UPDATE BasketEntity SET quantity = quantity-1 WHERE id =:id")
    suspend fun decrementQuantity(id: Long)
    @Query("SELECT SUM(price*quantity)FROM basketentity WHERE id=:id")
    fun getTotalPrice(id: Long): Long
}