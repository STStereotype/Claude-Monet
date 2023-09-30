package ru.red_planet.claude_monet.screens.prducts.views

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ru.red_planet.claude_monet.R
import ru.red_planet.claude_monet.data.local.cart.model.Cart
import ru.red_planet.claude_monet.data.local.cart.model.CartItem
import ru.red_planet.claude_monet.screens.prducts.models.ProductsViewState
import ru.red_planet.claude_monet.ui.theme.ClaudeMonetTheme
import ru.red_planet.claude_monet.ui.theme.components.description.CartButton
import ru.red_planet.claude_monet.ui.theme.components.topbar.Categories
import ru.red_planet.claude_monet.ui.theme.components.topbar.TopLine

@SuppressLint("ResourceType")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductsViewDisplay(
    navController: NavController,
    viewState: ProductsViewState.DisplayProducts,
    onCategoryClick: (Long) -> Unit,
    onProductClick: (Long) -> Unit,
    onFilterClick: () -> Unit,
    onSearchClick: () -> Unit,
    onIncreaseClick: (CartItem) -> Unit,
    onDecreaseClick: (CartItem) -> Unit,
) {
    Scaffold(
        topBar = {
            Column {
                TopLine(
                    onFilterClick = { onFilterClick.invoke() },
                    onSearchClick = { onSearchClick.invoke() }
                )
                Categories(
                    idSelectedCategory = viewState.selectedCategoryId,
                    scrollState = viewState.scrollStateCategory,
                    categories = viewState.categories,
                    onSelectCategoryClick = { categoryId -> onCategoryClick.invoke(categoryId) }
                )
            }
        },
        content = {
            Column {
                if (viewState.products.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                            .background(ClaudeMonetTheme.colors.surface)
                    ) {
                        Text(
                            text = "Пусто, выберите продукт из другой категории :)",
                            style = ClaudeMonetTheme.typography
                                .primaryLight.copy(textAlign = TextAlign.Center),
                            modifier = Modifier
                                .padding(horizontal = 60.dp)
                                .align(Alignment.Center)
                        )
                    }
                } else {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .weight(1f),
                        state = viewState.lazyListStateProducts,
                        cells = GridCells.Fixed(2),
                        contentPadding = PaddingValues(start = 12.dp, end = 12.dp, bottom = 12.dp),
                        content = {
                            val products = viewState.products
                            items(viewState.products.size) { index ->
                                var count = 0u
                                viewState.cart.cart.forEach { item ->
                                    if (item.productId == products[index].productId)
                                        count = item.count
                                }
                                ProductsCardItem(
                                    products[index],
                                    onProductClick = { productId ->
                                        onProductClick.invoke(productId)
                                    },
                                    onIncreaseClick = {
                                        onIncreaseClick.invoke(
                                            CartItem(
                                                productId = products[index].productId,
                                                categoryId = products[index].categoryId,
                                                price = products[index].priceCurrent,
                                                count = count
                                            )
                                        )
                                    },
                                    onDecreaseClick = {
                                        onDecreaseClick.invoke(
                                            CartItem(
                                                productId = products[index].productId,
                                                categoryId = products[index].categoryId,
                                                price = products[index].priceCurrent,
                                                count = count
                                            )
                                        )
                                    },
                                    countProductInCart = count
                                )
                            }
                        })
                }
                if (viewState.cart.cost > 0U) {
                    CartButton(
                        price = viewState.cart.cost,
                        text = "",
                        icon = R.drawable.cart
                    ) { }
                }
            }

        }
    )
}
