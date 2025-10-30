package com.example.shopapp.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shopapp.ui.component.TopNavbar


@Composable
fun OnlineShopApp() {
    val navController = rememberNavController()
    val isFullScreen by remember { mutableStateOf(checkFullScreen(navController)) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { if (!isFullScreen) TopNavbar() }) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
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
            }
        }

    }
}

fun checkFullScreen(navController: NavHostController): Boolean {
    val fullScreenRouts = listOf<String>()
    val currentRoute = navController.currentBackStackEntry?.destination?.route ?: "home"
    return currentRoute in fullScreenRouts
}