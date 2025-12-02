package com.forestmusic.converter_android.features.energy.data

import com.forestmusic.converter_android.features.energy.domain.models.EnergyUnit

/**
 * Data layer representation of energy units.
 * 
 * This object provides access to energy units and default unit selections
 * for the UI layer. It acts as a bridge between the domain layer (where
 * units are defined) and the UI layer (where units are displayed and used).
 * 
 * The data layer follows Clean Architecture principles by providing a simple
 * interface for accessing domain entities without exposing domain logic
 * directly to the UI layer.
 * 
 * Default unit selections are chosen to provide a good user experience:
 * - Default "from" unit: Joule (J) - the base SI unit
 * - Default "to" unit: Kilojoule (kJ) - commonly used in science and engineering
 * 
 * @see EnergyUnit
 * @see com.forestmusic.converter_android.features.energy.ui.EnergyViewModel
 */
object EnergyUnits {
	/**
	 * Returns all available energy units as a list.
	 * 
	 * This method provides access to all energy units defined in the
	 * [EnergyUnit] enum. The list is used to populate dropdown menus
	 * and unit selection UI components.
	 * 
	 * @return A list containing all energy units in enum definition order
	 */
	fun getAllUnits(): List<EnergyUnit> {
		return EnergyUnit.values().toList()
	}

	/**
	 * Returns the default source unit for energy conversion.
	 * 
	 * This unit is used as the initial "from" unit when the conversion
	 * screen is first displayed. Joule (J) is chosen as the default because:
	 * - It is the base SI unit for energy
	 * - It provides a good reference point for conversions
	 * 
	 * @return The default source unit: [EnergyUnit.JOULE]
	 */
	fun getDefaultFromUnit(): EnergyUnit = EnergyUnit.JOULE

	/**
	 * Returns the default target unit for energy conversion.
	 * 
	 * This unit is used as the initial "to" unit when the conversion
	 * screen is first displayed. Kilojoule (kJ) is chosen as the default because:
	 * - It is commonly used in science and engineering
	 * - It provides a good contrast to Joules (1000x larger)
	 * - It demonstrates the conversion functionality effectively
	 * 
	 * @return The default target unit: [EnergyUnit.KILOJOULE]
	 */
	fun getDefaultToUnit(): EnergyUnit = EnergyUnit.KILOJOULE
}

