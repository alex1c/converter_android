package com.forestmusic.converter_android.features.pressure.data

import com.forestmusic.converter_android.features.pressure.domain.models.PressureUnit

/**
 * Data layer representation of pressure units.
 * 
 * This object provides access to pressure units and default unit selections
 * for the UI layer. It acts as a bridge between the domain layer (where
 * units are defined) and the UI layer (where units are displayed and used).
 * 
 * The data layer follows Clean Architecture principles by providing a simple
 * interface for accessing domain entities without exposing domain logic
 * directly to the UI layer.
 * 
 * Default unit selections are chosen to provide a good user experience:
 * - Default "from" unit: Pascal (Pa) - the base SI unit
 * - Default "to" unit: Kilopascal (kPa) - commonly used in engineering
 * 
 * @see PressureUnit
 * @see com.forestmusic.converter_android.features.pressure.ui.PressureViewModel
 */
object PressureUnits {
	/**
	 * Returns all available pressure units as a list.
	 * 
	 * This method provides access to all pressure units defined in the
	 * [PressureUnit] enum. The list is used to populate dropdown menus
	 * and unit selection UI components.
	 * 
	 * @return A list containing all pressure units in enum definition order
	 */
	fun getAllUnits(): List<PressureUnit> {
		return PressureUnit.values().toList()
	}

	/**
	 * Returns the default source unit for pressure conversion.
	 * 
	 * This unit is used as the initial "from" unit when the conversion
	 * screen is first displayed. Pascal (Pa) is chosen as the default because:
	 * - It is the base SI unit for pressure
	 * - It provides a good reference point for conversions
	 * 
	 * @return The default source unit: [PressureUnit.PASCAL]
	 */
	fun getDefaultFromUnit(): PressureUnit = PressureUnit.PASCAL

	/**
	 * Returns the default target unit for pressure conversion.
	 * 
	 * This unit is used as the initial "to" unit when the conversion
	 * screen is first displayed. Kilopascal (kPa) is chosen as the default because:
	 * - It is commonly used in engineering and meteorology
	 * - It provides a good contrast to Pascals (1000x larger)
	 * - It demonstrates the conversion functionality effectively
	 * 
	 * @return The default target unit: [PressureUnit.KILOPASCAL]
	 */
	fun getDefaultToUnit(): PressureUnit = PressureUnit.KILOPASCAL
}

