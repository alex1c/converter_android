package com.forestmusic.converter_android.features.angle.data

import com.forestmusic.converter_android.features.angle.domain.ConvertAngleUseCase
import com.forestmusic.converter_android.features.angle.domain.models.AngleUnit

/**
 * Data layer converter for angle units.
 * 
 * This class acts as a wrapper around the domain layer's [ConvertAngleUseCase],
 * providing a clean interface for the UI layer to perform angle conversions
 * without directly depending on domain use cases.
 * 
 * Following Clean Architecture principles, this class:
 * - Hides domain implementation details from the UI layer
 * - Provides a simple, focused interface for angle conversion
 * - Allows for easy testing and mocking in the UI layer
 * - Maintains separation of concerns between layers
 * 
 * The converter delegates all conversion logic to the domain use case,
 * ensuring that business rules and conversion algorithms remain in the
 * domain layer where they belong.
 * 
 * @see ConvertAngleUseCase
 * @see AngleUnit
 * @see com.forestmusic.converter_android.features.angle.ui.AngleViewModel
 */
class AngleConverter {
	/**
	 * The domain use case that performs the actual angle conversion.
	 * 
	 * This is initialized once when the converter is created and reused
	 * for all conversion operations, ensuring efficient memory usage.
	 */
	private val convertAngleUseCase = ConvertAngleUseCase()

	/**
	 * Converts an angle value from one unit to another.
	 * 
	 * This method provides a simple interface for converting angle values
	 * between different units. It delegates the actual conversion logic to
	 * the domain use case, maintaining proper layer separation.
	 *
	 * @param value The angle value to convert. Can be any positive or negative number.
	 * @param from The source angle unit from which to convert
	 * @param to The target angle unit to which to convert
	 * @return The converted angle value in the target unit
	 */
	fun convert(value: Double, from: AngleUnit, to: AngleUnit): Double {
		return convertAngleUseCase.convert(value, from, to)
	}
}

