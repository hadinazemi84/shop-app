package com.example.shopapp.repository

import com.example.shopapp.dao.BasketDao
import com.example.shopapp.model.db.BasketEntity
import jakarta.inject.Inject

class BasketRepository @Inject constructor(private val dao: BasketDao) {
    suspend fun findItemBasket(productId: Long, colorId: Long, sizeId: Long): BasketEntity? {
        return dao.findItemBasket(productId, colorId, sizeId)
    }

    suspend fun addItemBasket(itemBasket: BasketEntity) = dao.addItemBasket(itemBasket)
    suspend fun incrementQuantity(oldItem: BasketEntity) = dao.incrementQuantity(oldItem.id)
    suspend fun decrementQuantity(oldItem: BasketEntity) = dao.decrementQuantity(oldItem.id)
    suspend fun getBasketItems(): List<BasketEntity> = dao.getAll()
    suspend fun deleteItem(item: BasketEntity) = dao.remove(item)
    suspend fun getTotalPrice(id: Long): Long {
       return dao.getTotalPrice(id)
    }


}
