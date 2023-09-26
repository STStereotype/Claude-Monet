package ru.red_planet.claude_monet.screens.prducts.models

import ru.red_planet.claude_monet.data.remote.common.RemoteListProduct

sealed class ProductsEvent {
    object EnterScreen: ProductsEvent()
    data class EnterDetailsScreen(val product: RemoteListProduct): ProductsEvent()
    data class OnCategoryClick(val categoryId: Long): ProductsEvent()
    data class OnFilterClick(val filterId: Long, val filterSelected: Boolean): ProductsEvent()
    data class OnProductClick(val productId: Long) : ProductsEvent()
    data class OnButtonCartClick(val products: List<Products>): ProductsEvent()
}
