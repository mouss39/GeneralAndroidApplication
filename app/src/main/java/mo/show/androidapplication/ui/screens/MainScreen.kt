package mo.show.androidapplication.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mo.show.androidapplication.store.presentation.products_screen.ProductsViewModel
import mo.show.androidapplication.ui.screens.components.ProductsList

@Composable
internal fun MainScreen(onNavigateToProducts: () -> Unit) {
MainScreenContent(onNavigateToProducts= onNavigateToProducts)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    productsViewModel: ProductsViewModel= hiltViewModel(),
    onNavigateToProducts: () -> Unit
){

    val firstState= productsViewModel.state.collectAsStateWithLifecycle()



    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Main Screen") })
        }
    ) {innerPadding ->

     Column(
         modifier = Modifier
             .fillMaxSize()
             .padding(innerPadding)
     ) {
         Spacer(modifier = Modifier.height(10.dp))
         // Row with title + "View All" button
         Row(
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(horizontal = 16.dp),
             horizontalArrangement = Arrangement.SpaceBetween,
             verticalAlignment = Alignment.CenterVertically
         ) {
             Text(
                 text = "Products",
                 style = MaterialTheme.typography.headlineSmall,
                 fontWeight = FontWeight.Bold
             )
             TextButton(onClick = { onNavigateToProducts() }) {
                 Text(text = "View All Products >>")
             }
         }
         ProductsList(productsList = firstState.value.products)


     }
    }

}