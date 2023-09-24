@file:OptIn(ExperimentalFoundationApi::class)

package ru.red_planet.claude_monet.screens.claudeMonet

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.red_planet.claude_monet.ui.theme.components.products.ProductCard
import ru.red_planet.claude_monet.ui.theme.components.topbar.Categories
import ru.red_planet.claude_monet.ui.theme.components.topbar.Topline

@Composable
fun ClaudeMonetScreen(
    navController: NavController,
    claudeMonetViewModel: ClaudeMonetViewModel = viewModel()
) {
    val selectedCategory = claudeMonetViewModel.selectedCategory.observeAsState()
    val categories = claudeMonetViewModel.categories.observeAsState()
    val products = claudeMonetViewModel.products.observeAsState().value?.filter {
        it.category_id == selectedCategory.value
    }

    claudeMonetViewModel.addCategories(
        claudeMonetViewModel.readJsonFileCategories(LocalContext.current, "categories.json")
    ) // TODO - we should remove this from here, but where

    claudeMonetViewModel.addProducts(
        claudeMonetViewModel.readJsonFileProducts(LocalContext.current, "products.json")
    ) // TODO - we should remove this from here, but where

    Scaffold(
        topBar = {
            Column {
                Topline()
                categories.value?.let { category ->
                    selectedCategory.value?.let { selCategory ->
                        Categories(
                            idSelectedCategory = selCategory,
                            categories = category,
                            onClick = {
                                claudeMonetViewModel.addSelectedCategory(it)
                            }
                        )
                    }
                }
            }
        },
        content = {
            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                contentPadding = PaddingValues(start = 12.dp, end = 12.dp, bottom = 12.dp),
                content = {
                    products?.let { products ->
                        items(products.size) { index ->
                            val product = products[index]
                            ProductCard(
                                product.id,
                                product.image,
                                product.name,
                                "${product.measure} ${product.measure_unit}",
                                product.price_current,
                                product.price_old
                            ) {
                                navController.navigate("description/${product.id}")
                            }
                        }
                    }
                })
        }
    )
}
