package mo.show.androidapplication.store.presentation.products_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import mo.show.androidapplication.store.presentation.util.components.CategoryItem


@Composable
fun CategoryList(
    categories: List<String>,
    selectedCategory: String?,                 // Current selected category
    onCategorySelected: (String?) -> Unit
) {

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(categories) { index, category ->
            val color =getCategoryColor(index, categories.size)
            CategoryItem(
                categoryName = category,
                backgroundColor = color,
                onClick = {
                    onCategorySelected(if (selectedCategory == category) null else category)
                }
            )
        }
    }
}

/**
 * Generates a color for a category based on its index and the total number of categories.
 */
fun getCategoryColor(index: Int, total: Int): Color {
    val hue = (index.toFloat() / total) * 360f
    return Color.hsv(hue, 0.4f, 1f)
}