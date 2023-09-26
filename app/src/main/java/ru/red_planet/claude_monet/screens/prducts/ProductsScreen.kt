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
                productsViewModel
                    .obtainEvent(ProductsEvent
                        .OnCategoryClick(categoryId = categoryId))
            },
            onProductClick = { productsViewModel
                .obtainEvent(ProductsEvent.OnProductClick(it))
            }
        )
        is ProductsViewState.DisplayProductDetails -> ProductDetailsViewDisplay(
            viewState = state,
            onBackClick = {productsViewModel
                .obtainEvent(ProductsEvent.EnterScreen)},
            onButtonCartClick = { }
        )
    }

    LaunchedEffect(key1 = viewState, block = {
        productsViewModel.updateScrollState(productsViewModel.scrollStateCategory.value!!)
        productsViewModel.updateLazyListState(productsViewModel.lazyListStateProducts.value!!)
        productsViewModel.obtainEvent(event = ProductsEvent.EnterScreen)
    })
}
