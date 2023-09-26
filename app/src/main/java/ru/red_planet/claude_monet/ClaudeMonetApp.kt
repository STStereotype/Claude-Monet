package ru.red_planet.claude_monet

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ClaudeMonetApp: Application() {

    companion object {
        val DEVELOP: Boolean = true

        var productsJsonAsString: String = ""
        var categoriesJsonAsString: String = ""
        var splashScreenAnimationJsonAsString: String = ""
        var tagsJsonAsString: String = ""
    }

    fun fetchJsonAsString(context: Context, fileName: String): String =
        context.assets.open(fileName).bufferedReader().use { it.readText() }
}
