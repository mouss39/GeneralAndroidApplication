package mo.show.androidapplication.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mo.show.androidapplication.store.presentation.util.components.MyTopAppBar

@Composable
internal fun MainScreen(onNavigateToProducts: () -> Unit) {
MainScreenContent(onNavigateToProducts)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    onNavigateToProducts: () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Main Screen") })
        }
    ) {padding ->

        Spacer(Modifier.padding(10.dp))
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = { onNavigateToProducts() }) {
                Text("Go to Products")
            }
        }
    }

}