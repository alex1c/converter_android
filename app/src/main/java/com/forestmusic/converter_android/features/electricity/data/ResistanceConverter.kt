package com.forestmusic.converter_android.features.electricity.data

import com.forestmusic.converter_android.features.electricity.domain.ConvertResistanceUseCase
import com.forestmusic.converter_android.features.electricity.domain.models.ResistanceUnit

/**
 * Data layer converter for resistance units.
 * 
 * This class acts as a wrapper around the domain layer's [ConvertResistanceUseCase],
 * providing a clean interface for the UI layer to perform resistance conversions
 * without directly depending on domain use cases.
 * 
 * Following Clean Architecture principles, this class:
 * - Hides domain implementation details from the UI layer
 * - Provides a simple, focused interface for resistance conversion
 * - Allows for easy testing and mocking in the UI layer
 * - Maintains separation of concerns between layers
 * 
 * The converter delegates all conversion logic to the domain use case,
 * ensuring that business rules and conversion algorithms remain in the
 * domain layer where they belong.
 * 
 * @see ConvertResistanceUseCase
 * @see ResistanceUnit
 */
class ResistanceConverter {
	/**
	 * The domain use case that performs the actual resistance conversion.
	 * 
	 * This is initialized once when the converter is created and reused
	 * for all conversion operations, ensuring efficient memory usage.
	 */
	private val convertResistanceUseCase = ConvertResistanceUseCase()

	/**
	 * Converts a resistance value from one unit to another.
	 * 
	 * This method provides a simple interface for converting resistance values
	 * between different units. It delegates the actual conversion logic to
	 * the domain use case, maintaining proper layer separation.
	 *
	 * @param value The resistance value to convert. Can be any positive or negative number.
	 * @param from The source resistance unit from which to convert
	 * @param to The target resistance unit to which to convert
	 * @return The converted resistance value in the target unit
	 * 
	 * @example
	 * val converter = ResistanceConverter()
	 * // Convert 5000 ohms to kiloohms
	 * val result = converter.convert(5000.0, ResistanceUnit.OHM, ResistanceUnit.KILOOHM) // 5.0
	 */
	fun convert(value: Double, from: ResistanceUnit, to: ResistanceUnit): Double {
		return convertResistanceUseCase.convert(value, from, to)
	}
}

