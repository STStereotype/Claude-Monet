package ru.red_planet.claude_monet.screens.data.features.topBar.models

import kotlinx.serialization.Serializable

@Serializable
data class Categories(
    val id: Int,
    val name: String,
)

