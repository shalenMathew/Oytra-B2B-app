package com.example.oytra.data.repository

import com.example.oytra.data.OytraApiService
import com.example.oytra.data.model.Products
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    val apiService: OytraApiService
) {


    suspend fun getProductsList(): Products{

        return apiService.getProductList()
    }

}