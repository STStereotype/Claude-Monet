package ru.red_planet.claude_monet.screens.tabs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.red_planet.claude_monet.screens.claudeMonet.ClaudeMonetScreen
import ru.red_planet.claude_monet.screens.description.DescriptionScreen
import ru.red_planet.claude_monet.screens.splash.MainBottomScreen

fun NavGraphBuilder.claudeMonetFlow(
    navController: NavController
) {
    navigation(
        route = MainBottomScreen.ClaudeMonet.route,
        startDestination ="main",
    ) {
        composable("main") {
            ClaudeMonetScreen(navController)
        }
        composable("description/{id}") { backStackEntry ->
            val arg = backStackEntry.arguments?.getString("id")
            arg?.let {
                DescriptionScreen(navController, it.toInt())
            }
        }
    }
}
