package ru.red_planet.claude_monet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.red_planet.claude_monet.screens.main.MainScreen
import ru.red_planet.claude_monet.screens.splash.SplashScreen
import ru.red_planet.claude_monet.ui.theme.MainTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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

