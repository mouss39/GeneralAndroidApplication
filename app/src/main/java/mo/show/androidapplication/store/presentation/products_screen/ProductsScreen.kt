package mo.show.androidapplication.store.presentation.products_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import mo.show.androidapplication.store.domain.model.Product
import mo.show.androidapplication.store.domain.model.Rating
import mo.show.androidapplication.store.presentation.products_screen.components.CategoryList
import mo.show.androidapplication.store.presentation.products_screen.components.ProductCard
import mo.show.androidapplication.store.presentation.util.components.LoadingDialog
import mo.show.androidapplication.store.presentation.util.components.MyTopAppBar

@Composable
internal fun ProductsScreen (
    onProductClick: (Int) -> Unit,
    onBack:()->Unit,
    viewModel: ProductsViewModel= hiltViewModel()
){
   val state= viewModel.state.collectAsStateWithLifecycle()
    ProductsContent(state = state.value, onBack,onProductClick)

}

@Composable
fun ProductsContent(
    state: ProductsViewState,
    onBack: ()-> Unit,
    onProductClick: (Int) -> Unit
){
    //search text
    var query by rememberSaveable { mutableStateOf("") }

    //categories
    val categories = state.products.map { it.category }.distinct()
    var selectedCategory by rememberSaveable { mutableStateOf<String?>(null) }

    //filter the list based on the search text and/or if a category is selected
    val filteredProducts = remember(query, selectedCategory, state.products) {
        state.products.filter { product ->
            val matchesQuery = product.title.contains(query, ignoreCase = true)
            val matchesCategory = selectedCategory?.let { it == product.category } ?: true
            matchesQuery && matchesCategory
        }
    }

    //loading dialog
    LoadingDialog(isLoading = state.isLoading)

    // page Content
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar ={ MyTopAppBar(title = "Products", onBack = onBack) }
    ) {innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text(text = "Search Products by Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                shape = RoundedCornerShape(16.dp)
                )

            CategoryList(
                categories = categories,
                selectedCategory = selectedCategory,
                onCategorySelected = { selectedCategory = it }
            )

            if (filteredProducts.isEmpty()) {
                // Show a message when no products match
                Text(
                    text = "No products found",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
            } else {
                LazyVerticalStaggeredGrid(
                    modifier = Modifier.padding(top = 10.dp),
                    columns = StaggeredGridCells.Fixed(count = 2),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalItemSpacing = 10.dp
                ) {
                    items(filteredProducts) { product ->
                        ProductCard(
                            product = product,
                            onClick = { onProductClick(product.id) })

                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsScreenPreview() {
    val sampleProducts = listOf(
        Product(
            id = 1,
            title = "Shoes",
            price = 59.99,
            description = "Nice",
            category = "Footwear",
            image = "",
            rating = Rating(rage = 4.5, count = 120)
        ),
        Product(
            id = 2,
            title = "Bag",
            price = 39.99,
            description = "Stylish",
            category = "Accessories",
            image = "",
            rating = Rating(rage = 4.0, count = 80)
        )
    )

    ProductsContent(
        state = ProductsViewState(
            products = sampleProducts,
            isLoading = false
        ),
        onBack = {},
        onProductClick = {}
    )
}

