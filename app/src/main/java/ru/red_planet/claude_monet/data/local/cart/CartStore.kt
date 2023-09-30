package ru.red_planet.claude_monet.data.local.cart

import android.util.Log
import ru.red_planet.claude_monet.data.local.cart.model.Cart
import ru.red_planet.claude_monet.data.local.cart.model.CartItem

interface CartStore {

    fun fetchCart(): Cart

    fun addProduct(cartItem: CartItem)

    fun deleteProduct(cartItem: CartItem)

    fun increaseCount(cartItem: CartItem)

    fun decreaseCount(cartItem: CartItem)
}

class CartStoreImp: CartStore {

    private val cart: ArrayList<CartItem> = arrayListOf()
    private var cost: UInt = 0U

    override fun fetchCart(): Cart = Cart(cart, cost)

    override fun addProduct(cartItem: CartItem) {
        cart.add(cartItem)
        cost += cartItem.price.toUInt()
    }

    override fun deleteProduct(cartItem: CartItem) {
        cart.remove(cartItem)
    }

    override fun increaseCount(cartItem: CartItem) {
        if (cartItem.count == 99u) return
        val tempCart = cartItem.copy(count = cartItem.count + 1u)
        deleteProduct(cartItem)
        addProduct(tempCart)
    }

    override fun decreaseCount(cartItem: CartItem) {
        cost -= cartItem.price.toUInt()
        if (cartItem.count == 1U) {
            deleteProduct(cartItem)
            return
        }
        val tempCart = cartItem.copy(count = cartItem.count - 1U)
        deleteProduct(cartItem)
        cart.add(tempCart)
    }
}
