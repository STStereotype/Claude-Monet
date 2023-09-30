package ru.red_planet.claude_monet.screens.prducts.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.red_planet.claude_monet.R
import ru.red_planet.claude_monet.screens.prducts.models.ProductsViewState
import ru.red_planet.claude_monet.ui.theme.ClaudeMonetTheme
import ru.red_planet.claude_monet.ui.theme.components.description.CartButton
import ru.red_planet.claude_monet.ui.theme.components.description.Item
import ru.red_planet.claude_monet.ui.theme.components.products.shadow

@Composable
fun ProductDetailsViewDisplay(
    viewState: ProductsViewState.DisplayProductDetails,
    onBackClick: () -> Unit,
    onButtonCartClick: (Long, Long, Int) -> Unit
) {

    val product = viewState.product

    Column {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            item {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.product),
                        contentDescription = "name",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    IconButton(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp)
                            .shadow(
                                color = Color(0x201F1F1F),
                                offsetY = 10.dp
                            )
                            .background(
                                color = ClaudeMonetTheme.colors.surface,
                                shape = CircleShape,
                            ),
                        onClick = {
                            onBackClick.invoke()
                        }) {
                        Icon(
                            modifier = Modifier
                                .padding(10.dp),
                            painter = painterResource(id = R.drawable.ic24_arrow_left),
                            contentDescription = "back"
                        )
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                ) {
                    Text(
                        text = product.name,
                        style = ClaudeMonetTheme.typography.descriptionTitle
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 8.dp),
                        text = product.description,
                        style = ClaudeMonetTheme.typography.primaryLight
                    )
                }
            }
            item {
                Item(
                    name = "Вес",
                    value = "${product.measure} ${product.measureUnit}",
                    width = 105.dp
                )
            }
            item {
                Item(
                    name = "Энерг. ценность",
                    value = "${product.energy} ккал",
                    width = 242.dp
                )
            }
            item {
                Item(
                    name = "Белки",
                    value = "${product.proteins} г",
                    width = 115.dp
                )
            }
            item {
                Item(
                    name = "Жиры",
                    value = "${product.fats} г",
                    width = 118.dp
                )
            }
            item {
                Item(
                    name = "Углеводы",
                    value = "${product.carbohydrates} г",
                    width = 155.dp
                )
            }
        }
        CartButton(
            viewState.product.priceCurrent.toUInt(),
            text = "В корзина за",
            icon = null,
        ) {
            onButtonCartClick.invoke(
                viewState.product.productId,
                viewState.product.categoryId,
                viewState.product.priceCurrent
            )
        }
    }
}
