package ru.red_planet.claude_monet.ui.theme.components.products

import android.graphics.BlurMaskFilter
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.red_planet.claude_monet.R
import ru.red_planet.claude_monet.ui.theme.ClaudeMonetTheme

@Composable
fun ProductCard(
    id: Int,
    image: String,
    name: String,
    weight: String,
    price: Int,
    oldPrice: Int?,
    onClick: (Int) -> Unit
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
                    contentDescription = name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                )
                if (oldPrice != null) {
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
                    text = name,
                    style = ClaudeMonetTheme.typography.secondaryDark,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                if (weight != "0") {
                    Text(
                        text = weight,
                        style = ClaudeMonetTheme.typography.secondaryLight
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth()
                        .shadow(
                            color = Color(0x201F1F1F),
                            offsetY = 10.dp
                        )
                        .background(
                            color = ClaudeMonetTheme.colors.surface,
                            shape = ClaudeMonetTheme.shapes.button
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                        .clickable { onClick.invoke(id) }
                ) {
                    Text(
                        text = "$price ₽",
                        style = ClaudeMonetTheme.typography.primaryDark
                    )
                    if (oldPrice != null) {
                        Text(
                            modifier = Modifier
                                .padding(start = 8.dp),
                            text = "$oldPrice ₽",
                            style = ClaudeMonetTheme.typography.oldPrice
                        )
                    }
                }
            }

        }
    }
}

fun Modifier.shadow(
    color: Color = Color.Black,
    borderRadius: Dp = 0.dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    spread: Dp = 0.dp
) = this.then(
    Modifier.drawBehind {
        this.drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.toPx()
            val leftPixel = (0f - spreadPixel) + offsetX.toPx()
            val topPixel = (0f - spreadPixel) + offsetY.toPx()
            val rightPixel = this.size.width + spreadPixel
            val bottomPixel = this.size.height + spreadPixel

            frameworkPaint.maskFilter =
                (BlurMaskFilter(10.dp.toPx(), BlurMaskFilter.Blur.NORMAL))

            frameworkPaint.color = color.toArgb()
            it.drawRoundRect(
                left = leftPixel,
                top = topPixel,
                right = rightPixel,
                bottom = bottomPixel,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint
            )
        }
    }
)
