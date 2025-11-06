package mo.show.androidapplication.store.presentation.product_detail_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mo.show.androidapplication.store.domain.repository.ProductsRepository
import mo.show.androidapplication.store.presentation.products_screen.ProductsViewState
import javax.inject.Inject

@HiltViewModel
class ProductsScreenViewModel @Inject constructor(
    private val productsRepository: ProductsRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state= MutableStateFlow(ProductDetailViewState())
    val state=_state.asStateFlow()


    init{
        val productId = savedStateHandle.get<Int>("productId") ?: 0
        getProductById(productId)
    }
    fun getProductById(productId: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val result = productsRepository.getProductById(productId)

            result.fold(
                { error -> // Left: NetworkError
                    _state.update {
                        it.copy(
                            error = error.error.message,
                            isLoading = false
                        )
                    }
                },
                { product -> // Right: Product
                    _state.update {
                        it.copy(
                            product = product,
                            isLoading = false
                        )
                    }
                }
            )
        }
    }


}