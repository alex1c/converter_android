package com.forestmusic.converter_android.presentation.categories

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Data class representing a converter category.
 * 
 * This immutable data class holds all the information needed to display
 * and identify a converter category in the application. It is used throughout
 * the presentation layer to represent categories in lists, grids, and navigation.
 * 
 * Properties:
 * - [id]: Unique identifier used for navigation and routing
 * - [title]: Display name shown to users (localized)
 * - [icon]: Material icon displayed in the category card
 * 
 * Usage:
 * Instances of this class are created in [CategoriesData] and used in:
 * - [CategoriesScreen] for displaying the category grid
 * - [CategoryCard] for rendering individual category cards
 * - Navigation routing based on the category ID
 * 
 * Design decisions:
 * - Immutable data class ensures thread safety and prevents accidental modification
 * - All properties are required (no nullable types) for type safety
 * - Icon is an [ImageVector] for efficient rendering in Compose
 * 
 * @param id Unique string identifier for the category. Used in navigation routes
 *          and must match the route defined in [Screen] sealed class.
 *          Examples: "weight", "length", "temperature"
 * @param title Localized display name for the category. Shown to users in the UI.
 *             Examples: "Вес", "Длина", "Температура"
 * @param icon Material Design icon vector displayed in the category card.
 *            Should be semantically related to the category (e.g., Scale for weight,
 *            Straighten for length, Thermostat for temperature).
 * 
 * @see CategoriesData
 * @see CategoriesScreen
 * @see CategoryCard
 */
data class ConverterCategory(
	/**
	 * Unique identifier for the converter category.
	 * 
	 * This ID is used for:
	 * - Navigation routing (must match [Screen] route)
	 * - Identifying the category in callbacks
	 * - Matching categories with their conversion screens
	 * 
	 * The ID should be lowercase, use underscores for multi-word categories,
	 * and be consistent with the feature module name.
	 */
	val id: String,
	
	/**
	 * Display title for the category.
	 * 
	 * This is the localized name shown to users in the category card.
	 * The title should be clear, concise, and match the category's purpose.
	 */
	val title: String,
	
	/**
	 * Material Design icon for the category.
	 * 
	 * The icon is displayed in the category card to provide visual identification.
	 * Icons should be chosen from Material Icons library and be semantically
	 * related to the category's purpose.
	 */
	val icon: ImageVector
)

