package com.example.converter_android.features.electricity.data

import com.example.converter_android.features.electricity.domain.ConvertCurrentUseCase
import com.example.converter_android.features.electricity.domain.models.CurrentUnit

/**
 * Data layer converter for current units.
 * 
 * This class acts as a wrapper around the domain layer's [ConvertCurrentUseCase],
 * providing a clean interface for the UI layer to perform current conversions
 * without directly depending on domain use cases.
 * 
 * Following Clean Architecture principles, this class:
 * - Hides domain implementation details from the UI layer
 * - Provides a simple, focused interface for current conversion
 * - Allows for easy testing and mocking in the UI layer
 * - Maintains separation of concerns between layers
 * 
 * The converter delegates all conversion logic to the domain use case,
 * ensuring that business rules and conversion algorithms remain in the
 * domain layer where they belong.
 * 
 * @see ConvertCurrentUseCase
 * @see CurrentUnit
 */
class CurrentConverter {
	/**
	 * The domain use case that performs the actual current conversion.
	 * 
	 * This is initialized once when the converter is created and reused
	 * for all conversion operations, ensuring efficient memory usage.
	 */
	private val convertCurrentUseCase = ConvertCurrentUseCase()

	/**
	 * Converts a current value from one unit to another.
	 * 
	 * This method provides a simple interface for converting current values
	 * between different units. It delegates the actual conversion logic to
	 * the domain use case, maintaining proper layer separation.
	 *
	 * @param value The current value to convert. Can be any positive or negative number.
	 * @param from The source current unit from which to convert
	 * @param to The target current unit to which to convert
	 * @return The converted current value in the target unit
	 * 
	 * @example
	 * val converter = CurrentConverter()
	 * // Convert 2.5 amperes to milliamperes
	 * val result = converter.convert(2.5, CurrentUnit.AMPERE, CurrentUnit.MILLIAMPERE) // 2500.0
	 */
	fun convert(value: Double, from: CurrentUnit, to: CurrentUnit): Double {
		return convertCurrentUseCase.convert(value, from, to)
	}
}

