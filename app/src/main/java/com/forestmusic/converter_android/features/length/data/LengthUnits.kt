package com.forestmusic.converter_android.features.length.data

import com.forestmusic.converter_android.features.length.domain.models.LengthUnit

/**
 * Data layer representation of length units.
 * 
 * This object provides access to length units and default unit selections
 * for the UI layer. It acts as a bridge between the domain layer (where
 * units are defined) and the UI layer (where units are displayed and used).
 * 
 * The data layer follows Clean Architecture principles by providing a simple
 * interface for accessing domain entities without exposing domain logic
 * directly to the UI layer.
 * 
 * Default unit selections are chosen to provide a good user experience:
 * - Default "from" unit: Meter (m) - the most commonly used base unit
 * - Default "to" unit: Kilometer (km) - a common larger unit for comparison
 * 
 * @see LengthUnit
 * @see com.forestmusic.converter_android.features.length.ui.LengthViewModel
 */
object LengthUnits {
	/**
	 * Returns all available length units as a list.
	 * 
	 * This method provides access to all length units defined in the
	 * [LengthUnit] enum. The list is used to populate dropdown menus
	 * and unit selection UI components.
	 * 
	 * The units are returned in the order they are defined in the enum:
	 * METER, KILOMETER, CENTIMETER, MILLIMETER, MILE, YARD, FOOT, INCH, NAUTICAL_MILE
	 * 
	 * @return A list containing all length units in enum definition order
	 * 
	 * @example
	 * val units = LengthUnits.getAllUnits()
	 * // Returns: [METER, KILOMETER, CENTIMETER, MILLIMETER, MILE, YARD, FOOT, INCH, NAUTICAL_MILE]
	 */
	fun getAllUnits(): List<LengthUnit> {
		return LengthUnit.values().toList()
	}

	/**
	 * Returns the default source unit for length conversion.
	 * 
	 * This unit is used as the initial "from" unit when the conversion
	 * screen is first displayed. Meter (m) is chosen as the default because:
	 * - It is the base SI unit for length
	 * - It is the most commonly used unit in everyday life
	 * - It provides a good reference point for conversions
	 * 
	 * @return The default source unit: [LengthUnit.METER]
	 */
	fun getDefaultFromUnit(): LengthUnit = LengthUnit.METER

	/**
	 * Returns the default target unit for length conversion.
	 * 
	 * This unit is used as the initial "to" unit when the conversion
	 * screen is first displayed. Kilometer (km) is chosen as the default because:
	 * - It provides a good contrast to meters (1000x larger)
	 * - It is commonly used for longer distances
	 * - It demonstrates the conversion functionality effectively
	 * 
	 * @return The default target unit: [LengthUnit.KILOMETER]
	 */
	fun getDefaultToUnit(): LengthUnit = LengthUnit.KILOMETER
}

