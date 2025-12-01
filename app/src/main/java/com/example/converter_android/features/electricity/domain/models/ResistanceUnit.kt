package com.example.converter_android.features.electricity.domain.models

/**
 * Enum representing electrical resistance units for electrical resistance measurements.
 * 
 * Electrical resistance is a measure of the opposition to the flow of electric current
 * through a conductor. It is measured in ohms (Ω) in the International System of Units (SI).
 * 
 * Resistance is defined by Ohm's law: R = V / I, where:
 * - R is resistance in ohms
 * - V is voltage in volts
 * - I is current in amperes
 * 
 * This enum provides three common resistance units:
 * - OHM: The base SI unit (1 Ω = 1 V/A)
 * - KILOOHM: One thousand ohms (1 kΩ = 1000 Ω)
 * - MEGAOHM: One million ohms (1 MΩ = 1,000,000 Ω)
 * 
 * All conversions are performed relative to ohms as the base unit.
 * To convert from any unit to ohms, multiply by the conversion factor.
 * To convert from ohms to any unit, divide by the conversion factor.
 * 
 * @property displayNameRes Resource ID for the localized display name of the unit
 * @property conversionFactorToOhm Conversion factor to convert this unit to ohms
 * 
 * @example
 * // Convert 5 kiloohms to ohms
 * val ohms = 5.0 * ResistanceUnit.KILOOHM.conversionFactorToOhm // 5000.0 Ω
 * 
 * // Convert 2 megaohms to ohms
 * val ohms = 2.0 * ResistanceUnit.MEGAOHM.conversionFactorToOhm // 2,000,000.0 Ω
 */
enum class ResistanceUnit(
	/**
	 * Resource ID for the localized display name of this resistance unit.
	 * Used in UI to display the unit name in the user's language.
	 */
	val displayNameRes: Int,
	
	/**
	 * Conversion factor to convert this unit to ohms (the base unit).
	 * 
	 * To convert a value from this unit to ohms:
	 * valueInOhms = valueInThisUnit * conversionFactorToOhm
	 * 
	 * To convert a value from ohms to this unit:
	 * valueInThisUnit = valueInOhms / conversionFactorToOhm
	 */
	val conversionFactorToOhm: Double
) {
	/**
	 * Ohm (Ω) - The base SI unit for electrical resistance.
	 * 
	 * One ohm is defined as the resistance between two points of a conductor
	 * when a constant potential difference of one volt, applied to these points,
	 * produces in the conductor a current of one ampere.
	 * 
	 * Conversion factor: 1.0 (base unit, no conversion needed)
	 */
	OHM(com.forestmusic.converter_android.R.string.unit_ohm, 1.0),
	
	/**
	 * Kiloohm (kΩ) - One thousand ohms.
	 * 
	 * Commonly used for medium to high resistance measurements in electronics,
	 * resistors, and electrical circuits.
	 * 
	 * Conversion factor: 1000.0 (1 kΩ = 1000 Ω)
	 * 
	 * @example
	 * 1 kΩ = 1000 Ω
	 * 5 kΩ = 5000 Ω
	 */
	KILOOHM(com.forestmusic.converter_android.R.string.unit_kiloohm, 1000.0),
	
	/**
	 * Megaohm (MΩ) - One million ohms.
	 * 
	 * Commonly used for very high resistance measurements in insulation testing,
	 * high-impedance circuits, and electronic components with very low leakage current.
	 * 
	 * Conversion factor: 1,000,000.0 (1 MΩ = 1,000,000 Ω)
	 * 
	 * @example
	 * 1 MΩ = 1,000,000 Ω
	 * 0.5 MΩ = 500,000 Ω
	 */
	MEGAOHM(com.forestmusic.converter_android.R.string.unit_megaohm, 1_000_000.0)
}

