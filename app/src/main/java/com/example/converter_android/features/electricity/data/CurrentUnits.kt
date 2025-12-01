package com.example.converter_android.features.electricity.data

import com.example.converter_android.features.electricity.domain.models.CurrentUnit

/**
 * Data layer representation of current units.
 * 
 * This object provides access to current units and default unit selections
 * for the UI layer. It acts as a bridge between the domain layer (where
 * units are defined) and the UI layer (where units are displayed and used).
 * 
 * The data layer follows Clean Architecture principles by providing a simple
 * interface for accessing domain entities without exposing domain logic
 * directly to the UI layer.
 * 
 * Default unit selections are chosen to provide a good user experience:
 * - Default "from" unit: Ampere (A) - the most commonly used base unit
 * - Default "to" unit: Milliampere (mA) - a common smaller unit for comparison
 */
object CurrentUnits {
	/**
	 * Returns all available current units as a list.
	 * 
	 * This method provides access to all current units defined in the
	 * [CurrentUnit] enum. The list is used to populate dropdown menus
	 * and unit selection UI components.
	 * 
	 * @return A list containing all current units in the order they are
	 *         defined in the enum (AMPERE, MILLIAMPERE, KILOAMPERE)
	 * 
	 * @example
	 * val units = CurrentUnits.getAllUnits()
	 * // Returns: [AMPERE, MILLIAMPERE, KILOAMPERE]
	 */
	fun getAllUnits(): List<CurrentUnit> = CurrentUnit.values().toList()
	
	/**
	 * Returns the default source unit for current conversion.
	 * 
	 * This unit is used as the initial "from" unit when the conversion
	 * screen is first displayed. Ampere (A) is chosen as the default because
	 * it is the base SI unit and the most commonly used unit for current
	 * measurements in everyday applications.
	 * 
	 * @return The default source unit: [CurrentUnit.AMPERE]
	 */
	fun getDefaultFromUnit(): CurrentUnit = CurrentUnit.AMPERE
	
	/**
	 * Returns the default target unit for current conversion.
	 * 
	 * This unit is used as the initial "to" unit when the conversion
	 * screen is first displayed. Milliampere (mA) is chosen as the default
	 * because it provides a good contrast to amperes and is commonly used
	 * in electronics and small-scale measurements.
	 * 
	 * @return The default target unit: [CurrentUnit.MILLIAMPERE]
	 */
	fun getDefaultToUnit(): CurrentUnit = CurrentUnit.MILLIAMPERE
}

