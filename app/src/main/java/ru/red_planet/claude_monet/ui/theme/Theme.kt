package ru.red_planet.claude_monet.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color

@Composable
fun MainTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) BaseLightPalette else BaseLightPalette
    val typography = Typography
    val shape = Shapes

    CompositionLocalProvider (
        LocalClaudeMonetColors provides colors,
        LocalClaudeMonetTypography provides typography,
        LocalClaudeMonetShape provides shape,
        content = content
    )
}
