package com.forestmusic.converter_android.features.power.data

import com.forestmusic.converter_android.features.power.domain.ConvertPowerUseCase
import com.forestmusic.converter_android.features.power.domain.models.PowerUnit

/**
 * Data layer converter for power units.
 * 
 * This class acts as a wrapper around the domain layer's [ConvertPowerUseCase],
 * providing a clean interface for the UI layer to perform power conversions
 * without directly depending on domain use cases.
 * 
 * Following Clean Architecture principles, this class:
 * - Hides domain implementation details from the UI layer
 * - Provides a simple, focused interface for power conversion
 * - Allows for easy testing and mocking in the UI layer
 * - Maintains separation of concerns between layers
 * 
 * The converter delegates all conversion logic to the domain use case,
 * ensuring that business rules and conversion algorithms remain in the
 * domain layer where they belong.
 * 
 * @see ConvertPowerUseCase
 * @see PowerUnit
 * @see com.forestmusic.converter_android.features.power.ui.PowerViewModel
 */
class PowerConverter {
	/**
	 * The domain use case that performs the actual power conversion.
	 * 
	 * This is initialized once when the converter is created and reused
	 * for all conversion operations, ensuring efficient memory usage.
	 */
	private val convertPowerUseCase = ConvertPowerUseCase()

	/**
	 * Converts a power value from one unit to another.
	 * 
	 * This method provides a simple interface for converting power values
	 * between different units. It delegates the actual conversion logic to
	 * the domain use case, maintaining proper layer separation.
	 *
	 * @param value The power value to convert. Can be any positive or negative number.
	 * @param from The source power unit from which to convert
	 * @param to The target power unit to which to convert
	 * @return The converted power value in the target unit
	 */
	fun convert(value: Double, from: PowerUnit, to: PowerUnit): Double {
		return convertPowerUseCase.convert(value, from, to)
	}
}

