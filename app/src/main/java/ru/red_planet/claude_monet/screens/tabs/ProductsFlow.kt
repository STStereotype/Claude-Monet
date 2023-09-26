package ru.red_planet.claude_monet.screens.tabs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.red_planet.claude_monet.screens.prducts.ProductsScreen
import ru.red_planet.claude_monet.screens.prducts.ProductsViewModel
import ru.red_planet.claude_monet.screens.splash.MainBottomScreen

fun NavGraphBuilder.productsFlow(
    navController: NavController
) {
    navigation(
        route = MainBottomScreen.ClaudeMonet.route,
        startDestination ="main",
    ) {
        composable("main") {
            val productsViewModel = hiltViewModel<ProductsViewModel>()
            ProductsScreen(navController, productsViewModel)
        }
    }
}
