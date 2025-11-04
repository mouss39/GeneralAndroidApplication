package mo.show.androidapplication.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                onBack = { navController.popBackStack() }
            )
        }
    }
}