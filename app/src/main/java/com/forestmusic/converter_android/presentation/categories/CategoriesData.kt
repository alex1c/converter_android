package com.forestmusic.converter_android.presentation.categories

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.LocalDrink
import androidx.compose.material.icons.filled.Power
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Scale
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Straighten
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.WaterDrop

/**
 * List of available converter categories.
 * 
 * This object serves as the central registry for all converter categories
 * in the application. It provides a single source of truth for which categories
 * are available and how they should be displayed.
 * 
 * Purpose:
 * - Centralizes category definitions in one location
 * - Makes it easy to add, remove, or reorder categories
 * - Ensures consistency across the application
 * - Simplifies maintenance and updates
 * 
 * Structure:
 * Each category is defined as a [ConverterCategory] with:
 * - A unique ID (used for navigation)
 * - A localized title (displayed to users)
 * - A Material icon (visual representation)
 * 
 * Adding new categories:
 * 1. Create the category feature module (domain, data, ui layers)
 * 2. Add a new [ConverterCategory] entry to this list
 * 3. Add the category route to [Screen] sealed class in [NavGraph]
 * 4. Add navigation handling in [NavGraph]'s NavHost
 * 5. Add localization strings for the category title
 * 
 * Category order:
 * Categories are listed in a logical order, typically:
 * - Basic measurements first (weight, length, volume)
 * - Derived measurements (area, speed, pressure)
 * - Specialized measurements (energy, power, torque)
 * - Special categories (electricity with calculators)
 * 
 * @see ConverterCategory
 * @see CategoriesScreen
 * @see NavGraph
 */
object CategoriesData {
	/**
	 * List of all available converter categories.
	 * 
	 * This list is used by [CategoriesScreen] to display the category grid.
	 * The order of categories in this list determines their display order
	 * in the UI (top to bottom, left to right in a 2-column grid).
	 * 
	 * Each category must have:
	 * - A unique ID that matches a route in [Screen]
	 * - A corresponding feature module with conversion logic
	 * - Navigation handling in [NavGraph]
	 * 
	 * @return Immutable list of [ConverterCategory] objects
	 */
	val categories = listOf(
		ConverterCategory(
			id = "weight",
			title = "Вес",
			icon = Icons.Default.Scale
		),
		ConverterCategory(
			id = "length",
			title = "Длина",
			icon = Icons.Default.Straighten
		),
		ConverterCategory(
			id = "volume",
			title = "Объём",
			icon = Icons.Default.LocalDrink
		),
		ConverterCategory(
			id = "temperature",
			title = "Температура",
			icon = Icons.Default.Thermostat
		),
		ConverterCategory(
			id = "speed",
			title = "Скорость",
			icon = Icons.Default.Speed
		),
		ConverterCategory(
			id = "area",
			title = "Площадь",
			icon = Icons.Default.GridOn
		),
		ConverterCategory(
			id = "pressure",
			title = "Давление",
			icon = Icons.Default.WaterDrop
		),
		ConverterCategory(
			id = "energy",
			title = "Энергия",
			icon = Icons.Default.Bolt
		),
		ConverterCategory(
			id = "time",
			title = "Время",
			icon = Icons.Default.Schedule
		),
		ConverterCategory(
			id = "angle",
			title = "Углы",
			icon = Icons.Default.Explore
		),
		ConverterCategory(
			id = "density",
			title = "Плотность",
			icon = Icons.Default.Layers
		),
		ConverterCategory(
			id = "power",
			title = "Мощность",
			icon = Icons.Default.Power
		),
		ConverterCategory(
			id = "torque",
			title = "Крутящий момент",
			icon = Icons.Default.Build
		),
		ConverterCategory(
			id = "electricity",
			title = "Электричество",
			icon = Icons.Default.Bolt
		)
		// Future categories can be added here:
	)
}

