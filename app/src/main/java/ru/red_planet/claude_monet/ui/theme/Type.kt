package ru.red_planet.claude_monet.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

val Typography = ClaudeMonetTypography(
    primaryDark = TextStyle(
        color = BaseLightPalette.onSurfaceHighEmphasis,
        textAlign = TextAlign.Center,
        fontFamily = Roboto,
        fontSize = 16.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.W500,
        lineHeight = 1.15.em
    ),
    primaryLight = TextStyle(
        color = BaseLightPalette.onSurfaceMediumEmphasis,
        fontFamily = Roboto,
        fontSize = 16.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.W500,
        lineHeight = 1.15.em
    ),
    primaryWhite = TextStyle(
        color = BaseLightPalette.surface,
        textAlign = TextAlign.Center,
        fontFamily = Roboto,
        fontSize = 16.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.W500,
        lineHeight = 1.15.em
    ),
    secondaryDark = TextStyle(
        color = BaseLightPalette.onSurfaceHighEmphasis,
        fontFamily = Roboto,
        fontSize = 14.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.W400,
        lineHeight = 1.64.em
    ),
    secondaryLight = TextStyle(
        color = BaseLightPalette.onSurfaceMediumEmphasis,
        fontFamily = Roboto,
        fontSize = 14.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.W400,
        lineHeight = 1.64.em
    ),
    oldPrice = TextStyle(
        color = BaseLightPalette.onSurfaceMediumEmphasis,
        textAlign = TextAlign.Center,
        fontFamily = Roboto,
        fontSize = 14.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.W400,
        lineHeight = 1.31.em,
        textDecoration = TextDecoration.LineThrough
    ),
    descriptionTitle = TextStyle(
        color = BaseLightPalette.onSurfaceHighEmphasis,
        fontFamily = Roboto,
        fontSize = 34.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.W400,
        lineHeight = 1.21.em,
    ),
    screenTitle = TextStyle(
        color = BaseLightPalette.onSurfaceHighEmphasis,
        fontFamily = Inter,
        fontSize = 18.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.W600,
        lineHeight = 1.43.em,
        textDecoration = TextDecoration.LineThrough
    ),
    dialogTitle = TextStyle(
        color = BaseLightPalette.onSurfaceHighEmphasis,
        fontFamily = Roboto,
        fontSize = 20.sp,
        fontStyle = FontStyle.Normal,
        fontWeight = FontWeight.W500,
        lineHeight = 1.38.em,
        letterSpacing = 0.015.em,
    ),
)
