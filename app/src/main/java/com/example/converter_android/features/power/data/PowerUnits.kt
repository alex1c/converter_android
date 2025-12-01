package com.example.converter_android.features.power.data

import com.example.converter_android.features.power.domain.models.PowerUnit

/**
 * Data layer representation of power units.
 * 
 * This object provides access to power units and default unit selections
 * for the UI layer. It acts as a bridge between the domain layer (where
 * units are defined) and the UI layer (where units are displayed and used).
 * 
 * The data layer follows Clean Architecture principles by providing a simple
 * interface for accessing domain entities without exposing domain logic
 * directly to the UI layer.
 * 
 * Default unit selections are chosen to provide a good user experience:
 * - Default "from" unit: Watt (W) - the base SI unit
 * - Default "to" unit: Kilowatt (kW) - commonly used for electrical power
 * 
 * @see PowerUnit
 * @see com.example.converter_android.features.power.ui.PowerViewModel
 */
object PowerUnits {
	/**
	 * Returns all available power units as a list.
	 * 
	 * This method provides access to all power units defined in the
	 * [PowerUnit] enum. The list is used to populate dropdown menus
	 * and unit selection UI components.
	 * 
	 * @return A list containing all power units in enum definition order
	 */
	fun getAllUnits(): List<PowerUnit> {
		return PowerUnit.values().toList()
	}

	/**
	 * Returns the default source unit for power conversion.
	 * 
	 * This unit is used as the initial "from" unit when the conversion
	 * screen is first displayed. Watt (W) is chosen as the default because:
	 * - It is the base SI unit for power
	 * - It provides a good reference point for conversions
	 * 
	 * @return The default source unit: [PowerUnit.WATT]
	 */
	fun getDefaultFromUnit(): PowerUnit = PowerUnit.WATT

	/**
	 * Returns the default target unit for power conversion.
	 * 
	 * This unit is used as the initial "to" unit when the conversion
	 * screen is first displayed. Kilowatt (kW) is chosen as the default because:
	 * - It is commonly used for electrical power ratings
	 * - It provides a good contrast to Watts (1000x larger)
	 * - It demonstrates the conversion functionality effectively
	 * 
	 * @return The default target unit: [PowerUnit.KILOWATT]
	 */
	fun getDefaultToUnit(): PowerUnit = PowerUnit.KILOWATT
}

