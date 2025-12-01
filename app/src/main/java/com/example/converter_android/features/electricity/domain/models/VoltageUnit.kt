package com.example.converter_android.features.electricity.domain.models

/**
 * Enum representing voltage units for electrical voltage measurements.
 * 
 * Voltage (also called electric potential difference or electromotive force)
 * is the difference in electric potential between two points in an electric circuit.
 * It is measured in volts (V) in the International System of Units (SI).
 * 
 * This enum provides three common voltage units:
 * - VOLT: The base SI unit (1 V = 1 J/C, where J is joules and C is coulombs)
 * - MILLIVOLT: One thousandth of a volt (1 mV = 0.001 V)
 * - KILOVOLT: One thousand volts (1 kV = 1000 V)
 * 
 * All conversions are performed relative to volts as the base unit.
 * To convert from any unit to volts, multiply by the conversion factor.
 * To convert from volts to any unit, divide by the conversion factor.
 * 
 * @property displayNameRes Resource ID for the localized display name of the unit
 * @property conversionFactorToVolt Conversion factor to convert this unit to volts
 * 
 * @example
 * // Convert 5 kilovolts to volts
 * val volts = 5.0 * VoltageUnit.KILOVOLT.conversionFactorToVolt // 5000.0 V
 * 
 * // Convert 5000 millivolts to volts
 * val volts = 5000.0 * VoltageUnit.MILLIVOLT.conversionFactorToVolt // 5.0 V
 */
enum class VoltageUnit(
	/**
	 * Resource ID for the localized display name of this voltage unit.
	 * Used in UI to display the unit name in the user's language.
	 */
	val displayNameRes: Int,
	
	/**
	 * Conversion factor to convert this unit to volts (the base unit).
	 * 
	 * To convert a value from this unit to volts:
	 * valueInVolts = valueInThisUnit * conversionFactorToVolt
	 * 
	 * To convert a value from volts to this unit:
	 * valueInThisUnit = valueInVolts / conversionFactorToVolt
	 */
	val conversionFactorToVolt: Double
) {
	/**
	 * Volt (V) - The base SI unit for electric potential difference.
	 * 
	 * One volt is defined as the electric potential difference between two points
	 * of a conducting wire when an electric current of one ampere dissipates one watt
	 * of power between those points.
	 * 
	 * Conversion factor: 1.0 (base unit, no conversion needed)
	 */
	VOLT(com.example.converter_android.R.string.unit_volt, 1.0),
	
	/**
	 * Millivolt (mV) - One thousandth of a volt.
	 * 
	 * Commonly used for small voltage measurements in electronics, sensors,
	 * and low-voltage circuits.
	 * 
	 * Conversion factor: 0.001 (1 mV = 0.001 V)
	 * 
	 * @example
	 * 1000 mV = 1 V
	 * 500 mV = 0.5 V
	 */
	MILLIVOLT(com.example.converter_android.R.string.unit_millivolt, 0.001),
	
	/**
	 * Kilovolt (kV) - One thousand volts.
	 * 
	 * Commonly used for high-voltage measurements in power transmission,
	 * electrical distribution systems, and industrial applications.
	 * 
	 * Conversion factor: 1000.0 (1 kV = 1000 V)
	 * 
	 * @example
	 * 1 kV = 1000 V
	 * 0.5 kV = 500 V
	 */
	KILOVOLT(com.example.converter_android.R.string.unit_kilovolt, 1000.0)
}

