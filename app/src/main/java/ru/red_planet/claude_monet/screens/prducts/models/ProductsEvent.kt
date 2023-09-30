package ru.red_planet.claude_monet.screens.prducts.models

sealed class ProductsEvent {
    object EnterProductsDisplay: ProductsEvent()
    object OnSearchClick: ProductsEvent()
    data class OnCategoryClick(val categoryId: Long): ProductsEvent()
    data class OnFilterClick(val filters: List<Filter>): ProductsEvent()
    data class OnProductClick(val productId: Long) : ProductsEvent()
    data class OnButtonCartClick(val products: List<Products>): ProductsEvent()
}

data class Filter(
    val name: String,
    val value: Boolean
)
