package com.forestmusic.converter_android.features.time.data

import com.forestmusic.converter_android.features.time.domain.ConvertTimeUseCase
import com.forestmusic.converter_android.features.time.domain.models.TimeUnit

/**
 * Data layer converter for time units.
 * 
 * This class acts as a wrapper around the domain layer's [ConvertTimeUseCase],
 * providing a clean interface for the UI layer to perform time conversions
 * without directly depending on domain use cases.
 * 
 * Following Clean Architecture principles, this class:
 * - Hides domain implementation details from the UI layer
 * - Provides a simple, focused interface for time conversion
 * - Allows for easy testing and mocking in the UI layer
 * - Maintains separation of concerns between layers
 * 
 * The converter delegates all conversion logic to the domain use case,
 * ensuring that business rules and conversion algorithms remain in the
 * domain layer where they belong.
 * 
 * @see ConvertTimeUseCase
 * @see TimeUnit
 * @see com.forestmusic.converter_android.features.time.ui.TimeViewModel
 */
class TimeConverter {
	/**
	 * The domain use case that performs the actual time conversion.
	 * 
	 * This is initialized once when the converter is created and reused
	 * for all conversion operations, ensuring efficient memory usage.
	 */
	private val convertTimeUseCase = ConvertTimeUseCase()

	/**
	 * Converts a time value from one unit to another.
	 * 
	 * This method provides a simple interface for converting time values
	 * between different units. It delegates the actual conversion logic to
	 * the domain use case, maintaining proper layer separation.
	 *
	 * @param value The time value to convert. Can be any positive or negative number.
	 * @param from The source time unit from which to convert
	 * @param to The target time unit to which to convert
	 * @return The converted time value in the target unit
	 */
	fun convert(value: Double, from: TimeUnit, to: TimeUnit): Double {
		return convertTimeUseCase.convert(value, from, to)
	}
}

