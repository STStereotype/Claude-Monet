package ru.red_planet.claude_monet.data.remote.common

import com.google.gson.annotations.SerializedName

data class RemoteListCategory(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String
)
