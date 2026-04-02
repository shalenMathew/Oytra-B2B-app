package com.example.oytra.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector


sealed class Screen(val route: String, val title: String?, val icon: ImageVector?) {
    object Products : Screen("products/{userType}", "Products", Icons.Default.List)
    object Scanner : Screen("scanner", "Scanner", Icons.Default.DateRange)
    object UserSelectionScreen: Screen("user_select", title = null,icon = null)
}