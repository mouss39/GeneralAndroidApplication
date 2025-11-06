package mo.show.androidapplication.ui.navigation

import android.R.attr.type
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import mo.show.androidapplication.store.presentation.product_detail_screen.ProductDetailsScreen
import mo.show.androidapplication.store.presentation.products_screen.ProductsScreen
import mo.show.androidapplication.ui.screens.MainScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Main
    ) {
        composable(Routes.Main) {
            MainScreen(
                onNavigateToProducts = {
                    navController.navigate(Routes.Products)
                }
            )
        }

        composable(Routes.Products) {
            ProductsScreen(
                onProductClick = { productId ->
                    navController.navigate("${Routes.ProductDetails}/$productId")
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = "${Routes.ProductDetails}/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            ProductDetailsScreen(
                onBack = { navController.popBackStack() }
            ) // ViewModel automatically gets productId via SavedStateHandle
        }
    }
}