package ru.red_planet.claude_monet.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ru.red_planet.claude_monet.R

data class ClaudeMonetColors(
    val primary: Color,
    val dark: Color,
    val outline: Color,
    val surface: Color,
    val onSurfaceMediumEmphasis: Color,
    val onSurfaceHighEmphasis: Color,
    val whiteBackground: Color,
    val grayBackground: Color,
)

data class ClaudeMonetTypography(
    val primaryDark: TextStyle,
    val primaryLight: TextStyle,
    val secondaryDark: TextStyle,
    val secondaryLight: TextStyle,
    val oldPrice: TextStyle,
    val descriptionTitle: TextStyle,
    val screenTitle: TextStyle,
    val dialogTitle: TextStyle,
)

data class ClaudeMonetShape(
    val button: Shape,
    val dialog: Shape,
)

object ClaudeMonetTheme {
    val colors: ClaudeMonetColors
        @Composable
        get() = LocalClaudeMonetColors.current

    val typography: ClaudeMonetTypography
        @Composable
        get() = LocalClaudeMonetTypography.current

    val shapes: ClaudeMonetShape
        @Composable
        get() = LocalClaudeMonetShape.current
}

val Roboto = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium)
)

val Inter = FontFamily(
    Font(R.font.inter_semibold, FontWeight.SemiBold)
)

val LocalClaudeMonetColors = staticCompositionLocalOf<ClaudeMonetColors> {
    error("No colors provided")
}

val LocalClaudeMonetTypography = staticCompositionLocalOf<ClaudeMonetTypography> {
    error("No font provided")
}

val LocalClaudeMonetShape = staticCompositionLocalOf<ClaudeMonetShape> {
    error("No shapes provided")
}
