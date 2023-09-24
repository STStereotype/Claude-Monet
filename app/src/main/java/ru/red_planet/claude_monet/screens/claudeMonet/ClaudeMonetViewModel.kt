package ru.red_planet.claude_monet.screens.claudeMonet

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.red_planet.claude_monet.screens.claudeMonet.models.Products
import ru.red_planet.claude_monet.screens.data.features.topBar.models.Categories

class ClaudeMonetViewModel: ViewModel() {
    private val _categories: MutableLiveData<List<Categories>> = MutableLiveData()
    val categories: LiveData<List<Categories>> = _categories

    private val _products: MutableLiveData<List<Products>> = MutableLiveData()
    val products: LiveData<List<Products>> = _products

    private val _selectedCategory: MutableLiveData<Int> = MutableLiveData(676153)
    val selectedCategory: LiveData<Int> = _selectedCategory

    fun addCategories(category: List<Categories>) {
        _categories.postValue(category)
    }

    fun addProducts(product: List<Products>) {
        _products.postValue(product)
    }

    fun addSelectedCategory(idCategory: Int) {
        _selectedCategory.postValue(idCategory)
    }

    fun readJsonFileCategories(const: Context, fileName: String): List<Categories> {
        val jsonString = const.assets.open(fileName).bufferedReader().use { it.readText() }
        return Json.decodeFromString(jsonString)
    }

    fun readJsonFileProducts(const: Context, fileName: String): List<Products> {
        val jsonString = const.assets.open(fileName).bufferedReader().use { it.readText() }
        return Json.decodeFromString(jsonString)
    }
}
