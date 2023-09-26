package ru.red_planet.claude_monet.screens.prducts.views

import android.annotation.SuppressLint
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
) {
    Scaffold(
        topBar = {
            Column {
                TopLine()
                Categories(
                    idSelectedCategory = viewState.selectedCategoryId,
                    scrollState = viewState.scrollStateCategory,
                    categories = viewState.categories,
                ) { categoryId ->
                    onCategoryClick.invoke(categoryId)
                }
            }

        },
        content = {
            if (viewState.products.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
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
                Column {
                    LazyVerticalGrid(
                        modifier = Modifier
                            .weight(1f),
                        state = viewState.lazyListStateProducts,
                        cells = GridCells.Fixed(2),
                        contentPadding =
                        PaddingValues(start = 12.dp, end = 12.dp, bottom = 12.dp),
                        content = {
                            val products = viewState.products
                            items(viewState.products.size) { index ->
                                ProductsCardItem(
                                    products[index],
                                    onClick = { productId ->
                                        onProductClick.invoke(productId)
                                    }
                                )
                            }
                        })
                    CartButton(
                        price = 0,
                        text = "",
                        icon = R.drawable.cart
                    ) { }
                }
            }
        }
    )
}
