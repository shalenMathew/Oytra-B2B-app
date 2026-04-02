package com.example.oytra.ui.screens.productScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun ProductListScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    viewModel: ProductViewModel = hiltViewModel()
) {
    val products = viewModel.productList
    val isLoading = viewModel.isLoading
    val context = LocalContext.current

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(paddingValues)
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        if (products.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(products){ product->
                        ProductListItem(
                            product = product,
                            onOrderClick = { finalQuantity ->
                                val totalPrice = finalQuantity * product.price


                                Toast.makeText(
                                    context,
                                    "Order Placed! Total Price: ₹$totalPrice",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        )
                }
            }
        }
    }
}