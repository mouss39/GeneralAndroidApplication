package mo.show.androidapplication.store.presentation.products_screen

import mo.show.androidapplication.store.domain.model.Product

data class ProductsViewState (

    val products: List<Product> = emptyList(),
    val isLoading: Boolean=false,
    val error: String?=null,
)