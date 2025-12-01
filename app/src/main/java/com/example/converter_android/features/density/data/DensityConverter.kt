package com.example.converter_android.features.density.data

import com.example.converter_android.features.density.domain.ConvertDensityUseCase
import com.example.converter_android.features.density.domain.models.DensityUnit

/**
 * Data layer converter for density units.
 * 
 * This class acts as a wrapper around the domain layer's [ConvertDensityUseCase],
 * providing a clean interface for the UI layer to perform density conversions
 * without directly depending on domain use cases.
 * 
 * Following Clean Architecture principles, this class:
 * - Hides domain implementation details from the UI layer
 * - Provides a simple, focused interface for density conversion
 * - Allows for easy testing and mocking in the UI layer
 * - Maintains separation of concerns between layers
 * 
 * The converter delegates all conversion logic to the domain use case,
 * ensuring that business rules and conversion algorithms remain in the
 * domain layer where they belong.
 * 
 * @see ConvertDensityUseCase
 * @see DensityUnit
 * @see com.example.converter_android.features.density.ui.DensityViewModel
 */
class DensityConverter {
	/**
	 * The domain use case that performs the actual density conversion.
	 * 
	 * This is initialized once when the converter is created and reused
	 * for all conversion operations, ensuring efficient memory usage.
	 */
	private val convertDensityUseCase = ConvertDensityUseCase()

	/**
	 * Converts a density value from one unit to another.
	 * 
	 * This method provides a simple interface for converting density values
	 * between different units. It delegates the actual conversion logic to
	 * the domain use case, maintaining proper layer separation.
	 *
	 * @param value The density value to convert. Can be any positive or negative number.
	 * @param from The source density unit from which to convert
	 * @param to The target density unit to which to convert
	 * @return The converted density value in the target unit
	 */
	fun convert(value: Double, from: DensityUnit, to: DensityUnit): Double {
		return convertDensityUseCase.convert(value, from, to)
	}
}

