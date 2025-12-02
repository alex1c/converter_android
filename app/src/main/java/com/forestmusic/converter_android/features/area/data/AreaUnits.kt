package com.forestmusic.converter_android.features.area.data

import com.forestmusic.converter_android.features.area.domain.models.AreaUnit

/**
 * Data layer representation of area units.
 * 
 * This object provides access to area units and default unit selections
 * for the UI layer. It acts as a bridge between the domain layer (where
 * units are defined) and the UI layer (where units are displayed and used).
 * 
 * The data layer follows Clean Architecture principles by providing a simple
 * interface for accessing domain entities without exposing domain logic
 * directly to the UI layer.
 * 
 * Default unit selections are chosen to provide a good user experience:
 * - Default "from" unit: Square meter (m²) - the base SI unit
 * - Default "to" unit: Hectare (ha) - commonly used for large land areas
 * 
 * @see AreaUnit
 * @see com.forestmusic.converter_android.features.area.ui.AreaViewModel
 */
object AreaUnits {
	/**
	 * Returns all available area units as a list.
	 * 
	 * This method provides access to all area units defined in the
	 * [AreaUnit] enum. The list is used to populate dropdown menus
	 * and unit selection UI components.
	 * 
	 * @return A list containing all area units in enum definition order
	 */
	fun getAllUnits(): List<AreaUnit> {
		return AreaUnit.values().toList()
	}

	/**
	 * Returns the default source unit for area conversion.
	 * 
	 * This unit is used as the initial "from" unit when the conversion
	 * screen is first displayed. Square meter (m²) is chosen as the default because:
	 * - It is the base SI unit for area
	 * - It is commonly used in everyday measurements
	 * - It provides a good reference point for conversions
	 * 
	 * @return The default source unit: [AreaUnit.SQUARE_METER]
	 */
	fun getDefaultFromUnit(): AreaUnit = AreaUnit.SQUARE_METER

	/**
	 * Returns the default target unit for area conversion.
	 * 
	 * This unit is used as the initial "to" unit when the conversion
	 * screen is first displayed. Hectare (ha) is chosen as the default because:
	 * - It is commonly used for large land areas
	 * - It provides a good contrast to square meters (10,000x larger)
	 * - It demonstrates the conversion functionality effectively
	 * 
	 * @return The default target unit: [AreaUnit.HECTARE]
	 */
	fun getDefaultToUnit(): AreaUnit = AreaUnit.HECTARE
}

