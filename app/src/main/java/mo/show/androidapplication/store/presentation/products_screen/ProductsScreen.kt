package mo.show.androidapplication.store.presentation.products_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mo.show.androidapplication.store.presentation.products_screen.components.ProductCard
import mo.show.androidapplication.store.presentation.util.components.LoadingDialog
import mo.show.androidapplication.store.presentation.util.components.MyTopAppBar

@Composable
internal fun ProductsScreen (
    onBack:()->Unit,
    viewModel: ProductsViewModel= hiltViewModel()
){
   val state= viewModel.state.collectAsStateWithLifecycle()
    ProductsContent(state = state.value, onBack)

}

@Composable
fun ProductsContent(
    state: ProductsViewState,
    onBack: ()-> Unit
){
    LoadingDialog(isLoading = state.isLoading)
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar ={ MyTopAppBar(title = "Products", onBack = onBack) }
    ) {
        LazyVerticalStaggeredGrid(
            modifier = Modifier.padding(top = it.calculateTopPadding()),
            columns = StaggeredGridCells.Fixed(count = 2),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalItemSpacing = 10.dp
        ) {
            items(state.products){product->
               ProductCard(product = product)

            }

        }
    }
}


