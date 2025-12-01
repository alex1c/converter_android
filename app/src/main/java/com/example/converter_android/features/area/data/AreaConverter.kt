package com.example.converter_android.features.area.data

import com.example.converter_android.features.area.domain.ConvertAreaUseCase
import com.example.converter_android.features.area.domain.models.AreaUnit

/**
 * Data layer converter for area units.
 * 
 * This class acts as a wrapper around the domain layer's [ConvertAreaUseCase],
 * providing a clean interface for the UI layer to perform area conversions
 * without directly depending on domain use cases.
 * 
 * Following Clean Architecture principles, this class:
 * - Hides domain implementation details from the UI layer
 * - Provides a simple, focused interface for area conversion
 * - Allows for easy testing and mocking in the UI layer
 * - Maintains separation of concerns between layers
 * 
 * The converter delegates all conversion logic to the domain use case,
 * ensuring that business rules and conversion algorithms remain in the
 * domain layer where they belong.
 * 
 * @see ConvertAreaUseCase
 * @see AreaUnit
 * @see com.example.converter_android.features.area.ui.AreaViewModel
 */
class AreaConverter {
	/**
	 * The domain use case that performs the actual area conversion.
	 * 
	 * This is initialized once when the converter is created and reused
	 * for all conversion operations, ensuring efficient memory usage.
	 */
	private val convertAreaUseCase = ConvertAreaUseCase()

	/**
	 * Converts an area value from one unit to another.
	 * 
	 * This method provides a simple interface for converting area values
	 * between different units. It delegates the actual conversion logic to
	 * the domain use case, maintaining proper layer separation.
	 *
	 * @param value The area value to convert. Can be any positive or negative number.
	 * @param from The source area unit from which to convert
	 * @param to The target area unit to which to convert
	 * @return The converted area value in the target unit
	 */
	fun convert(value: Double, from: AreaUnit, to: AreaUnit): Double {
		return convertAreaUseCase.convert(value, from, to)
	}
}

