package com.forestmusic.converter_android.features.temperature.data

import com.forestmusic.converter_android.features.temperature.domain.models.TemperatureUnit

/**
 * Data layer representation of temperature units.
 * 
 * This object provides access to temperature units and default unit selections
 * for the UI layer. It acts as a bridge between the domain layer (where
 * units are defined) and the UI layer (where units are displayed and used).
 * 
 * The data layer follows Clean Architecture principles by providing a simple
 * interface for accessing domain entities without exposing domain logic
 * directly to the UI layer.
 * 
 * Default unit selections are chosen to provide a good user experience:
 * - Default "from" unit: Celsius (°C) - the most commonly used metric unit
 * - Default "to" unit: Fahrenheit (°F) - commonly used in the United States
 * 
 * @see TemperatureUnit
 * @see com.forestmusic.converter_android.features.temperature.ui.TemperatureViewModel
 */
object TemperatureUnits {
	/**
	 * Returns all available temperature units as a list.
	 * 
	 * This method provides access to all temperature units defined in the
	 * [TemperatureUnit] enum. The list is used to populate dropdown menus
	 * and unit selection UI components.
	 * 
	 * @return A list containing all temperature units in enum definition order
	 */
	fun getAllUnits(): List<TemperatureUnit> {
		return TemperatureUnit.values().toList()
	}

	/**
	 * Returns the default source unit for temperature conversion.
	 * 
	 * This unit is used as the initial "from" unit when the conversion
	 * screen is first displayed. Celsius (°C) is chosen as the default because:
	 * - It is the most commonly used metric temperature unit
	 * - It is used worldwide for everyday temperature measurements
	 * - It provides a good reference point for conversions
	 * 
	 * @return The default source unit: [TemperatureUnit.CELSIUS]
	 */
	fun getDefaultFromUnit(): TemperatureUnit = TemperatureUnit.CELSIUS

	/**
	 * Returns the default target unit for temperature conversion.
	 * 
	 * This unit is used as the initial "to" unit when the conversion
	 * screen is first displayed. Fahrenheit (°F) is chosen as the default because:
	 * - It is commonly used in the United States
	 * - It provides a good contrast to Celsius
	 * - It demonstrates the conversion functionality effectively
	 * 
	 * @return The default target unit: [TemperatureUnit.FAHRENHEIT]
	 */
	fun getDefaultToUnit(): TemperatureUnit = TemperatureUnit.FAHRENHEIT
}

