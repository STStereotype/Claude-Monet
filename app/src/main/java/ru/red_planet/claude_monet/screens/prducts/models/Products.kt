package ru.red_planet.claude_monet.screens.prducts.models

data class Products(
    val id: Int,
    val category_id: Int,
    val name: String,
    val description: String,
    val image: String,
    val price_current: Int,
    val price_old: Int?,
    val measure: Int,
    val measure_unit: String,
    val energy_per_100_grams: Float,
    val proteins_per_100_grams: Float,
    val fats_per_100_grams: Float,
    val carbohydrates_per_100_grams: Float,
    val tag_ids: List<Int>,
)
