package com.forestmusic.converter_android.features.angle.data

import com.forestmusic.converter_android.features.angle.domain.models.AngleUnit

/**
 * Data layer representation of angle units.
 * 
 * This object provides access to angle units and default unit selections
 * for the UI layer. It acts as a bridge between the domain layer (where
 * units are defined) and the UI layer (where units are displayed and used).
 * 
 * The data layer follows Clean Architecture principles by providing a simple
 * interface for accessing domain entities without exposing domain logic
 * directly to the UI layer.
 * 
 * Default unit selections are chosen to provide a good user experience:
 * - Default "from" unit: Degree (°) - the most commonly used unit
 * - Default "to" unit: Radian (rad) - commonly used in mathematics
 * 
 * @see AngleUnit
 * @see com.forestmusic.converter_android.features.angle.ui.AngleViewModel
 */
object AngleUnits {
	/**
	 * Returns all available angle units as a list.
	 * 
	 * This method provides access to all angle units defined in the
	 * [AngleUnit] enum. The list is used to populate dropdown menus
	 * and unit selection UI components.
	 * 
	 * @return A list containing all angle units in enum definition order
	 */
	fun getAllUnits(): List<AngleUnit> {
		return AngleUnit.values().toList()
	}

	/**
	 * Returns the default source unit for angle conversion.
	 * 
	 * This unit is used as the initial "from" unit when the conversion
	 * screen is first displayed. Degree (°) is chosen as the default because:
	 * - It is the most commonly used unit for angles
	 * - It is intuitive and easy to understand
	 * - It provides a good reference point for conversions
	 * 
	 * @return The default source unit: [AngleUnit.DEGREE]
	 */
	fun getDefaultFromUnit(): AngleUnit = AngleUnit.DEGREE

	/**
	 * Returns the default target unit for angle conversion.
	 * 
	 * This unit is used as the initial "to" unit when the conversion
	 * screen is first displayed. Radian (rad) is chosen as the default because:
	 * - It is commonly used in mathematics and physics
	 * - It provides a good contrast to degrees
	 * - It demonstrates the conversion functionality effectively
	 * 
	 * @return The default target unit: [AngleUnit.RADIAN]
	 */
	fun getDefaultToUnit(): AngleUnit = AngleUnit.RADIAN
}

