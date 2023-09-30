package ru.red_planet.claude_monet.screens.prducts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import ru.red_planet.claude_monet.screens.prducts.models.ProductsEvent
import ru.red_planet.claude_monet.screens.prducts.models.ProductsViewState
import ru.red_planet.claude_monet.screens.prducts.views.ProductDetailsViewDisplay
import ru.red_planet.claude_monet.screens.prducts.views.ProductsViewDisplay
import ru.red_planet.claude_monet.screens.prducts.views.ProductsViewLoading
import ru.red_planet.claude_monet.screens.prducts.views.ProductsViewSearch

@Composable
fun ProductsScreen(
    navController: NavController,
    productsViewModel: ProductsViewModel,
) {
    val viewState = productsViewModel.productsViewState.observeAsState()

    when (val state = viewState.value) {
        ProductsViewState.Loading -> ProductsViewLoading()
        is ProductsViewState.DisplayProducts -> ProductsViewDisplay(
            navController = navController,
            viewState = state,
            onCategoryClick = { categoryId ->
                productsViewModel.obtainEvent(ProductsEvent.OnCategoryClick(categoryId))
            },
            onProductClick = { productId ->
                productsViewModel.obtainEvent(ProductsEvent.OnProductClick(productId))
            },
            onFilterClick = { /* TODO - add click logic */ },
            onSearchClick = { productsViewModel.obtainEvent(ProductsEvent.OnSearchClick) },
            onIncreaseClick = {
                productsViewModel.increaseCount(it)
                productsViewModel.obtainEvent(ProductsEvent.EnterProductsDisplay)
            },
            onDecreaseClick = {
                productsViewModel.decreaseCount(it)
                productsViewModel.obtainEvent(ProductsEvent.EnterProductsDisplay)
            }
        )

        is ProductsViewState.DisplayProductDetails -> ProductDetailsViewDisplay(
            viewState = state,
            onBackClick = { productsViewModel.obtainEvent(ProductsEvent.EnterProductsDisplay) },
            onButtonCartClick = { productId, categoryId, price ->
                productsViewModel.addProductToCart(productId, categoryId, price)
                productsViewModel.obtainEvent(ProductsEvent.EnterProductsDisplay)
            }
        )

        is ProductsViewState.DisplaySearchProducts -> ProductsViewSearch(
            viewState = state,
            onProductClick = { productId ->
                productsViewModel.obtainEvent(ProductsEvent.OnProductClick(productId))
            },
            onBackClick = { productsViewModel.obtainEvent(ProductsEvent.EnterProductsDisplay) }
        )

        else -> {}
    }

    LaunchedEffect(key1 = viewState, block = {
        productsViewModel.updateScrollState(productsViewModel.scrollStateCategory.value!!)
        productsViewModel.updateLazyListState(productsViewModel.lazyListStateProducts.value!!)
        productsViewModel.obtainEvent(event = ProductsEvent.EnterProductsDisplay)
    })
}
