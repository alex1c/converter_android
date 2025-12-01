package com.example.converter_android.features.time.data

import com.example.converter_android.features.time.domain.models.TimeUnit

/**
 * Data layer representation of time units.
 * 
 * This object provides access to time units and default unit selections
 * for the UI layer. It acts as a bridge between the domain layer (where
 * units are defined) and the UI layer (where units are displayed and used).
 * 
 * The data layer follows Clean Architecture principles by providing a simple
 * interface for accessing domain entities without exposing domain logic
 * directly to the UI layer.
 * 
 * Default unit selections are chosen to provide a good user experience:
 * - Default "from" unit: Second (s) - the base SI unit
 * - Default "to" unit: Minute (min) - commonly used in everyday life
 * 
 * @see TimeUnit
 * @see com.example.converter_android.features.time.ui.TimeViewModel
 */
object TimeUnits {
	/**
	 * Returns all available time units as a list.
	 * 
	 * This method provides access to all time units defined in the
	 * [TimeUnit] enum. The list is used to populate dropdown menus
	 * and unit selection UI components.
	 * 
	 * @return A list containing all time units in enum definition order
	 */
	fun getAllUnits(): List<TimeUnit> {
		return TimeUnit.values().toList()
	}

	/**
	 * Returns the default source unit for time conversion.
	 * 
	 * This unit is used as the initial "from" unit when the conversion
	 * screen is first displayed. Second (s) is chosen as the default because:
	 * - It is the base SI unit for time
	 * - It provides a good reference point for conversions
	 * 
	 * @return The default source unit: [TimeUnit.SECOND]
	 */
	fun getDefaultFromUnit(): TimeUnit = TimeUnit.SECOND

	/**
	 * Returns the default target unit for time conversion.
	 * 
	 * This unit is used as the initial "to" unit when the conversion
	 * screen is first displayed. Minute (min) is chosen as the default because:
	 * - It is commonly used in everyday life
	 * - It provides a good contrast to seconds (60x larger)
	 * - It demonstrates the conversion functionality effectively
	 * 
	 * @return The default target unit: [TimeUnit.MINUTE]
	 */
	fun getDefaultToUnit(): TimeUnit = TimeUnit.MINUTE
}

