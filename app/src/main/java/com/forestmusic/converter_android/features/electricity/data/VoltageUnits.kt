package com.forestmusic.converter_android.features.electricity.data

import com.forestmusic.converter_android.features.electricity.domain.models.VoltageUnit

/**
 * Data layer representation of voltage units.
 * 
 * This object provides access to voltage units and default unit selections
 * for the UI layer. It acts as a bridge between the domain layer (where
 * units are defined) and the UI layer (where units are displayed and used).
 * 
 * The data layer follows Clean Architecture principles by providing a simple
 * interface for accessing domain entities without exposing domain logic
 * directly to the UI layer.
 * 
 * Default unit selections are chosen to provide a good user experience:
 * - Default "from" unit: Volt (V) - the most commonly used base unit
 * - Default "to" unit: Millivolt (mV) - a common smaller unit for comparison
 */
object VoltageUnits {
	/**
	 * Returns all available voltage units as a list.
	 * 
	 * This method provides access to all voltage units defined in the
	 * [VoltageUnit] enum. The list is used to populate dropdown menus
	 * and unit selection UI components.
	 * 
	 * @return A list containing all voltage units in the order they are
	 *         defined in the enum (VOLT, MILLIVOLT, KILOVOLT)
	 * 
	 * @example
	 * val units = VoltageUnits.getAllUnits()
	 * // Returns: [VOLT, MILLIVOLT, KILOVOLT]
	 */
	fun getAllUnits(): List<VoltageUnit> = VoltageUnit.values().toList()
	
	/**
	 * Returns the default source unit for voltage conversion.
	 * 
	 * This unit is used as the initial "from" unit when the conversion
	 * screen is first displayed. Volt (V) is chosen as the default because
	 * it is the base SI unit and the most commonly used unit for voltage
	 * measurements in everyday applications.
	 * 
	 * @return The default source unit: [VoltageUnit.VOLT]
	 */
	fun getDefaultFromUnit(): VoltageUnit = VoltageUnit.VOLT
	
	/**
	 * Returns the default target unit for voltage conversion.
	 * 
	 * This unit is used as the initial "to" unit when the conversion
	 * screen is first displayed. Millivolt (mV) is chosen as the default
	 * because it provides a good contrast to volts and is commonly used
	 * in electronics and small-scale measurements.
	 * 
	 * @return The default target unit: [VoltageUnit.MILLIVOLT]
	 */
	fun getDefaultToUnit(): VoltageUnit = VoltageUnit.MILLIVOLT
}

