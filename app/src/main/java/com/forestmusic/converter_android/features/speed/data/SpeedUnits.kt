package com.forestmusic.converter_android.features.speed.data

import com.forestmusic.converter_android.features.speed.domain.models.SpeedUnit

/**
 * Data layer representation of speed units.
 * 
 * This object provides access to speed units and default unit selections
 * for the UI layer. It acts as a bridge between the domain layer (where
 * units are defined) and the UI layer (where units are displayed and used).
 * 
 * The data layer follows Clean Architecture principles by providing a simple
 * interface for accessing domain entities without exposing domain logic
 * directly to the UI layer.
 * 
 * Default unit selections are chosen to provide a good user experience:
 * - Default "from" unit: Meter per second (m/s) - the base SI unit
 * - Default "to" unit: Kilometer per hour (km/h) - commonly used for road speeds
 * 
 * @see SpeedUnit
 * @see com.forestmusic.converter_android.features.speed.ui.SpeedViewModel
 */
object SpeedUnits {
	/**
	 * Returns all available speed units as a list.
	 * 
	 * This method provides access to all speed units defined in the
	 * [SpeedUnit] enum. The list is used to populate dropdown menus
	 * and unit selection UI components.
	 * 
	 * @return A list containing all speed units in enum definition order
	 */
	fun getAllUnits(): List<SpeedUnit> {
		return SpeedUnit.values().toList()
	}

	/**
	 * Returns the default source unit for speed conversion.
	 * 
	 * This unit is used as the initial "from" unit when the conversion
	 * screen is first displayed. Meter per second (m/s) is chosen as the default because:
	 * - It is the base SI unit for speed
	 * - It provides a good reference point for conversions
	 * 
	 * @return The default source unit: [SpeedUnit.METER_PER_SECOND]
	 */
	fun getDefaultFromUnit(): SpeedUnit = SpeedUnit.METER_PER_SECOND

	/**
	 * Returns the default target unit for speed conversion.
	 * 
	 * This unit is used as the initial "to" unit when the conversion
	 * screen is first displayed. Kilometer per hour (km/h) is chosen as the default because:
	 * - It is commonly used for road speeds worldwide
	 * - It provides a good contrast to m/s
	 * - It demonstrates the conversion functionality effectively
	 * 
	 * @return The default target unit: [SpeedUnit.KILOMETER_PER_HOUR]
	 */
	fun getDefaultToUnit(): SpeedUnit = SpeedUnit.KILOMETER_PER_HOUR
}

