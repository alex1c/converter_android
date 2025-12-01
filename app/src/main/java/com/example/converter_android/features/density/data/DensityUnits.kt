package com.example.converter_android.features.density.data

import com.example.converter_android.features.density.domain.models.DensityUnit

/**
 * Data layer representation of density units.
 * 
 * This object provides access to density units and default unit selections
 * for the UI layer. It acts as a bridge between the domain layer (where
 * units are defined) and the UI layer (where units are displayed and used).
 * 
 * The data layer follows Clean Architecture principles by providing a simple
 * interface for accessing domain entities without exposing domain logic
 * directly to the UI layer.
 * 
 * Default unit selections are chosen to provide a good user experience:
 * - Default "from" unit: Kilogram per cubic meter (kg/m³) - the base SI unit
 * - Default "to" unit: Gram per cubic centimeter (g/cm³) - commonly used in science
 * 
 * @see DensityUnit
 * @see com.example.converter_android.features.density.ui.DensityViewModel
 */
object DensityUnits {
	/**
	 * Returns all available density units as a list.
	 * 
	 * This method provides access to all density units defined in the
	 * [DensityUnit] enum. The list is used to populate dropdown menus
	 * and unit selection UI components.
	 * 
	 * @return A list containing all density units in enum definition order
	 */
	fun getAllUnits(): List<DensityUnit> {
		return DensityUnit.values().toList()
	}

	/**
	 * Returns the default source unit for density conversion.
	 * 
	 * This unit is used as the initial "from" unit when the conversion
	 * screen is first displayed. Kilogram per cubic meter (kg/m³) is chosen as the default because:
	 * - It is the base SI unit for density
	 * - It provides a good reference point for conversions
	 * 
	 * @return The default source unit: [DensityUnit.KILOGRAM_PER_CUBIC_METER]
	 */
	fun getDefaultFromUnit(): DensityUnit = DensityUnit.KILOGRAM_PER_CUBIC_METER

	/**
	 * Returns the default target unit for density conversion.
	 * 
	 * This unit is used as the initial "to" unit when the conversion
	 * screen is first displayed. Gram per cubic centimeter (g/cm³) is chosen as the default because:
	 * - It is commonly used in science and chemistry
	 * - It provides a good contrast to kg/m³ (1000x larger)
	 * - It demonstrates the conversion functionality effectively
	 * 
	 * @return The default target unit: [DensityUnit.GRAM_PER_CUBIC_CENTIMETER]
	 */
	fun getDefaultToUnit(): DensityUnit = DensityUnit.GRAM_PER_CUBIC_CENTIMETER
}

