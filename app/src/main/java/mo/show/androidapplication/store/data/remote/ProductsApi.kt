package mo.show.androidapplication.store.data.remote

import mo.show.androidapplication.store.domain.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductsApi {

    @GET("products")
    suspend fun getProducts(): List<Product>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int): Product


}
