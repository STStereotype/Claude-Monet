package ru.red_planet.claude_monet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.red_planet.claude_monet.screens.main.MainScreen
import ru.red_planet.claude_monet.screens.splash.SplashScreen
import ru.red_planet.claude_monet.ui.theme.MainTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            ClaudeMonetApp.productsJsonAsString = ClaudeMonetApp().fetchJsonAsString(this, "products.json")
            ClaudeMonetApp.categoriesJsonAsString = ClaudeMonetApp().fetchJsonAsString(this, "categories.json")
            ClaudeMonetApp.splashScreenAnimationJsonAsString = ClaudeMonetApp().fetchJsonAsString(this, "splash_screen_animation.json")
            ClaudeMonetApp.tagsJsonAsString = ClaudeMonetApp().fetchJsonAsString(this, "tags.json")

            MainTheme {
                val navController = rememberNavController()
                Surface {
                    NavHost(
                        navController = navController,
                        startDestination ="splash"
                    ) {
                        composable("splash") {
                            SplashScreen(navController)
                        }
                        composable("main") {
                            MainScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
