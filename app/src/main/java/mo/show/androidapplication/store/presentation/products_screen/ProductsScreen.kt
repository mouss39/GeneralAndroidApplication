package mo.show.androidapplication.store.presentation.products_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mo.show.androidapplication.store.domain.model.Product
import mo.show.androidapplication.store.domain.model.Rating
import mo.show.androidapplication.store.presentation.products_screen.components.CategoryList
import mo.show.androidapplication.store.presentation.products_screen.components.ProductCard
import mo.show.androidapplication.store.presentation.util.components.CreativeTopBar
import mo.show.androidapplication.store.presentation.util.components.LoadingDialog

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
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            // Creative Top Bar
            CreativeTopBar(onBack = onBack,"Products")

            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                OutlinedTextField(
                    value = query,
                    onValueChange = { query = it },
                    placeholder = { Text(text = "Search Products...") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(50),
                    colors = androidx.compose.material3.TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.outline
                    ),
                    singleLine = true
                )

                CategoryList(
                    categories = categories,
                    selectedCategory = selectedCategory,
                    onCategorySelected = { selectedCategory = it }
                )

                if (filteredProducts.isEmpty()) {
                    // Show a message when no products match
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No products found",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                } else {
                    LazyVerticalStaggeredGrid(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        columns = StaggeredGridCells.Fixed(count = 2),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalItemSpacing = 16.dp,
                        contentPadding = PaddingValues(bottom = 16.dp)
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

