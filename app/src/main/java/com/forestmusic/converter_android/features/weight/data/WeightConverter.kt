package com.forestmusic.converter_android.features.weight.data

import com.forestmusic.converter_android.features.weight.domain.ConvertWeightUseCase
import com.forestmusic.converter_android.features.weight.domain.models.WeightUnit

/**
 * Data layer converter for weight units.
 * 
 * This class acts as a wrapper around the domain layer's [ConvertWeightUseCase],
 * providing a clean interface for the UI layer to perform weight conversions
 * without directly depending on domain use cases.
 * 
 * Following Clean Architecture principles, this class:
 * - Hides domain implementation details from the UI layer
 * - Provides a simple, focused interface for weight conversion
 * - Allows for easy testing and mocking in the UI layer
 * - Maintains separation of concerns between layers
 * 
 * The converter delegates all conversion logic to the domain use case,
 * ensuring that business rules and conversion algorithms remain in the
 * domain layer where they belong.
 * 
 * @see ConvertWeightUseCase
 * @see WeightUnit
 * @see com.example.converter_android.features.weight.ui.WeightViewModel
 */
class WeightConverter {
	/**
	 * The domain use case that performs the actual weight conversion.
	 * 
	 * This is initialized once when the converter is created and reused
	 * for all conversion operations, ensuring efficient memory usage.
	 */
	private val convertWeightUseCase = ConvertWeightUseCase()

	/**
	 * Converts a weight value from one unit to another.
	 * 
	 * This method provides a simple interface for converting weight values
	 * between different units. It delegates the actual conversion logic to
	 * the domain use case, maintaining proper layer separation.
	 *
	 * @param value The weight value to convert. Can be any positive or negative number.
	 * @param from The source weight unit from which to convert
	 * @param to The target weight unit to which to convert
	 * @return The converted weight value in the target unit
	 * 
	 * @example
	 * val converter = WeightConverter()
	 * // Convert 2.5 kilograms to pounds
	 * val result = converter.convert(2.5, WeightUnit.KILOGRAM, WeightUnit.POUND) // ≈ 5.51156
	 */
	fun convert(value: Double, from: WeightUnit, to: WeightUnit): Double {
		return convertWeightUseCase.convert(value, from, to)
	}
}

