package com.example.converter_android.features.temperature.domain.models

/**
 * Enum representing temperature units for temperature measurements.
 * 
 * Temperature is a physical quantity that expresses the degree of hotness or coldness
 * of a substance. Unlike most other physical quantities, temperature conversion
 * requires special formulas because temperature scales have different zero points
 * and different degrees of separation between units.
 * 
 * This enum provides three common temperature units used in science, everyday life,
 * and industry:
 * - CELSIUS: The metric unit, based on the freezing and boiling points of water
 * - FAHRENHEIT: The imperial unit, commonly used in the United States
 * - KELVIN: The SI base unit for thermodynamic temperature, used in science
 * 
 * Temperature conversion formulas:
 * - Celsius to Fahrenheit: F = C × 9/5 + 32
 * - Fahrenheit to Celsius: C = (F - 32) × 5/9
 * - Celsius to Kelvin: K = C + 273.15
 * - Kelvin to Celsius: C = K - 273.15
 * 
 * All conversions in this application are performed through Celsius as the base unit.
 * This means:
 * 1. Convert from source unit to Celsius
 * 2. Convert from Celsius to target unit
 * 
 * Important notes:
 * - Temperature conversion is NOT linear (unlike most other unit conversions)
 * - Different scales have different zero points (absolute zero for Kelvin, freezing
 *   point of water for Celsius, different point for Fahrenheit)
 * - The conversion formulas account for both the scale difference and the offset
 * 
 * @property displayNameRes Resource ID for the localized display name of the unit.
 *                        Used in UI to display the unit name in the user's language.
 * 
 * @example
 * // Freezing point of water:
 * // 0°C = 32°F = 273.15 K
 * 
 * // Boiling point of water:
 * // 100°C = 212°F = 373.15 K
 * 
 * // Absolute zero:
 * // -273.15°C = -459.67°F = 0 K
 */
enum class TemperatureUnit(
	/**
	 * Resource ID for the localized display name of this temperature unit.
	 * Used in UI to display the unit name in the user's language.
	 */
	val displayNameRes: Int
) {
	/**
	 * Celsius (°C) - Metric temperature unit.
	 * 
	 * Named after Anders Celsius. The Celsius scale is based on:
	 * - 0°C: Freezing point of water (at standard atmospheric pressure)
	 * - 100°C: Boiling point of water (at standard atmospheric pressure)
	 * 
	 * Celsius is the most commonly used temperature scale worldwide and is the
	 * standard unit in the metric system. It is used in most countries for
	 * everyday temperature measurements.
	 * 
	 * Conversion formulas:
	 * - To Fahrenheit: F = C × 9/5 + 32
	 * - To Kelvin: K = C + 273.15
	 */
	CELSIUS(com.forestmusic.converter_android.R.string.unit_celsius),
	
	/**
	 * Fahrenheit (°F) - Imperial temperature unit.
	 * 
	 * Named after Daniel Gabriel Fahrenheit. The Fahrenheit scale is based on:
	 * - 32°F: Freezing point of water (at standard atmospheric pressure)
	 * - 212°F: Boiling point of water (at standard atmospheric pressure)
	 * 
	 * Fahrenheit is primarily used in the United States for everyday temperature
	 * measurements. It is also used in some Caribbean countries.
	 * 
	 * Conversion formulas:
	 * - To Celsius: C = (F - 32) × 5/9
	 * - To Kelvin: K = (F - 32) × 5/9 + 273.15
	 */
	FAHRENHEIT(com.forestmusic.converter_android.R.string.unit_fahrenheit),
	
	/**
	 * Kelvin (K) - SI base unit for thermodynamic temperature.
	 * 
	 * Named after Lord Kelvin. The Kelvin scale is an absolute temperature scale
	 * where:
	 * - 0 K: Absolute zero (the lowest possible temperature)
	 * - 273.15 K: Freezing point of water
	 * - 373.15 K: Boiling point of water
	 * 
	 * Kelvin is the SI base unit for temperature and is used in scientific
	 * applications, especially in physics and chemistry. Unlike Celsius and
	 * Fahrenheit, Kelvin does not use the degree symbol (°).
	 * 
	 * Conversion formulas:
	 * - To Celsius: C = K - 273.15
	 * - To Fahrenheit: F = (K - 273.15) × 9/5 + 32
	 */
	KELVIN(com.forestmusic.converter_android.R.string.unit_kelvin)
}

