package ru.red_planet.claude_monet.screens.prducts.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.red_planet.claude_monet.ui.theme.ClaudeMonetTheme
import ru.red_planet.claude_monet.ui.theme.MainTheme

@Composable
fun ProductsViewNoItems() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ClaudeMonetTheme.colors.surface)
    ) {
        Text(
            text = "Пусто, выберите продукт из другой категории :)",
            style = ClaudeMonetTheme.typography.primaryLight.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .padding(horizontal = 60.dp)
                .align(Alignment.Center))
    }
}

@Preview
@Composable
fun ProductsViewNoItems_Preview() {
    MainTheme {
        ProductsViewNoItems()
    }
}
