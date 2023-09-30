package ru.red_planet.claude_monet.ui.theme.components.topbar

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.red_planet.claude_monet.data.remote.common.RemoteListCategory

@Composable
fun Categories(
    idSelectedCategory: Long,
    categories: List<RemoteListCategory>,
    scrollState: ScrollState,
    onSelectCategoryClick: (Long) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp, end = 16.dp, bottom = 16.dp)
            .horizontalScroll(scrollState)
    ) {
        categories.forEach { item ->
            CategoryButton(
                isSelected = item.id == idSelectedCategory,
                title = item.name,
                productId = item.id,
                onSelectCategoryClick = onSelectCategoryClick
            )
        }
    }
}
