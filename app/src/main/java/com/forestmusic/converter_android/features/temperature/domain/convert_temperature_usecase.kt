package com.forestmusic.converter_android.features.temperature.domain

import com.forestmusic.converter_android.core.converters.UnitConverter
import com.forestmusic.converter_android.features.temperature.domain.models.TemperatureUnit

/**
 * Use case for converting temperature values between different temperature units.
 * 
 * This class implements the universal [UnitConverter] interface for [TemperatureUnit],
 * providing a standardized way to convert temperature measurements across different units.
 * 
 * Important: Temperature conversion is NOT linear like most other unit conversions.
 * Temperature scales have different zero points and different degrees of separation,
 * requiring special conversion formulas that account for both scale differences and offsets.
 * 
 * The conversion process follows a two-step approach:
 * 1. Convert the input value from the source unit to the base unit (Celsius)
 * 2. Convert the value from the base unit to the target unit
 * 
 * All conversions use Celsius as the intermediate base unit because:
 * - It's the most commonly used metric temperature unit
 * - Conversion formulas to/from Celsius are well-established
 * - It provides a good reference point (freezing/boiling point of water)
 * 
 * Supported units:
 * - Celsius (°C) - base unit
 * - Fahrenheit (°F) - F = C × 9/5 + 32
 * - Kelvin (K) - K = C + 273.15
 * 
 * Conversion formulas:
 * - Celsius ↔ Fahrenheit: Accounts for both scale (9/5) and offset (32)
 * - Celsius ↔ Kelvin: Accounts for offset only (273.15)
 * - Fahrenheit ↔ Kelvin: Two-step conversion through Celsius
 * 
 * @see UnitConverter
 * @see TemperatureUnit
 * 
 * @example
 * val useCase = ConvertTemperatureUseCase()
 * // Convert 100°C to Fahrenheit (boiling point of water)
 * val result = useCase.convert(100.0, TemperatureUnit.CELSIUS, TemperatureUnit.FAHRENHEIT) // 212.0
 * // Convert 32°F to Celsius (freezing point of water)
 * val result2 = useCase.convert(32.0, TemperatureUnit.FAHRENHEIT, TemperatureUnit.CELSIUS) // 0.0
 */
class ConvertTemperatureUseCase : UnitConverter<TemperatureUnit> {
	/**
	 * Converts a temperature value from one unit to another.
	 * 
	 * The conversion is performed through a two-step process:
	 * 1. Convert the value to Celsius (base unit) using the appropriate formula
	 * 2. Convert from Celsius to the target unit using the appropriate formula
	 * 
	 * If the source and target units are the same, the value is returned unchanged
	 * without any calculation, optimizing performance for no-op conversions.
	 * 
	 * Temperature conversion formulas:
	 * - Celsius to Fahrenheit: F = C × 9/5 + 32
	 * - Fahrenheit to Celsius: C = (F - 32) × 5/9
	 * - Celsius to Kelvin: K = C + 273.15
	 * - Kelvin to Celsius: C = K - 273.15
	 *
	 * @param value The temperature value to convert. Can be any number (including negative).
	 * @param from The source temperature unit from which to convert
	 * @param to The target temperature unit to which to convert
	 * @return The converted temperature value in the target unit
	 * 
	 * @example
	 * // Convert 0°C to Kelvin (freezing point of water)
	 * val kelvin = convert(0.0, TemperatureUnit.CELSIUS, TemperatureUnit.KELVIN) // 273.15
	 * 
	 * // Convert 98.6°F to Celsius (normal body temperature)
	 * val celsius = convert(98.6, TemperatureUnit.FAHRENHEIT, TemperatureUnit.CELSIUS) // ≈ 37.0
	 */
	override fun convert(value: Double, from: TemperatureUnit, to: TemperatureUnit): Double {
		// Optimization: if source and target units are the same, return value unchanged
		if (from == to) {
			return value
		}

		// Step 1: Convert to base unit (Celsius)
		// This handles the conversion from any unit to Celsius
		val celsius = convertToCelsius(value, from)

		// Step 2: Convert from base unit to target unit
		// This handles the conversion from Celsius to any unit
		return convertFromCelsius(celsius, to)
	}

	/**
	 * Converts any temperature unit to Celsius.
	 * 
	 * This private helper method implements the conversion formulas for converting
	 * from Fahrenheit or Kelvin to Celsius. If the source unit is already Celsius,
	 * the value is returned unchanged.
	 * 
	 * Conversion formulas:
	 * - From Celsius: No conversion needed (return as-is)
	 * - From Fahrenheit: C = (F - 32) × 5/9
	 * - From Kelvin: C = K - 273.15
	 *
	 * @param value The temperature value in the source unit
	 * @param from The source temperature unit
	 * @return The temperature value converted to Celsius
	 * 
	 * @example
	 * // Convert 212°F to Celsius
	 * val celsius = convertToCelsius(212.0, TemperatureUnit.FAHRENHEIT) // 100.0
	 * 
	 * // Convert 373.15 K to Celsius
	 * val celsius = convertToCelsius(373.15, TemperatureUnit.KELVIN) // 100.0
	 */
	private fun convertToCelsius(value: Double, from: TemperatureUnit): Double {
		return when (from) {
			TemperatureUnit.CELSIUS -> value // Already in Celsius, no conversion needed
			// Fahrenheit to Celsius: subtract 32, then multiply by 5/9
			TemperatureUnit.FAHRENHEIT -> (value - 32.0) * 5.0 / 9.0
			// Kelvin to Celsius: subtract 273.15 (the difference in zero points)
			TemperatureUnit.KELVIN -> value - 273.15
		}
	}

	/**
	 * Converts Celsius to any target temperature unit.
	 * 
	 * This private helper method implements the conversion formulas for converting
	 * from Celsius to Fahrenheit or Kelvin. If the target unit is Celsius,
	 * the value is returned unchanged.
	 * 
	 * Conversion formulas:
	 * - To Celsius: No conversion needed (return as-is)
	 * - To Fahrenheit: F = C × 9/5 + 32
	 * - To Kelvin: K = C + 273.15
	 *
	 * @param celsius The temperature value in Celsius
	 * @param to The target temperature unit
	 * @return The temperature value converted to the target unit
	 * 
	 * @example
	 * // Convert 100°C to Fahrenheit
	 * val fahrenheit = convertFromCelsius(100.0, TemperatureUnit.FAHRENHEIT) // 212.0
	 * 
	 * // Convert 0°C to Kelvin
	 * val kelvin = convertFromCelsius(0.0, TemperatureUnit.KELVIN) // 273.15
	 */
	private fun convertFromCelsius(celsius: Double, to: TemperatureUnit): Double {
		return when (to) {
			TemperatureUnit.CELSIUS -> celsius // Already in Celsius, no conversion needed
			// Celsius to Fahrenheit: multiply by 9/5, then add 32
			TemperatureUnit.FAHRENHEIT -> celsius * 9.0 / 5.0 + 32.0
			// Celsius to Kelvin: add 273.15 (the difference in zero points)
			TemperatureUnit.KELVIN -> celsius + 273.15
		}
	}
}

