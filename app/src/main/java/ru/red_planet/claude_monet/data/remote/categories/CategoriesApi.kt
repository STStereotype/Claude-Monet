package ru.red_planet.claude_monet.data.remote.categories

import retrofit2.http.GET
import ru.red_planet.claude_monet.data.remote.common.RemoteListCategory

interface CategoriesApi {
    @GET("./fetchCategories")
    fun fetchCategories(): List<RemoteListCategory>
}
