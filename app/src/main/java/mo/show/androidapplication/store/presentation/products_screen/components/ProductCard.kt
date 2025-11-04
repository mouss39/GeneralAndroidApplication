package mo.show.androidapplication.store.presentation.products_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import mo.show.androidapplication.store.domain.model.Product
import mo.show.androidapplication.store.domain.model.Rating

@Composable
fun ProductCard(
    modifier: Modifier = Modifier,
    product: Product) {

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White)

    ) {
        Column (modifier = Modifier.padding(15.dp)){
            AsyncImage(model= product.image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.FillBounds
            )
            Spacer(modifier = Modifier.height(5.dp))

            Text(text= product.title,
                style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(text= product.category,
                    style = MaterialTheme.typography.bodyMedium)
                Text(text= product.price.toString(),
                    style = MaterialTheme.typography.bodyMedium)
            }


        }
    }

}

@Composable
@Preview(showBackground = true)
fun ProductCardPreview() {
    val sampleProduct = Product(
        id = 1,
        title = "Stylish Shoes",
        price = 79.99,
        description = "High quality running shoes",
        category = "Footwear",
        image = "https://fakestoreapi.com/img/71YXzeOuslL._AC_UY879_t.png",
        rating = Rating(rage = 4.5, count = 120)
    )

    ProductCard(product = sampleProduct)
}