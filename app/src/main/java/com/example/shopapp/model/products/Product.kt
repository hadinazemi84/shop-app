package com.example.shopapp.model.products

data class Product(
    var id: Long?,
    var title: String?,
    var image: String?,
    var description: String?,
    var price: Long?,
    var addDate: String?,
    var visitCount: Int?,
    var category: ProductCategory? = null,
    var colors: List<ProductColor>? = emptyList(),
    var sizes: List<ProductSize>? = emptyList(),
    var lang: String?
)