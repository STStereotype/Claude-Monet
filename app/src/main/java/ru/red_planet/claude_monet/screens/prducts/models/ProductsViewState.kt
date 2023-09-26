package ru.red_planet.claude_monet.screens.prducts.models

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import ru.red_planet.claude_monet.data.remote.common.RemoteListCategory
import ru.red_planet.claude_monet.data.remote.common.RemoteListProduct

sealed class ProductsViewState {
    object Loading: ProductsViewState()
    data class DisplayProducts(
        val categories: List<RemoteListCategory>,
        val selectedCategoryId: Long,
        val scrollStateCategory: ScrollState,
        val lazyListStateProducts: LazyListState,
        val products: List<RemoteListProduct>
    ): ProductsViewState()
    data class DisplayProductDetails(
        val product: RemoteListProduct
    ): ProductsViewState()
}
