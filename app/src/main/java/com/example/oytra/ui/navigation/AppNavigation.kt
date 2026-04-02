package com.example.oytra.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.oytra.ui.screens.ScannerScreen.ScannerScreen
import com.example.oytra.ui.screens.productScreen.ProductListScreen
import com.example.oytra.ui.screens.userSelectionScreen.UserSelectionScreen

@Composable
fun AppNavigation(
    navHost:NavHostController,
    paddingValues: PaddingValues
){

    NavHost(navController = navHost, startDestination = Screen.UserSelectionScreen.route ){

        composable(Screen.UserSelectionScreen.route){
            UserSelectionScreen(userTypeSelected = { userType->
                navHost.navigate("products/$userType")
            })
        }

        composable(Screen.Products.route){

            ProductListScreen(paddingValues = paddingValues)

        }

        composable(Screen.Scanner.route) {
            ScannerScreen(paddingValues = paddingValues)
        }

    }


}