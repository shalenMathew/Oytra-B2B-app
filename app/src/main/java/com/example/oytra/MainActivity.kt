package com.example.oytra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.oytra.ui.navigation.AppNavigation
import com.example.oytra.ui.navigation.Screen
import com.example.oytra.ui.theme.OytraTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OytraTheme {

                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                var selectedUserType by rememberSaveable { mutableStateOf<String?>(null) }


                navBackStackEntry?.arguments?.getString("userType")?.let {
                    selectedUserType = it
                }

                val showBottomBar = currentRoute != null && currentRoute != Screen.UserSelectionScreen.route

                Scaffold(modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (showBottomBar) {
                            NavigationBar {
                                val items = listOf(Screen.Products, Screen.Scanner)
                                items.forEach { screen ->
                                    val baseRoute = screen.route.split("/")[0]
                                    val isSelected = currentRoute?.startsWith(baseRoute) == true

                                    NavigationBarItem(
                                        icon = {
                                            screen.icon?.let {
                                                Icon(
                                                    it,
                                                    contentDescription = screen.title
                                                )
                                            }
                                        },
                                        label = { screen.title?.let { Text(it) } },
                                        selected = isSelected,
                                        onClick = {
                                            val routeToNavigate = if (screen == Screen.Products) {
                                                "products/${selectedUserType ?: "RETAILER"}"
                                            } else {
                                                screen.route
                                            }

                                            if (currentRoute != routeToNavigate) {
                                                navController.navigate(routeToNavigate) {
                                                    popUpTo(navController.graph.findStartDestination().id) {
                                                        saveState = true
                                                    }
                                                    launchSingleTop = true
                                                    restoreState = true
                                                }
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }) { innerPadding ->

                    AppNavigation(navHost = navController, paddingValues = innerPadding)

                }
            }
        }
    }
}
