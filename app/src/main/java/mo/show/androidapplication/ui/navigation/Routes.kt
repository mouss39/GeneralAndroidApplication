package mo.show.androidapplication.ui.navigation

object Routes {
    const val Main = "main"
    const val Products = "products"

    const val ProductDetails="productDetails"

    fun productDetails(productId: Int) = "$ProductDetails/$productId"
}