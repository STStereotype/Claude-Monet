package ru.red_planet.claude_monet.screens.prducts.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.red_planet.claude_monet.R
import ru.red_planet.claude_monet.data.remote.common.RemoteListProduct
import ru.red_planet.claude_monet.ui.theme.ClaudeMonetTheme
import ru.red_planet.claude_monet.ui.theme.MainTheme
import ru.red_planet.claude_monet.ui.theme.components.products.shadow

@Composable
fun ProductsCardItem(
    product: RemoteListProduct,
    onClick: (Long) -> Unit
) {
    Card(
        shape = ClaudeMonetTheme.shapes.button,
        backgroundColor = ClaudeMonetTheme.colors.grayBackground,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = 0.dp,
    ) {
        Column {
            Box {
                Image(
                    painter = painterResource(id = R.drawable.product),
                    contentDescription = product.name,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                if (product.priceOld != null) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_sale),
                        contentDescription = "Sale",
                        modifier = Modifier
                            .padding(start = 8.dp, top = 8.dp)
                            .size(24.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Text(
                    text = product.name,
                    style = ClaudeMonetTheme.typography.secondaryDark,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (product.measure != 0) {
                    Text(
                        text = "${product.measure} г",
                        style = ClaudeMonetTheme.typography.secondaryLight
                    )
                }

                Button(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                        .shadow(
                            color = Color(0x201F1F1F),
                            offsetY = 10.dp
                        ),
                    contentPadding = PaddingValues(
                        horizontal = 16.dp, vertical = 12.dp
                    ),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ClaudeMonetTheme.colors.surface),
                    shape = ClaudeMonetTheme.shapes.button,
                    elevation = null,
                    onClick = {
                        onClick.invoke(product.productId)
                    }
                ) {
                    Text(
                        text = "${product.priceCurrent} ₽",
                        style = ClaudeMonetTheme.typography.primaryDark
                    )
                    if (product.priceOld != null) {
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp),
                            text = "${product.priceOld} ₽",
                            style = ClaudeMonetTheme.typography.oldPrice
                        )
                    }
                }
            }

        }
    }
}

private val product = RemoteListProduct(
    productId = 1,
    name = "Name name",
    measure = 500,
    image = "1.jpg",
    priceCurrent = 10000,
    priceOld = 15000,
    categoryId = 0,
    description = "",
    measureUnit = "",
    energy = 1f,
    proteins = 1f,
    fats = 1f,
    carbohydrates = 1f,
    tagIds = listOf()
    )

@Preview
@Composable
fun ProductsViewCardOneItem_Preview() {
    MainTheme {
        ProductsCardItem(
            product,
            onClick = { }
        )

    }
}

@Preview
@Composable
fun ProductsViewCardTwoItem_Preview() {
    MainTheme {
        Row {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                ProductsCardItem(
                    product,
                    onClick = { }
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                ProductsCardItem(
                    product,
                    onClick = { }
                )
            }
        }
    }
}
