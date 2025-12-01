package com.example.converter_android.presentation.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.converter_android.core.utils.Constants

/**
 * Main screen displaying all converter categories.
 * 
 * This composable function creates the home screen of the application, which displays
 * all available converter categories in a grid layout. Users can tap on any category
 * card to navigate to the corresponding conversion screen.
 * 
 * Features:
 * - Displays application title at the top
 * - Shows all converter categories in a 2-column grid
 * - Each category is represented by a card with an icon and title
 * - Cards are clickable and trigger navigation to the selected category
 * - Cards feature gradient backgrounds for visual appeal
 * 
 * Layout:
 * - Uses a [Column] as the root container for vertical arrangement
 * - Title text is centered at the top with appropriate spacing
 * - Categories are displayed in a [LazyVerticalGrid] for efficient rendering
 * - Grid uses 2 columns for optimal use of screen space
 * 
 * Performance:
 * - Uses [LazyVerticalGrid] for efficient rendering of large lists
 * - Only visible items are composed, improving performance
 * - Grid cells are fixed size for consistent layout
 * 
 * @param onCategorySelected Callback function called when a category card is clicked.
 *                           Receives the category ID as a parameter, which is used
 *                           for navigation to the appropriate conversion screen.
 * @param modifier Modifier to be applied to the root Column container.
 *                Allows for customization of layout, padding, etc.
 * 
 * @see CategoryCard
 * @see CategoriesData
 */
@Composable
fun CategoriesScreen(
	onCategorySelected: (String) -> Unit,
	modifier: Modifier = Modifier
) {
	// Root column container with padding and centered horizontal alignment
	Column(
		modifier = modifier
			.fillMaxSize()
			.padding(Constants.SPACING_MD.dp),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		// Application title displayed at the top
		Text(
			text = "Универсальный конвертер",
			style = MaterialTheme.typography.headlineLarge,
			fontWeight = FontWeight.Bold,
			modifier = Modifier
				.fillMaxWidth()
				.padding(
					top = Constants.SPACING_LG.dp,
					bottom = Constants.SPACING_XL.dp
				),
			textAlign = TextAlign.Center
		)

		// Grid layout for displaying category cards
		// Uses LazyVerticalGrid for efficient rendering of potentially large lists
		LazyVerticalGrid(
			columns = GridCells.Fixed(2), // 2 columns for optimal space usage
			horizontalArrangement = Arrangement.spacedBy(Constants.SPACING_MD.dp), // Space between columns
			verticalArrangement = Arrangement.spacedBy(Constants.SPACING_MD.dp), // Space between rows
			contentPadding = PaddingValues(bottom = Constants.SPACING_MD.dp) // Bottom padding for scroll
		) {
			// Create a card for each category in the data list
			items(CategoriesData.categories) { category ->
				CategoryCard(
					category = category,
					onClick = { onCategorySelected(category.id) } // Navigate on click
				)
			}
		}
	}
}

/**
 * Card representing a converter category.
 * 
 * This composable creates a clickable card that displays a category's icon and title.
 * The card is used in the categories grid on the home screen to represent each
 * available converter category.
 * 
 * Design:
 * - Uses Material3 Card component for consistent styling
 * - Fixed height (160.dp) for uniform grid appearance
 * - Gradient background for visual appeal
 * - Improved elevation for depth perception
 * - Centered icon and title layout
 * - Larger icons (56.dp) for better visibility
 * 
 * Interaction:
 * - Entire card is clickable
 * - Click triggers the [onClick] callback
 * - Uses Material ripple effect for visual feedback
 * - Pressed elevation for tactile feedback
 * 
 * Accessibility:
 * - Icon has content description for screen readers
 * - Card is focusable and keyboard navigable
 * 
 * @param category The [ConverterCategory] data object containing the category's
 *                ID, title, and icon. This data is used to populate the card's content.
 * @param onClick Callback function called when the card is clicked.
 *               Typically used to navigate to the category's conversion screen.
 * @param modifier Modifier to be applied to the Card container.
 *                Allows for customization of layout, padding, etc.
 * 
 * @see ConverterCategory
 * @see CategoriesScreen
 */
@Composable
private fun CategoryCard(
	category: ConverterCategory,
	onClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	// Generate gradient colors based on category index for variety
	val colorScheme = MaterialTheme.colorScheme
	val gradientColors = remember(category.id, colorScheme) {
		val baseColor = colorScheme.primaryContainer
		val variantColor = colorScheme.primary.copy(alpha = 0.3f)
		listOf(baseColor, variantColor)
	}

	// Material3 Card component with clickable modifier
	Card(
		modifier = modifier
			.fillMaxWidth()
			.height(160.dp) // Increased height for better proportions
			.clickable { onClick() }, // Make entire card clickable
		elevation = CardDefaults.cardElevation(
			defaultElevation = 2.dp, // Reduced for modern look
			pressedElevation = 8.dp // Increased on press for feedback
		),
		colors = CardDefaults.cardColors(
			containerColor = Color.Transparent // Transparent to show gradient
		)
	) {
		// Box with gradient background
		Box(
			modifier = Modifier
				.fillMaxSize()
				.background(
					brush = Brush.verticalGradient(
						colors = gradientColors
					)
				)
				.padding(Constants.SPACING_MD.dp),
			contentAlignment = Alignment.Center
		) {
			// Column for vertical arrangement of icon and title
			Column(
				horizontalAlignment = Alignment.CenterHorizontally,
				verticalArrangement = Arrangement.Center
			) {
				// Category icon - larger size for better visibility
				Icon(
					imageVector = category.icon,
					contentDescription = category.title, // Accessibility: describe the icon
					modifier = Modifier.size(56.dp), // Increased icon size
					tint = MaterialTheme.colorScheme.onPrimaryContainer // Icon color
				)
				// Category title
				Text(
					text = category.title,
					style = MaterialTheme.typography.titleLarge,
					fontWeight = FontWeight.Bold,
					color = MaterialTheme.colorScheme.onPrimaryContainer,
					textAlign = TextAlign.Center,
					modifier = Modifier.padding(top = Constants.SPACING_SM.dp) // Space between icon and title
				)
			}
		}
	}
}
