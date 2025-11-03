package mo.show.androidapplication.store.presentation.products_screen

import android.provider.CalendarContract
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import mo.show.androidapplication.store.domain.repository.ProductsRepository
import mo.show.androidapplication.store.presentation.util.sendEvent
import mo.show.androidapplication.util.Event
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsRepository: ProductsRepository
): ViewModel() {
    private val _state= MutableStateFlow(ProductsViewState())
    val state=_state.asStateFlow()

    init {
        getProducts()
    }
    fun getProducts() {
        viewModelScope.launch {
            _state.update {
                it.copy(isLoading = true)

            }

            productsRepository.getProducts()
                .onRight { products ->
                    _state.update {
                        it.copy(
                            products = products,
                            isLoading = false
                        )
                    }
                }
                .onLeft { error ->
                    _state.update {
                        it.copy(
                            error = error.error.message,
                            isLoading = false
                        )
                    }
                    sendEvent(Event.Toast(error.error.message))
                }
            _state.update {
                it.copy(isLoading = false)

            }



        }
    }
}