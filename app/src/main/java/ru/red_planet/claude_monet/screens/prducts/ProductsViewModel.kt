package ru.red_planet.claude_monet.screens.prducts

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.red_planet.claude_monet.base.Event
import ru.red_planet.claude_monet.data.local.cart.CartRepository
import ru.red_planet.claude_monet.data.local.cart.model.Cart
import ru.red_planet.claude_monet.data.local.cart.model.CartItem
import ru.red_planet.claude_monet.data.remote.categories.CategoriesRepository
import ru.red_planet.claude_monet.data.remote.common.RemoteListCategory
import ru.red_planet.claude_monet.data.remote.common.RemoteListProduct
import ru.red_planet.claude_monet.data.remote.products.ProductsRepository
import ru.red_planet.claude_monet.screens.prducts.models.ProductsEvent
import ru.red_planet.claude_monet.screens.prducts.models.ProductsViewState
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    private val categoriesRepository: CategoriesRepository,
    private val cartRepository: CartRepository
): ViewModel(), Event<ProductsEvent> {

    private lateinit var _categories: List<RemoteListCategory>
    private val selectedCategoryId: MutableLiveData<Long> = MutableLiveData(-1)
    private lateinit var _products: List<RemoteListProduct>
    private lateinit var _displayedProducts: List<RemoteListProduct>

    /**
     * Stores state about the current screen state
     */
    private val _productsViewState: MutableLiveData<ProductsViewState> =
        MutableLiveData(ProductsViewState.Loading)
    val productsViewState: LiveData<ProductsViewState> = _productsViewState

    /**
     * Saves the state of the current category list scroll position.
     */
    private val _scrollStateCategory: MutableLiveData<ScrollState> =
        MutableLiveData(ScrollState(0))
    val scrollStateCategory: LiveData<ScrollState> = _scrollStateCategory

    /**
     * Stores state about the current scroll position of a product list.
     */
    private val _lazyListStateProducts: MutableLiveData<LazyListState> =
        MutableLiveData(LazyListState(0))
    val lazyListStateProducts: LiveData<LazyListState> = _lazyListStateProducts

    /**
     * Router function. Processes incoming events for each state
     * and sends them to the corresponding reduce.
     * @param {event: ProductsEvent} - Event that happened.
     * @param {ProductsViewState} - Sealed class with possible states.
     */
    override fun obtainEvent(event: ProductsEvent) {
        when (val state = _productsViewState.value) {
            is ProductsViewState.Loading -> reduce(event, state)
            is ProductsViewState.DisplayProducts -> reduce(event, state)
            is ProductsViewState.DisplayProductDetails -> reduce(event, state)
            is ProductsViewState.DisplaySearchProducts -> reduce(event, state)
            else -> throw NotImplementedError("Unexpected daily state")
        }
    }

    /**
     * Reduce for ProductsViewState.Loading
     */
    private fun reduce(
        event: ProductsEvent,
        currentState: ProductsViewState.Loading
    ) {
        when (event) {
            ProductsEvent.EnterProductsDisplay -> fetchProductsDisplay()
            else -> throw NotImplementedError(
                "Unexpected event: reduce for state " +
                        "ProductsViewState.Loading has no event $event"
            )
        }
    }

    /**
     * Reduce for ProductsViewState.DisplayProducts
     */
    private fun reduce(
        event: ProductsEvent,
        currentState: ProductsViewState.DisplayProducts
    ) {
        //Log.e("CheckState", _cart.value!!.cost)
        when (event) {
            ProductsEvent.EnterProductsDisplay -> fetchProductsDisplay()
            ProductsEvent.OnSearchClick -> fetchSearchProducts()
            is ProductsEvent.OnCategoryClick -> performCategoryClick(event.categoryId)
            is ProductsEvent.OnFilterClick -> {}
            is ProductsEvent.OnProductClick -> fetchProductDetails(event.productId)
            is ProductsEvent.OnButtonCartClick -> {}
            else -> throw NotImplementedError(
                "Unexpected event: reduce for state " +
                        "ProductsViewState.DisplayProducts has no event $event"
            )
        }
    }

    /**
     * Reduce for ProductsViewState.DisplayProductDetails
     */
    private fun reduce(
        event: ProductsEvent,
        currentState: ProductsViewState.DisplayProductDetails
    ) {
        when (event) {
            ProductsEvent.EnterProductsDisplay -> fetchProductsDisplay()
            is ProductsEvent.OnButtonCartClick -> {}
            else -> throw NotImplementedError(
                "Unexpected event: reduce for state " +
                        "ProductsViewState.DisplayProductDetails has no event $event"
            )
        }
    }

    /**
     * Reduce for ProductsViewState.DisplaySearchProducts
     */
    private fun reduce(
        event: ProductsEvent,
        currentState: ProductsViewState.DisplaySearchProducts
    ) {
        Log.e("CheckState", event.toString())
        when (event) {
            ProductsEvent.EnterProductsDisplay -> fetchProductsDisplay()
            is ProductsEvent.OnProductClick -> {}

            is ProductsEvent.OnButtonCartClick -> {}
            else -> throw NotImplementedError(
                "Unexpected event: reduce for state " +
                        "ProductsViewState.DisplaySearchProducts has no event $event"
            )
        }
    }

    private fun performCategoryClick(categoryId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                fetchProductsDisplay(
                    categoryId = categoryId,
                    resetLazyListStateProducts = true
                )
            }
        }
    }

    private fun performSearchClick() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {

            }
        }
    }

    private fun fetchProductsDisplay(
        categoryId: Long? = null,
        resetLazyListStateProducts: Boolean = false
    ) {
        viewModelScope.launch {
            if (_productsViewState.value == ProductsViewState.Loading) {
                _categories = categoriesRepository.fetchCategories()
                _products = productsRepository.fetchProducts()
                selectedCategoryId.value = _categories[0].id
            }

            if (_products.isEmpty()) {
                return@launch
            } else {
                if (categoryId != null)
                    selectedCategoryId.value = categoryId

                _displayedProducts = _products.filter { product ->
                    product.categoryId == selectedCategoryId.value
                }
                _productsViewState.postValue(
                    ProductsViewState.DisplayProducts(
                        categories = _categories,
                        selectedCategoryId = selectedCategoryId.value!!,
                        scrollStateCategory = scrollStateCategory.value!!,
                        lazyListStateProducts = lazyListStateProducts.value!!,
                        products = _displayedProducts,
                        cart = cartRepository.fetchCart()
                    )
                )
                if (resetLazyListStateProducts) _lazyListStateProducts.value?.scrollToItem(0)
            }
        }
    }

    private fun fetchProductDetails(productId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _displayedProducts.forEach { product ->
                    if (product.productId == productId) {
                        _productsViewState.postValue(
                            ProductsViewState.DisplayProductDetails(product)
                        )
                        return@forEach
                    }
                }
            }
        }
    }

    private fun fetchSearchProducts() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                ProductsViewState.DisplaySearchProducts(listOf())
            }
        }
    }

    /**
     * Category list scroll state update function
     */
    fun updateScrollState(newState: ScrollState) {
        _scrollStateCategory.postValue(newState)
    }

    /**
     * Product List Scroll status update function
     */
    fun updateLazyListState(newState: LazyListState) {
        _lazyListStateProducts.postValue(newState)
    }

    fun addProductToCart(productId: Long, categoryId: Long, price: Int) {
        cartRepository.addProduct(
            CartItem(
                categoryId = categoryId,
                productId = productId,
                price = price,
                count = 1U
            )
        )
    }

    fun increaseCount(cartItem: CartItem) {
        cartRepository.increaseCount(cartItem)
    }

    fun decreaseCount(cartItem: CartItem) {
        cartRepository.decreaseCount(cartItem)
    }
}
