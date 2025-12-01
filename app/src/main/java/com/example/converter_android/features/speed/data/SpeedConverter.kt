package com.example.converter_android.features.speed.data

import com.example.converter_android.features.speed.domain.ConvertSpeedUseCase
import com.example.converter_android.features.speed.domain.models.SpeedUnit

/**
 * Data layer converter for speed units.
 * 
 * This class acts as a wrapper around the domain layer's [ConvertSpeedUseCase],
 * providing a clean interface for the UI layer to perform speed conversions
 * without directly depending on domain use cases.
 * 
 * Following Clean Architecture principles, this class:
 * - Hides domain implementation details from the UI layer
 * - Provides a simple, focused interface for speed conversion
 * - Allows for easy testing and mocking in the UI layer
 * - Maintains separation of concerns between layers
 * 
 * The converter delegates all conversion logic to the domain use case,
 * ensuring that business rules and conversion algorithms remain in the
 * domain layer where they belong.
 * 
 * @see ConvertSpeedUseCase
 * @see SpeedUnit
 * @see com.example.converter_android.features.speed.ui.SpeedViewModel
 */
class SpeedConverter {
	/**
	 * The domain use case that performs the actual speed conversion.
	 * 
	 * This is initialized once when the converter is created and reused
	 * for all conversion operations, ensuring efficient memory usage.
	 */
	private val convertSpeedUseCase = ConvertSpeedUseCase()

	/**
	 * Converts a speed value from one unit to another.
	 * 
	 * This method provides a simple interface for converting speed values
	 * between different units. It delegates the actual conversion logic to
	 * the domain use case, maintaining proper layer separation.
	 *
	 * @param value The speed value to convert. Can be any positive or negative number.
	 * @param from The source speed unit from which to convert
	 * @param to The target speed unit to which to convert
	 * @return The converted speed value in the target unit
	 */
	fun convert(value: Double, from: SpeedUnit, to: SpeedUnit): Double {
		return convertSpeedUseCase.convert(value, from, to)
	}
}

