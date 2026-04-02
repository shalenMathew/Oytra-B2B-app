package com.example.oytra.data

import com.example.oytra.data.model.Products
import retrofit2.http.GET

interface OytraApiService {

@GET("shalenMathew/831599f73356594a960ae1ca60c949b9/raw/products.json")
suspend fun getProductList(): Products

}