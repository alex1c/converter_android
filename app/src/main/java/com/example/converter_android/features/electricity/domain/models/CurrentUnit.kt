package com.example.converter_android.features.electricity.domain.models

/**
 * Enum representing electric current units for electrical current measurements.
 * 
 * Electric current is the flow of electric charge through a conductor or circuit.
 * It is measured in amperes (A) in the International System of Units (SI).
 * 
 * This enum provides three common current units:
 * - AMPERE: The base SI unit (1 A = 1 C/s, where C is coulombs and s is seconds)
 * - MILLIAMPERE: One thousandth of an ampere (1 mA = 0.001 A)
 * - KILOAMPERE: One thousand amperes (1 kA = 1000 A)
 * 
 * All conversions are performed relative to amperes as the base unit.
 * To convert from any unit to amperes, multiply by the conversion factor.
 * To convert from amperes to any unit, divide by the conversion factor.
 * 
 * @property displayNameRes Resource ID for the localized display name of the unit
 * @property conversionFactorToAmpere Conversion factor to convert this unit to amperes
 * 
 * @example
 * // Convert 2 kiloamperes to amperes
 * val amperes = 2.0 * CurrentUnit.KILOAMPERE.conversionFactorToAmpere // 2000.0 A
 * 
 * // Convert 500 milliamperes to amperes
 * val amperes = 500.0 * CurrentUnit.MILLIAMPERE.conversionFactorToAmpere // 0.5 A
 */
enum class CurrentUnit(
	/**
	 * Resource ID for the localized display name of this current unit.
	 * Used in UI to display the unit name in the user's language.
	 */
	val displayNameRes: Int,
	
	/**
	 * Conversion factor to convert this unit to amperes (the base unit).
	 * 
	 * To convert a value from this unit to amperes:
	 * valueInAmperes = valueInThisUnit * conversionFactorToAmpere
	 * 
	 * To convert a value from amperes to this unit:
	 * valueInThisUnit = valueInAmperes / conversionFactorToAmpere
	 */
	val conversionFactorToAmpere: Double
) {
	/**
	 * Ampere (A) - The base SI unit for electric current.
	 * 
	 * One ampere is defined as the constant current that, if maintained in two
	 * straight parallel conductors of infinite length, of negligible circular
	 * cross-section, and placed one meter apart in vacuum, would produce between
	 * these conductors a force equal to 2 × 10⁻⁷ newtons per meter of length.
	 * 
	 * Conversion factor: 1.0 (base unit, no conversion needed)
	 */
	AMPERE(com.example.converter_android.R.string.unit_ampere, 1.0),
	
	/**
	 * Milliampere (mA) - One thousandth of an ampere.
	 * 
	 * Commonly used for small current measurements in electronics, battery-powered
	 * devices, sensors, and low-power circuits.
	 * 
	 * Conversion factor: 0.001 (1 mA = 0.001 A)
	 * 
	 * @example
	 * 1000 mA = 1 A
	 * 500 mA = 0.5 A
	 */
	MILLIAMPERE(com.example.converter_android.R.string.unit_milliampere, 0.001),
	
	/**
	 * Kiloampere (kA) - One thousand amperes.
	 * 
	 * Commonly used for high-current measurements in power systems, industrial
	 * equipment, electric motors, and high-power electrical installations.
	 * 
	 * Conversion factor: 1000.0 (1 kA = 1000 A)
	 * 
	 * @example
	 * 1 kA = 1000 A
	 * 0.5 kA = 500 A
	 */
	KILOAMPERE(com.example.converter_android.R.string.unit_kiloampere, 1000.0)
}

