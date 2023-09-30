package ru.red_planet.claude_monet.data.local.cart.model

data class Cart(
    val cart: List<CartItem>,
    val cost: UInt = 0U,
)

data class CartItem(
    val categoryId: Long,
    val productId: Long,
    val price: Int,
    val count: UInt = 0u
)
