package ru.red_planet.claude_monet.ui.theme.components.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.red_planet.claude_monet.ui.theme.ClaudeMonetTheme

@Composable
fun CategoryButton(
    isSelected: Boolean,
    title: String,
    productId: Int,
    onClick: (Int) -> Unit
) {
    val colorBackground =
        if (isSelected) ClaudeMonetTheme.colors.primary
        else ClaudeMonetTheme.colors.primary.copy(alpha = 0f)
    val textStyle =
        if (isSelected) ClaudeMonetTheme.typography.primaryLight
        else ClaudeMonetTheme.typography.primaryDark

    Box(
        modifier = Modifier
            .padding(start = 8.dp)
            .background(
                color = colorBackground,
                shape = ClaudeMonetTheme.shapes.button
            )
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable {
                onClick.invoke(productId)
            }
    ) {
        Text(
            text = title,
            style = textStyle,
        )
    }
}