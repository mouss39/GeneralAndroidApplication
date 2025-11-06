package mo.show.androidapplication.store.data.repository

import arrow.core.Either
import mo.show.androidapplication.store.data.mapper.toNetworkError
import mo.show.androidapplication.store.data.remote.ProductsApi
import mo.show.androidapplication.store.domain.model.NetworkError
import mo.show.androidapplication.store.domain.model.Product
import mo.show.androidapplication.store.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsApi: ProductsApi
): ProductsRepository{

    override suspend fun getProducts(): Either<NetworkError, List<Product>> {
       return  Either.catch {
            productsApi.getProducts()
        }.mapLeft { it.toNetworkError() }
    }


    override suspend fun getProductById(id:Int): Either<NetworkError, Product> {
       return Either.catch {
           productsApi.getProductById(id)
       }.mapLeft {
           it.toNetworkError()
       }
    }
}