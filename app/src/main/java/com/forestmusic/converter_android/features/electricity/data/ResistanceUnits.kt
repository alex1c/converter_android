package com.forestmusic.converter_android.features.electricity.data

import com.forestmusic.converter_android.features.electricity.domain.models.ResistanceUnit

/**
 * Data layer representation of resistance units.
 * 
 * This object provides access to resistance units and default unit selections
 * for the UI layer. It acts as a bridge between the domain layer (where
 * units are defined) and the UI layer (where units are displayed and used).
 * 
 * The data layer follows Clean Architecture principles by providing a simple
 * interface for accessing domain entities without exposing domain logic
 * directly to the UI layer.
 * 
 * Default unit selections are chosen to provide a good user experience:
 * - Default "from" unit: Ohm (Ω) - the most commonly used base unit
 * - Default "to" unit: Kiloohm (kΩ) - a common larger unit for comparison
 */
object ResistanceUnits {
	/**
	 * Returns all available resistance units as a list.
	 * 
	 * This method provides access to all resistance units defined in the
	 * [ResistanceUnit] enum. The list is used to populate dropdown menus
	 * and unit selection UI components.
	 * 
	 * @return A list containing all resistance units in the order they are
	 *         defined in the enum (OHM, KILOOHM, MEGAOHM)
	 * 
	 * @example
	 * val units = ResistanceUnits.getAllUnits()
	 * // Returns: [OHM, KILOOHM, MEGAOHM]
	 */
	fun getAllUnits(): List<ResistanceUnit> = ResistanceUnit.values().toList()
	
	/**
	 * Returns the default source unit for resistance conversion.
	 * 
	 * This unit is used as the initial "from" unit when the conversion
	 * screen is first displayed. Ohm (Ω) is chosen as the default because
	 * it is the base SI unit and the most commonly used unit for resistance
	 * measurements in everyday applications.
	 * 
	 * @return The default source unit: [ResistanceUnit.OHM]
	 */
	fun getDefaultFromUnit(): ResistanceUnit = ResistanceUnit.OHM
	
	/**
	 * Returns the default target unit for resistance conversion.
	 * 
	 * This unit is used as the initial "to" unit when the conversion
	 * screen is first displayed. Kiloohm (kΩ) is chosen as the default
	 * because it provides a good contrast to ohms and is commonly used
	 * in electronics and circuit design.
	 * 
	 * @return The default target unit: [ResistanceUnit.KILOOHM]
	 */
	fun getDefaultToUnit(): ResistanceUnit = ResistanceUnit.KILOOHM
}

