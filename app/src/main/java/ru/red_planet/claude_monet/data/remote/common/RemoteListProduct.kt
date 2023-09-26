package ru.red_planet.claude_monet.data.remote.common

import com.google.gson.annotations.SerializedName

data class RemoteListProduct(
    @SerializedName("id")
    val productId: Long,

    @SerializedName("category_id")
    val categoryId: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("price_current")
    val priceCurrent: Int,

    @SerializedName("price_old")
    val priceOld: Int?,

    @SerializedName("measure")
    val measure: Int,

    @SerializedName("measure_unit")
    val measureUnit: String,

    @SerializedName("energy_per_100_grams")
    val energy: Float,

    @SerializedName("proteins_per_100_grams")
    val proteins: Float,

    @SerializedName("fats_per_100_grams")
    val fats: Float,

    @SerializedName("carbohydrates_per_100_grams")
    val carbohydrates: Float,

    @SerializedName("tag_ids")
    val tagIds: List<Int>,
)
