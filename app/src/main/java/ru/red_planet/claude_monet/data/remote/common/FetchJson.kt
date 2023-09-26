package ru.red_planet.claude_monet.data.remote.common

import com.google.gson.Gson
import kotlinx.coroutines.delay
import java.lang.reflect.Type

class FetchJson<T>(
    val typeToken: Type
) {
    suspend fun fetch(jsonAsString: String): List<T> {
        delay(500)
        return Gson().fromJson(jsonAsString, typeToken)
    }
}