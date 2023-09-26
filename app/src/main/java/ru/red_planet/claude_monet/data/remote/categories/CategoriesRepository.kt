package ru.red_planet.claude_monet.data.remote.categories

import com.google.gson.reflect.TypeToken
import ru.red_planet.claude_monet.ClaudeMonetApp
import ru.red_planet.claude_monet.data.remote.common.FetchJson
import ru.red_planet.claude_monet.data.remote.common.RemoteListCategory
import javax.inject.Inject

class CategoriesRepository @Inject constructor(
    private val categoriesApi: CategoriesApi
) {
    suspend fun fetchCategories(): List<RemoteListCategory> {
        return if (ClaudeMonetApp.DEVELOP) {
            FetchJson<RemoteListCategory>(
                object : TypeToken<List<RemoteListCategory>>() {}.type
            ).fetch(ClaudeMonetApp.categoriesJsonAsString)
        } else {
            categoriesApi.fetchCategories()
        }
    }
}