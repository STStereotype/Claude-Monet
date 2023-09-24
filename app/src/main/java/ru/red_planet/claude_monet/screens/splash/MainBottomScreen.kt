package ru.red_planet.claude_monet.screens.splash

import androidx.annotation.StringRes
import ru.red_planet.claude_monet.R

sealed class MainBottomScreen(val route: String, @StringRes val resourceId: Int) {
    object ClaudeMonet: MainBottomScreen("claudeMonetFlow", R.string.title_claude_monet)
}