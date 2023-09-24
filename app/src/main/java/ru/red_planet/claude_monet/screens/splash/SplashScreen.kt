package ru.red_planet.claude_monet.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.navOptions
import ru.red_planet.claude_monet.ui.theme.ClaudeMonetTheme

@Composable
fun SplashScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ClaudeMonetTheme.colors.primary)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Foodies",
                style = ClaudeMonetTheme.typography.descriptionTitle,
                color = ClaudeMonetTheme.colors.surface,
                textAlign = TextAlign.Center
            )
        }
    }

    LaunchedEffect(key1 = Unit, block = {
//        delay(3000)
        navController.navigate("main", navOptions {
            popUpTo("splash") {
                inclusive = true
            }
        })
    })
}
