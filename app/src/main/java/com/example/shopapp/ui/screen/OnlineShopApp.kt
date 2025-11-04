package com.example.shopapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shopapp.ui.component.TopNavbar


@Composable
fun OnlineShopApp() {
    val navController = rememberNavController()
    val isFullScreen = checkFullScreen(navController)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { if (!isFullScreen) TopNavbar() }) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(if (!isFullScreen) innerPadding else PaddingValues(0.dp))
        ) {
            NavHost(navController, startDestination = "home") {
                composable("home") { HomeScreen(navController) }
                composable(
                    route = "products/{id}/{title}",
                    arguments = listOf(
                        navArgument("id") { type = NavType.LongType },
                        navArgument("title") { type = NavType.StringType })
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getLong("id") ?: 0L
                    val title = backStackEntry.arguments?.getString("title") ?: "Title"
                    ProductsScreen(id, title, navController)
                }
                composable("product/{id}",
                    listOf(navArgument("id") {
                    type = NavType.LongType
                })) { backStackEntry ->
                    val productId = backStackEntry.arguments?.getLong("id") ?: 0L
                    SingleProductScreen(productId, innerPadding, navController)
                }
            }
        }

    }
}

@Composable
fun checkFullScreen(navController: NavHostController): Boolean {
    val fullScreenRouts = listOf("product")
    val currentRoute =
        navController.currentBackStackEntryAsState().value?.destination?.route ?: "home"
    return fullScreenRouts.any { currentRoute.startsWith(it) }
}