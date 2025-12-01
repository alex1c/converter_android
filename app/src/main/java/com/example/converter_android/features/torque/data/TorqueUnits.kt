package com.example.converter_android.features.torque.data

import com.example.converter_android.features.torque.domain.models.TorqueUnit

/**
 * Data layer representation of torque units.
 * 
 * This object provides access to torque units and default unit selections
 * for the UI layer. It acts as a bridge between the domain layer (where
 * units are defined) and the UI layer (where units are displayed and used).
 * 
 * The data layer follows Clean Architecture principles by providing a simple
 * interface for accessing domain entities without exposing domain logic
 * directly to the UI layer.
 * 
 * Default unit selections are chosen to provide a good user experience:
 * - Default "from" unit: Newton-meter (N·m) - the base SI unit
 * - Default "to" unit: Pound-foot (lbf·ft) - commonly used in automotive
 * 
 * @see TorqueUnit
 * @see com.example.converter_android.features.torque.ui.TorqueViewModel
 */
object TorqueUnits {
	/**
	 * Returns all available torque units as a list.
	 * 
	 * This method provides access to all torque units defined in the
	 * [TorqueUnit] enum. The list is used to populate dropdown menus
	 * and unit selection UI components.
	 * 
	 * @return A list containing all torque units in enum definition order
	 */
	fun getAllUnits(): List<TorqueUnit> {
		return TorqueUnit.values().toList()
	}

	/**
	 * Returns the default source unit for torque conversion.
	 * 
	 * This unit is used as the initial "from" unit when the conversion
	 * screen is first displayed. Newton-meter (N·m) is chosen as the default because:
	 * - It is the base SI unit for torque
	 * - It provides a good reference point for conversions
	 * 
	 * @return The default source unit: [TorqueUnit.NEWTON_METER]
	 */
	fun getDefaultFromUnit(): TorqueUnit = TorqueUnit.NEWTON_METER

	/**
	 * Returns the default target unit for torque conversion.
	 * 
	 * This unit is used as the initial "to" unit when the conversion
	 * screen is first displayed. Pound-foot (lbf·ft) is chosen as the default because:
	 * - It is commonly used in automotive specifications
	 * - It provides a good contrast to N·m
	 * - It demonstrates the conversion functionality effectively
	 * 
	 * @return The default target unit: [TorqueUnit.POUND_FOOT]
	 */
	fun getDefaultToUnit(): TorqueUnit = TorqueUnit.POUND_FOOT
}

