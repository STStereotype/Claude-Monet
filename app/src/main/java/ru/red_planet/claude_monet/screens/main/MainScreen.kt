package ru.red_planet.claude_monet.screens.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.red_planet.claude_monet.screens.splash.MainBottomScreen
import ru.red_planet.claude_monet.screens.tabs.claudeMonetFlow

@Composable
fun MainScreen(
    navController: NavController
) {
    val childNavController = rememberNavController()

    NavHost(
        navController = childNavController,
        startDestination = MainBottomScreen.ClaudeMonet.route
    ) {
        claudeMonetFlow(childNavController)
    }
}
