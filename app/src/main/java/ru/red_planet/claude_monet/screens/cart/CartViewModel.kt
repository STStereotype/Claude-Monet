package ru.red_planet.claude_monet.screens.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel: ViewModel() {
    private val _idProductsForPurchase: MutableLiveData<List<Int>> = MutableLiveData(listOf())
    val idProductsForPurchase: LiveData<List<Int>> = _idProductsForPurchase

    fun addItemToCart(id: Int) {
        val tempList: ArrayList<Int> = arrayListOf()
        _idProductsForPurchase.value?.forEach {
            tempList.add(it)
        }
        tempList.add(id)
        _idProductsForPurchase.postValue(tempList)
    }
}
