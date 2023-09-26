package ru.red_planet.claude_monet.data.remote.products

import com.google.gson.reflect.TypeToken
import ru.red_planet.claude_monet.ClaudeMonetApp
import ru.red_planet.claude_monet.data.remote.common.FetchJson
import ru.red_planet.claude_monet.data.remote.common.RemoteListProduct
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val productsApi: ProductsApi
) {
    suspend fun fetchProducts(): List<RemoteListProduct> {
        return if (ClaudeMonetApp.DEVELOP) {
            FetchJson<RemoteListProduct>(
                object : TypeToken<List<RemoteListProduct>>() {}.type
            ).fetch(ClaudeMonetApp.productsJsonAsString)
        } else {
            productsApi.fetchProducts()
        }
    }
}
