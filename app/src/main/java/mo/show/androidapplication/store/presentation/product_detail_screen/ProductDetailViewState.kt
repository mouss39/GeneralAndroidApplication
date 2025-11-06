package mo.show.androidapplication.store.presentation.product_detail_screen

import mo.show.androidapplication.store.domain.model.Product

data class  ProductDetailViewState (
        val product: Product? = null,
        val isLoading: Boolean=false,
        val error: String?=null,
)