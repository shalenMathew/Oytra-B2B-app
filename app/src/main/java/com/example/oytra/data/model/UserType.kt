package com.example.oytra.data.model

enum class UserType(val customerType: String, val discount: Double) {
    DEALER(customerType = "Dealer", discount = 0.8),
    RETAILER(customerType = "Retailer", discount = 1.0)
}