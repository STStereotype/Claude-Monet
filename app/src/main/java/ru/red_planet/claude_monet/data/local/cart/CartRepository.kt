package ru.red_planet.claude_monet.data.local.cart

import ru.red_planet.claude_monet.data.local.cart.model.Cart
import ru.red_planet.claude_monet.data.local.cart.model.CartItem
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val cartStoreImp: CartStoreImp
) {
    fun fetchCart(): Cart = cartStoreImp.fetchCart()

    fun addProduct(cartItem: CartItem) = cartStoreImp.addProduct(cartItem)

    fun deleteProduct(cartItem: CartItem) = cartStoreImp.deleteProduct(cartItem)

    fun increaseCount(cartItem: CartItem) = cartStoreImp.increaseCount(cartItem)

    fun decreaseCount(cartItem: CartItem) = cartStoreImp.decreaseCount(cartItem)
}
