package ru.red_planet.claude_monet.ui.theme.components.topbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.red_planet.claude_monet.ui.theme.ClaudeMonetTheme

@Composable
fun CategoryButton(
    isSelected: Boolean,
    title: String,
    productId: Long,
    onClick: (Long) -> Unit
) {
    val colorBackground =
        if (isSelected) ClaudeMonetTheme.colors.primary
        else ClaudeMonetTheme.colors.whiteBackground.copy(alpha = 0f)
    val textStyle =
        if (isSelected) ClaudeMonetTheme.typography.primaryWhite
        else ClaudeMonetTheme.typography.primaryDark

    Button(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        elevation = null,
        modifier = Modifier
            .padding(start = 8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorBackground),
        shape = ClaudeMonetTheme.shapes.button,
        onClick = {
            onClick.invoke(productId)
        }
    ) {
        Text(
            text = title,
            style = textStyle,
        )
    }
}
