package ru.red_planet.claude_monet.data.remote.products

import retrofit2.http.GET
import ru.red_planet.claude_monet.data.remote.common.RemoteListProduct

interface ProductsApi {
    @GET("./fetchProducts")
    fun fetchProducts(): List<RemoteListProduct>
}
