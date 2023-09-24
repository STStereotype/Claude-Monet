package ru.red_planet.claude_monet.ui.theme.components.topbar

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.red_planet.claude_monet.screens.data.features.topBar.models.Categories
import ru.red_planet.claude_monet.ui.theme.ClaudeMonetTheme

@Composable
fun Categories(
    idSelectedCategory: Int,
    categories: List<Categories>,
    onClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp, end = 16.dp, bottom = 16.dp)
            .horizontalScroll(ScrollState(0))
    ) {
        categories.forEach { item ->
            CategoryButton(
                isSelected = item.id == idSelectedCategory,
                title = item.name,
                productId = item.id,
                onClick = onClick
            )
        }
    }
}
