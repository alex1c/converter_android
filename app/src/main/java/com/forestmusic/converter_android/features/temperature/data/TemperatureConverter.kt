package com.forestmusic.converter_android.features.temperature.data

import com.forestmusic.converter_android.features.temperature.domain.ConvertTemperatureUseCase
import com.forestmusic.converter_android.features.temperature.domain.models.TemperatureUnit

/**
 * Data layer converter for temperature units.
 * 
 * This class acts as a wrapper around the domain layer's [ConvertTemperatureUseCase],
 * providing a clean interface for the UI layer to perform temperature conversions
 * without directly depending on domain use cases.
 * 
 * Following Clean Architecture principles, this class:
 * - Hides domain implementation details from the UI layer
 * - Provides a simple, focused interface for temperature conversion
 * - Allows for easy testing and mocking in the UI layer
 * - Maintains separation of concerns between layers
 * 
 * The converter delegates all conversion logic to the domain use case,
 * ensuring that business rules and conversion algorithms remain in the
 * domain layer where they belong.
 * 
 * Note: Temperature conversion is special because it requires non-linear
 * formulas (accounting for different zero points), but this complexity
 * is hidden in the domain layer.
 * 
 * @see ConvertTemperatureUseCase
 * @see TemperatureUnit
 * @see com.forestmusic.converter_android.features.temperature.ui.TemperatureViewModel
 */
class TemperatureConverter {
	/**
	 * The domain use case that performs the actual temperature conversion.
	 * 
	 * This is initialized once when the converter is created and reused
	 * for all conversion operations, ensuring efficient memory usage.
	 */
	private val convertTemperatureUseCase = ConvertTemperatureUseCase()

	/**
	 * Converts a temperature value from one unit to another.
	 * 
	 * This method provides a simple interface for converting temperature values
	 * between different units. It delegates the actual conversion logic to
	 * the domain use case, maintaining proper layer separation.
	 * 
	 * Note: Unlike most other conversions, temperature conversion uses special
	 * formulas that account for different zero points and scale differences.
	 * This complexity is handled in the domain layer.
	 *
	 * @param value The temperature value to convert. Can be any number (including negative).
	 * @param from The source temperature unit from which to convert
	 * @param to The target temperature unit to which to convert
	 * @return The converted temperature value in the target unit
	 */
	fun convert(value: Double, from: TemperatureUnit, to: TemperatureUnit): Double {
		return convertTemperatureUseCase.convert(value, from, to)
	}
}

