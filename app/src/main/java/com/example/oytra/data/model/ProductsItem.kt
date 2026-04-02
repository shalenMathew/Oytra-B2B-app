package com.example.oytra.data.model

import com.google.gson.annotations.SerializedName

data class ProductsItem(
    val id: Int,
    val name: String,
    @SerializedName("basePrice") var price: Double,
    val moq: Int,
    val imageUrl: String
)