package ru.red_planet.claude_monet.screens.prducts.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.red_planet.claude_monet.ui.theme.ClaudeMonetTheme
import ru.red_planet.claude_monet.ui.theme.MainTheme

@Composable
fun ProductsViewLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ClaudeMonetTheme.colors.surface)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = ClaudeMonetTheme.colors.primary
        )
    }
}

@Preview
@Composable
fun ProductsViewLoading_Preview() {
    MainTheme {
        ProductsViewLoading()
    }
}
