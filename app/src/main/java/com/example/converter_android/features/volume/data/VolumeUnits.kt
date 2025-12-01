package com.example.converter_android.features.volume.data

import com.example.converter_android.features.volume.domain.models.VolumeUnit

/**
 * Data layer representation of volume units.
 * 
 * This object provides access to volume units and default unit selections
 * for the UI layer. It acts as a bridge between the domain layer (where
 * units are defined) and the UI layer (where units are displayed and used).
 * 
 * The data layer follows Clean Architecture principles by providing a simple
 * interface for accessing domain entities without exposing domain logic
 * directly to the UI layer.
 * 
 * Default unit selections are chosen to provide a good user experience:
 * - Default "from" unit: Liter (L) - the most commonly used base unit
 * - Default "to" unit: Milliliter (mL) - a common smaller unit for comparison
 * 
 * @see VolumeUnit
 * @see com.example.converter_android.features.volume.ui.VolumeViewModel
 */
object VolumeUnits {
	/**
	 * Returns all available volume units as a list.
	 * 
	 * This method provides access to all volume units defined in the
	 * [VolumeUnit] enum. The list is used to populate dropdown menus
	 * and unit selection UI components.
	 * 
	 * The units are returned in the order they are defined in the enum.
	 * 
	 * @return A list containing all volume units in enum definition order
	 * 
	 * @example
	 * val units = VolumeUnits.getAllUnits()
	 * // Returns: [LITER, MILLILITER, CUBIC_METER, CUBIC_CENTIMETER, GALLON_US, GALLON_UK, PINT, CUP, TABLESPOON, TEASPOON]
	 */
	fun getAllUnits(): List<VolumeUnit> {
		return VolumeUnit.values().toList()
	}

	/**
	 * Returns the default source unit for volume conversion.
	 * 
	 * This unit is used as the initial "from" unit when the conversion
	 * screen is first displayed. Liter (L) is chosen as the default because:
	 * - It is the base metric unit for volume
	 * - It is the most commonly used unit in everyday life
	 * - It provides a good reference point for conversions
	 * 
	 * @return The default source unit: [VolumeUnit.LITER]
	 */
	fun getDefaultFromUnit(): VolumeUnit = VolumeUnit.LITER

	/**
	 * Returns the default target unit for volume conversion.
	 * 
	 * This unit is used as the initial "to" unit when the conversion
	 * screen is first displayed. Milliliter (mL) is chosen as the default because:
	 * - It provides a good contrast to liters (1000x smaller)
	 * - It is commonly used in cooking and medicine
	 * - It demonstrates the conversion functionality effectively
	 * 
	 * @return The default target unit: [VolumeUnit.MILLILITER]
	 */
	fun getDefaultToUnit(): VolumeUnit = VolumeUnit.MILLILITER
}

