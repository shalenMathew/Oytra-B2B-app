package com.example.oytra.ui.screens.productScreen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oytra.data.model.Products
import com.example.oytra.data.model.ProductsItem
import com.example.oytra.data.model.UserType
import com.example.oytra.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewModel @Inject constructor(
    val productsRepository: ProductsRepository,
    val savedStateHandle: SavedStateHandle): ViewModel() {

    val userTypeArg: String = savedStateHandle.get<String>("userType") ?: UserType.RETAILER.name
    val userType = try {
        UserType.valueOf(userTypeArg)
    } catch (e: Exception) {
        UserType.RETAILER
    }

    var isLoading: Boolean by mutableStateOf(false)
        private set
    var productList: List<ProductsItem> by mutableStateOf(emptyList())
        private  set

    init {
        Log.d("testing","userType - $userType")
        Log.d("testing","userTypeArg - $userTypeArg")

        fetchProducts()
    }



    fun fetchProducts(){

        viewModelScope.launch {

            isLoading = true

            try {

                val response = productsRepository.getProductsList()


              productList =  response.map { items->
                    items.copy(price = items.price * userType.discount)
                }


            }catch (e: Exception){
                Log.d("testing", e.message.toString())
            }finally {
                isLoading=false
            }

        }


    }


}