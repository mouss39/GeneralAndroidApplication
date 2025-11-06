package mo.show.androidapplication.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mo.show.androidapplication.store.domain.model.Product
import mo.show.androidapplication.store.presentation.products_screen.components.ProductCard

@Composable
fun ProductsList(
    productsList: List<Product>,
) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(productsList.size) { index ->
            ProductCard(product = productsList[index], onClick = {})
        }
    }
}
