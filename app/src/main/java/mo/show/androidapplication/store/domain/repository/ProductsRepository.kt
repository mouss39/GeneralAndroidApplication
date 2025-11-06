package mo.show.androidapplication.store.domain.repository

import arrow.core.Either
import mo.show.androidapplication.store.domain.model.NetworkError
import mo.show.androidapplication.store.domain.model.Product

interface ProductsRepository {
    suspend fun getProducts(): Either<NetworkError, List<Product>>
    suspend fun getProductById(id: Int): Either<NetworkError, Product>

}