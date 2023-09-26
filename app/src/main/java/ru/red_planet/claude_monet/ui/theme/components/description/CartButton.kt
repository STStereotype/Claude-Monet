package ru.red_planet.claude_monet.ui.theme.components.description

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.red_planet.claude_monet.ui.theme.ClaudeMonetTheme
import ru.red_planet.claude_monet.ui.theme.components.products.shadow

@Composable
fun CartButton(
    price: Int,
    text: String,
    @StringRes icon: Int?,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .shadow(
                color = Color(0x201F1F1F),
                offsetY = (-4).dp
            )
            .background(ClaudeMonetTheme.colors.surface)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Button(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    color = Color(0x201F1F1F),
                    offsetY = 10.dp
                ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ClaudeMonetTheme.colors.primary),
            elevation = null,
            shape = ClaudeMonetTheme.shapes.button,
            onClick = { onClick.invoke() }
        ) {
            icon?.let {
                painterResource(id = it) }?.let {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    painter = it,
                    tint = ClaudeMonetTheme.colors.surface,
                    contentDescription = "'icon cart'")
            }
            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                text = "$text $price",
                style = ClaudeMonetTheme.typography.primaryWhite
            )
        }
    }
}
