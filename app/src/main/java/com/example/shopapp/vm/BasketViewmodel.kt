package com.example.shopapp.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopapp.model.db.BasketEntity
import com.example.shopapp.model.products.Product
import com.example.shopapp.model.products.ProductColor
import com.example.shopapp.model.products.ProductSize
import com.example.shopapp.repository.BasketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class BasketViewmodel @Inject constructor(private val repository: BasketRepository) : ViewModel() {
    private val _basketItems = MutableLiveData<List<BasketEntity>>(emptyList())
    val basketItems: LiveData<List<BasketEntity>> = _basketItems

    private fun getBasketItems() {
        viewModelScope.launch() {
            _basketItems.postValue(repository.getBasketItems())

        }

    }

    fun incrementQuantity(item: BasketEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) { repository.incrementQuantity(item) }
            getBasketItems()
        }
    }

    fun deleteItemBasket(item: BasketEntity) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteItem(item)
            }
            getBasketItems()
        }
    }

    fun decrementQuantity(item: BasketEntity) {
        if (item.quantity == 1) return
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.decrementQuantity(item)
            }
            getBasketItems()
        }

    }

    fun addToBasket(product: Product?, color: ProductColor?, size: ProductSize?) {
        viewModelScope.launch(Dispatchers.IO) {
            val oldItem = repository.findItemBasket(
                productId = product?.id!!, colorId = color?.id!!, sizeId = size?.id!!
            )
            if (oldItem == null) {
                repository.addItemBasket(
                    itemBasket = BasketEntity(
                        productId = product.id!!,
                        colorId = color.id!!,
                        sizeId = size.id!!,
                        title = product.title,
                        colorHex = color.hexValue,
                        image = product.image,
                        quantity = 1,
                        size = size.title,
                        price = product.price
                    )
                )
                getBasketItems()
            } else {
                repository.incrementQuantity(oldItem)
                getBasketItems()
            }


        }
    }


    init {
        getBasketItems()
    }


}