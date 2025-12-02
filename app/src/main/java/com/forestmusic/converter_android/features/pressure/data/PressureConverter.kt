package com.forestmusic.converter_android.features.pressure.data

import com.forestmusic.converter_android.features.pressure.domain.ConvertPressureUseCase
import com.forestmusic.converter_android.features.pressure.domain.models.PressureUnit

/**
 * Data layer converter for pressure units.
 * 
 * This class acts as a wrapper around the domain layer's [ConvertPressureUseCase],
 * providing a clean interface for the UI layer to perform pressure conversions
 * without directly depending on domain use cases.
 * 
 * Following Clean Architecture principles, this class:
 * - Hides domain implementation details from the UI layer
 * - Provides a simple, focused interface for pressure conversion
 * - Allows for easy testing and mocking in the UI layer
 * - Maintains separation of concerns between layers
 * 
 * The converter delegates all conversion logic to the domain use case,
 * ensuring that business rules and conversion algorithms remain in the
 * domain layer where they belong.
 * 
 * @see ConvertPressureUseCase
 * @see PressureUnit
 * @see com.forestmusic.converter_android.features.pressure.ui.PressureViewModel
 */
class PressureConverter {
	/**
	 * The domain use case that performs the actual pressure conversion.
	 * 
	 * This is initialized once when the converter is created and reused
	 * for all conversion operations, ensuring efficient memory usage.
	 */
	private val convertPressureUseCase = ConvertPressureUseCase()

	/**
	 * Converts a pressure value from one unit to another.
	 * 
	 * This method provides a simple interface for converting pressure values
	 * between different units. It delegates the actual conversion logic to
	 * the domain use case, maintaining proper layer separation.
	 *
	 * @param value The pressure value to convert. Can be any positive or negative number.
	 * @param from The source pressure unit from which to convert
	 * @param to The target pressure unit to which to convert
	 * @return The converted pressure value in the target unit
	 */
	fun convert(value: Double, from: PressureUnit, to: PressureUnit): Double {
		return convertPressureUseCase.convert(value, from, to)
	}
}

