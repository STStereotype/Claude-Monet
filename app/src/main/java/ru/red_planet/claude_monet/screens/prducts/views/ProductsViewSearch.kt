package ru.red_planet.claude_monet.screens.prducts.views

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.red_planet.claude_monet.R
import ru.red_planet.claude_monet.screens.prducts.models.ProductsViewState
import ru.red_planet.claude_monet.ui.theme.ClaudeMonetTheme
import ru.red_planet.claude_monet.ui.theme.components.products.shadow

@SuppressLint("ResourceType")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductsViewSearch(
    viewState: ProductsViewState.DisplaySearchProducts,
    onProductClick: (Long) -> Unit,
    onBackClick: () -> Unit
) {
    var text = remember { mutableStateOf("") }

    Column {
        Row(
            modifier = Modifier
                .shadow(
                    color = Color(0x401F1F1F),
                    offsetY = 20.dp
                )
                .background(ClaudeMonetTheme.colors.surface)
                .padding(horizontal = 16.dp, vertical = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                modifier = Modifier
                    .size(24.dp),
                onClick = {
                    onBackClick.invoke()
                }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "back",
                    modifier = Modifier
                        .size(24.dp)
                )
            }
            BasicTextField(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
                value = text.value,
                onValueChange = { text.value = it },
                textStyle = ClaudeMonetTheme.typography.primaryDark.copy(
                    textAlign = TextAlign.Start
                ),
                maxLines = 1,
                decorationBox = { innerTextField ->
                    Box() {
                        if (text.value.isEmpty()) {
                            Text(
                                text = "Найти блюдо",
                                style = ClaudeMonetTheme.typography.primaryLight
                            )
                        }
                    }
                    innerTextField()
                })
        }
        LazyVerticalGrid(
            modifier = Modifier
                .weight(1f),
            state = rememberLazyListState(),
            cells = GridCells.Fixed(2),
            contentPadding =
            PaddingValues(start = 12.dp, end = 12.dp, bottom = 12.dp),
            content = {
                val products = viewState.products
                items(viewState.products.size) { index ->
                    /*ProductsCardItem(
                        products[index],
                        CartItem(),
                        onAddClick = { productId ->
                            onProductClick.invoke(productId)
                        },
                        onDecreaseClick = {},
                        onIncreaseClick = {}
                    )*/
                }
            })
    }
}
