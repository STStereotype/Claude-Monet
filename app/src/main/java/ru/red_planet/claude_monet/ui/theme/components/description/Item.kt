package ru.red_planet.claude_monet.ui.theme.components.description

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.red_planet.claude_monet.ui.theme.ClaudeMonetTheme

@Composable
fun Item(
    name: String,
    value: String,
    width: Dp
) {
    Column {
        Spacer(
            modifier = Modifier
                .background(ClaudeMonetTheme.colors.outline)
                .height(1.dp)
                .width(width)
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 13.dp)
        ) {
            Text(
                text = name,
                style = ClaudeMonetTheme.typography.primaryLight)
            Text(
                modifier = Modifier
                    .padding(start = 8.dp),
                text = value,
                    style = ClaudeMonetTheme.typography.primaryDark)
        }
    }
}
