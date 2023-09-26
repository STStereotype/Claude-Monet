package ru.red_planet.claude_monet.ui.theme.components.topbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.red_planet.claude_monet.R

@Composable
fun TopLine() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = { /* TODO - add click logic*/ }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.filter),
                contentDescription = "filter",
                modifier = Modifier
                    .padding(10.dp)
                    .size(24.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "foodies")
        IconButton(
            onClick = { /* TODO - add click logic*/ }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "search",
                modifier = Modifier
                    .padding(10.dp)
                    .size(24.dp)
            )
        }
    }
}
