package ru.red_planet.claude_monet.screens.prducts

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
    private val categoriesRepository: CategoriesRepository
): ViewModel(), Event<ProductsEvent> {

    private lateinit var _categories: List<RemoteListCategory>
    private val selectedCategoryId: MutableLiveData<Long> = MutableLiveData(-1)
    private lateinit var _products: List<RemoteListProduct>
    private lateinit var _displayedProducts: List<RemoteListProduct>

    private val _productsViewState: MutableLiveData<ProductsViewState> =
        MutableLiveData(ProductsViewState.Loading)
    val productsViewState: LiveData<ProductsViewState> =
        _productsViewState

    private val _scrollStateCategory: MutableLiveData<ScrollState> =
        MutableLiveData(ScrollState(0))
    val scrollStateCategory: LiveData<ScrollState> =
        _scrollStateCategory

    private val _lazyListStateProducts: MutableLiveData<LazyListState> =
        MutableLiveData(LazyListState(0))
    val lazyListStateProducts: LiveData<LazyListState> =
        _lazyListStateProducts

    override fun obtainEvent(event: ProductsEvent) {
        when (val currentState = _productsViewState.value) {
            is ProductsViewState.Loading -> reduce(event, currentState)
            is ProductsViewState.DisplayProducts -> reduce(event, currentState)
            is ProductsViewState.DisplayProductDetails -> reduce(event, currentState)
        }
    }

    private fun reduce(
        event: ProductsEvent,
        currentState: ProductsViewState.Loading
    ) {
        when (event) {
            ProductsEvent.EnterScreen -> fetchProductsForDate()
            else -> {}
        }
    }

    private fun reduce(
        event: ProductsEvent,
        currentState: ProductsViewState.DisplayProducts
    ) {
        when (event) {
            ProductsEvent.EnterScreen -> fetchProductsForDate()
            is ProductsEvent.OnCategoryClick -> performCategoryClick(
                categoryId = event.categoryId
            )
            is ProductsEvent.OnFilterClick -> { }
            is ProductsEvent.OnProductClick -> performProductClick(event.productId)
            is ProductsEvent.OnButtonCartClick -> { }
        }
    }

    private fun reduce(
        event: ProductsEvent,
        currentState: ProductsViewState.DisplayProductDetails
    ) {
        when (event) {
            ProductsEvent.EnterScreen -> fetchProductsForDate()
            is ProductsEvent.EnterDetailsScreen -> fetchProductDetailsForDate(event.product)
            else -> {}
        }
    }

    private fun performCategoryClick(categoryId: Long) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                fetchProductsForDate(categoryId = categoryId)
                _lazyListStateProducts.value?.scrollToItem(0)
            }
        }
    }

    private fun performProductClick(productId: Long) {
        _displayedProducts.forEach { product ->
            if (product.productId == productId) {
                _productsViewState.postValue(ProductsViewState.DisplayProductDetails(product))
            }
        }
    }

    fun updateScrollState(newState: ScrollState) {
        _scrollStateCategory.postValue(newState)
    }

    fun updateLazyListState(newState: LazyListState) {
        _lazyListStateProducts.postValue(newState)
    }

    private fun fetchProductsForDate(
        needsToRefresh: Boolean = false,
        categoryId: Long? = null) {
        if (needsToRefresh) {
            _productsViewState.postValue(ProductsViewState.Loading)
        }

        viewModelScope.launch {
            if (_productsViewState.value == ProductsViewState.Loading) {
                _categories = categoriesRepository.fetchCategories()
                _products = productsRepository.fetchProducts()
                selectedCategoryId.value = _categories[0].id
            }

            if (_products.isEmpty()) {
                return@launch
            } else {

                selectedCategoryId.value =
                    categoryId ?: selectedCategoryId.value

                _displayedProducts = filteringProductsByCategory(selectedCategoryId.value!!)

                _productsViewState.postValue(ProductsViewState.DisplayProducts(
                    categories = _categories,
                    selectedCategoryId = selectedCategoryId.value!!,
                    scrollStateCategory = scrollStateCategory.value!!,
                    lazyListStateProducts = lazyListStateProducts.value!!,
                    products = _displayedProducts
                ))
            }
        }
    }

    private fun fetchProductDetailsForDate(
        product: RemoteListProduct
    ) {
        viewModelScope.launch {

            _productsViewState.postValue(
                ProductsViewState.DisplayProductDetails(product)
            )
        }
    }

    private fun filteringProductsByCategory(
        categoryId: Long
    ): List<RemoteListProduct> {
        val products: ArrayList<RemoteListProduct> = arrayListOf()
        _products.forEach() { product ->
            if (product.categoryId == categoryId) {
                products.add(product)
            }
        }
        return products
    }

    private fun fetchProductById(productId: Long): RemoteListProduct? {
        _products.firstNotNullOf { product ->
            if (product.productId == productId) return product
        }
        return null
    }
}
