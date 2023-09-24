package ru.red_planet.claude_monet.screens.description

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.red_planet.claude_monet.screens.claudeMonet.models.Products

class DescriptionViewModel: ViewModel() {

    private val _product: MutableLiveData<Products> = MutableLiveData()
    val product: LiveData<Products> = _product

    fun searchProduct(id: Int, context: Context) {
        val products = readJsonFileProducts(context, "products.json")
        _product.postValue( products.firstOrNull() { product ->
            product.id == id
        })
    }

    private fun readJsonFileProducts(const: Context, fileName: String): List<Products> {
        val jsonString = const.assets.open(fileName).bufferedReader().use { it.readText() }
        return Json.decodeFromString(jsonString)
    }
}