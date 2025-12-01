package com.example.converter_android.features.length.data

import com.example.converter_android.features.length.domain.ConvertLengthUseCase
import com.example.converter_android.features.length.domain.models.LengthUnit

/**
 * Data layer converter for length units.
 * 
 * This class acts as a wrapper around the domain layer's [ConvertLengthUseCase],
 * providing a clean interface for the UI layer to perform length conversions
 * without directly depending on domain use cases.
 * 
 * Following Clean Architecture principles, this class:
 * - Hides domain implementation details from the UI layer
 * - Provides a simple, focused interface for length conversion
 * - Allows for easy testing and mocking in the UI layer
 * - Maintains separation of concerns between layers
 * 
 * The converter delegates all conversion logic to the domain use case,
 * ensuring that business rules and conversion algorithms remain in the
 * domain layer where they belong.
 * 
 * @see ConvertLengthUseCase
 * @see LengthUnit
 * @see com.example.converter_android.features.length.ui.LengthViewModel
 */
class LengthConverter {
	/**
	 * The domain use case that performs the actual length conversion.
	 * 
	 * This is initialized once when the converter is created and reused
	 * for all conversion operations, ensuring efficient memory usage.
	 */
	private val convertLengthUseCase = ConvertLengthUseCase()

	/**
	 * Converts a length value from one unit to another.
	 * 
	 * This method provides a simple interface for converting length values
	 * between different units. It delegates the actual conversion logic to
	 * the domain use case, maintaining proper layer separation.
	 *
	 * @param value The length value to convert. Can be any positive or negative number.
	 * @param from The source length unit from which to convert
	 * @param to The target length unit to which to convert
	 * @return The converted length value in the target unit
	 * 
	 * @example
	 * val converter = LengthConverter()
	 * // Convert 1.5 kilometers to meters
	 * val result = converter.convert(1.5, LengthUnit.KILOMETER, LengthUnit.METER) // 1500.0
	 */
	fun convert(value: Double, from: LengthUnit, to: LengthUnit): Double {
		return convertLengthUseCase.convert(value, from, to)
	}
}

