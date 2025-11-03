package mo.show.androidapplication.store.data.remote

import mo.show.androidapplication.store.domain.model.Product
import retrofit2.http.GET

interface ProductsApi {

    @GET("products")
    suspend fun getProducts(): List<Product>


}
