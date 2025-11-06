package mo.show.androidapplication.store.presentation.product_detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import mo.show.androidapplication.store.domain.model.Product
import mo.show.androidapplication.store.domain.model.Rating
import mo.show.androidapplication.store.presentation.products_screen.ProductsViewState
import mo.show.androidapplication.store.presentation.util.components.CategoryItem
import mo.show.androidapplication.store.presentation.util.components.LoadingDialog
import mo.show.androidapplication.store.presentation.util.components.MyTopAppBar

@Composable
fun ProductDetailsScreen(
    onBack:()->Unit,
    viewModel: ProductsScreenViewModel= hiltViewModel()
) {
    val state=viewModel.state.collectAsState().value
    ProductDetailsContent(state, onBack = onBack)

}


@Composable
fun ProductDetailsContent(
    state: ProductDetailViewState,
    onBack: () -> Unit
) {
    val product = state.product
    val pageTitle = product?.title ?: "Product Details"
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .padding(bottom = 10.dp),
        topBar = { MyTopAppBar(title = pageTitle, onBack = onBack) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(top=16.dp)
                .verticalScroll(scrollState),

            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Product Image
            product?.image?.let { imageUrl ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(5.dp)
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = product.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f)
                            .background(Color.LightGray),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                product?.let {
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,

                    )
                }


            }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
            // Category
            product?.category?.let {

                CategoryItem(   categoryName = "Electronics",
                    backgroundColor = Color(0xFFF44336),
                            textColor = Color.White,
                    modifier = Modifier.padding(4.dp) )
            }
                product?.let {

                    Text(
                        text = "$${it.price}",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color(0xFF4CAF50),
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(10.dp)
                    )
                }


                }
            // Description
            product?.description?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(10.dp),
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ProductDetailsScreenPreview() {
    val sampleProduct = Product(
        id = 1,
        title = "Stylish Shoes shoes with perfect fit for running",
        price = 79.99,
        description = "High quality running shoes",
        category = "Footwear",
        image = "https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_t.png",
        rating = Rating(rage = 4.5, count = 120)
    )

    ProductDetailsContent(
        state = ProductDetailViewState(
            product = sampleProduct,
            isLoading = false,
            error = null
        ),
        onBack = {}
    )
}