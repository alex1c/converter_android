package com.forestmusic.converter_android.features.weight.data

import com.forestmusic.converter_android.features.weight.domain.models.WeightUnit

/**
 * Data layer representation of weight units.
 * 
 * This object provides access to weight units and default unit selections
 * for the UI layer. It acts as a bridge between the domain layer (where
 * units are defined) and the UI layer (where units are displayed and used).
 * 
 * The data layer follows Clean Architecture principles by providing a simple
 * interface for accessing domain entities without exposing domain logic
 * directly to the UI layer.
 * 
 * Default unit selections are chosen to provide a good user experience:
 * - Default "from" unit: Kilogram (kg) - the most commonly used base unit
 * - Default "to" unit: Gram (g) - a common smaller unit for comparison
 * 
 * @see WeightUnit
 * @see com.example.converter_android.features.weight.ui.WeightViewModel
 */
object WeightUnits {
	/**
	 * Returns all available weight units as a list.
	 * 
	 * This method provides access to all weight units defined in the
	 * [WeightUnit] enum. The list is used to populate dropdown menus
	 * and unit selection UI components.
	 * 
	 * The units are returned in the order they are defined in the enum:
	 * KILOGRAM, GRAM, TON, POUND, OUNCE, MILLIGRAM
	 * 
	 * @return A list containing all weight units in enum definition order
	 * 
	 * @example
	 * val units = WeightUnits.getAllUnits()
	 * // Returns: [KILOGRAM, GRAM, TON, POUND, OUNCE, MILLIGRAM]
	 */
	fun getAllUnits(): List<WeightUnit> {
		return WeightUnit.values().toList()
	}

	/**
	 * Returns the default source unit for weight conversion.
	 * 
	 * This unit is used as the initial "from" unit when the conversion
	 * screen is first displayed. Kilogram (kg) is chosen as the default because:
	 * - It is the base SI unit for mass
	 * - It is the most commonly used unit in everyday life
	 * - It provides a good reference point for conversions
	 * 
	 * @return The default source unit: [WeightUnit.KILOGRAM]
	 */
	fun getDefaultFromUnit(): WeightUnit = WeightUnit.KILOGRAM

	/**
	 * Returns the default target unit for weight conversion.
	 * 
	 * This unit is used as the initial "to" unit when the conversion
	 * screen is first displayed. Gram (g) is chosen as the default because:
	 * - It provides a good contrast to kilograms (1000x smaller)
	 * - It is commonly used in everyday measurements
	 * - It demonstrates the conversion functionality effectively
	 * 
	 * @return The default target unit: [WeightUnit.GRAM]
	 */
	fun getDefaultToUnit(): WeightUnit = WeightUnit.GRAM
}

