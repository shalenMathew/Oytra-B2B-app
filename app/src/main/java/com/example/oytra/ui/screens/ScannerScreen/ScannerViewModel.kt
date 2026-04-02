package com.example.oytra.ui.screens.ScannerScreen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScannerViewModel @Inject constructor(): ViewModel() {

    fun evaluateBarcode(result: String): ScannerResult{

        val dummyNames = listOf("Engine Oil X1", "Hydraulic Filter", "Brake Pad Set", "Coolant 5L", "Spark Plug Platinum")

        val isNumeric = result.all { it.isDigit() }

        if (!isNumeric) return ScannerResult(status = "Invalid (Contains Characters)", isValid = false, barcode = result)

        val lastDigit = result.last().digitToInt()

        return if (lastDigit % 2 == 0) {
            ScannerResult(
                productName = dummyNames.random(),
                status = "Valid Product",
                isValid = true,
                barcode = result
            )
        } else {
            ScannerResult(status = "Invalid (Odd Number Barcode)", isValid = false, barcode = result)
        }

    }

}

data class ScannerResult(
    var barcode: String,
    var productName: String="unknown",
    var status: String,
    var isValid: Boolean
)