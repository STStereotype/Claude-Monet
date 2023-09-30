package ru.red_planet.claude_monet.ui.theme.components.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.red_planet.claude_monet.R
import ru.red_planet.claude_monet.ui.theme.ClaudeMonetTheme
import ru.red_planet.claude_monet.ui.theme.MainTheme

@Composable
fun IncDecButtonCard(
    modifier: Modifier = Modifier,
    count: UInt,
    onIncreaseClick: () -> Unit,
    onDecreaseClick: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = Modifier
                .shadow(
                    color = Color(0x201F1F1F),
                    offsetY = 10.dp
                )
                .background(
                    color = ClaudeMonetTheme.colors.surface,
                    shape = ClaudeMonetTheme.shapes.button
                ),
            onClick = { onDecreaseClick.invoke() }
        ) {
            Image(
                painter = painterResource(id = R.drawable.minus),
                contentDescription = "Increase",
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
            )
        }
        Text(
            modifier = Modifier
                .weight(1f),
            text = count.toString(),
            style = ClaudeMonetTheme.typography.primaryDark
        )
        IconButton(
            modifier = Modifier
                .shadow(
                    color = Color(0x201F1F1F),
                    offsetY = 10.dp
                )
                .background(
                    color = ClaudeMonetTheme.colors.surface,
                    shape = ClaudeMonetTheme.shapes.button
                ),
            onClick = { onIncreaseClick.invoke() }
        ) {
            Image(
                painter = painterResource(id = R.drawable.plus),
                contentDescription = "Decrease",
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp)
            )
        }
    }
}


@Preview
@Composable
fun IncDecButtonCard_Preview() {
    MainTheme {
        IncDecButtonCard(
            count = 99U,
            onIncreaseClick = {},
            onDecreaseClick = {}
        )
    }
}
