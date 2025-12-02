package com.forestmusic.converter_android.features.electricity.data

import com.forestmusic.converter_android.features.electricity.domain.ConvertVoltageUseCase
import com.forestmusic.converter_android.features.electricity.domain.models.VoltageUnit

/**
 * Data layer converter for voltage units.
 * 
 * This class acts as a wrapper around the domain layer's [ConvertVoltageUseCase],
 * providing a clean interface for the UI layer to perform voltage conversions
 * without directly depending on domain use cases.
 * 
 * Following Clean Architecture principles, this class:
 * - Hides domain implementation details from the UI layer
 * - Provides a simple, focused interface for voltage conversion
 * - Allows for easy testing and mocking in the UI layer
 * - Maintains separation of concerns between layers
 * 
 * The converter delegates all conversion logic to the domain use case,
 * ensuring that business rules and conversion algorithms remain in the
 * domain layer where they belong.
 * 
 * @see ConvertVoltageUseCase
 * @see VoltageUnit
 */
class VoltageConverter {
	/**
	 * The domain use case that performs the actual voltage conversion.
	 * 
	 * This is initialized once when the converter is created and reused
	 * for all conversion operations, ensuring efficient memory usage.
	 */
	private val convertVoltageUseCase = ConvertVoltageUseCase()

	/**
	 * Converts a voltage value from one unit to another.
	 * 
	 * This method provides a simple interface for converting voltage values
	 * between different units. It delegates the actual conversion logic to
	 * the domain use case, maintaining proper layer separation.
	 *
	 * @param value The voltage value to convert. Can be any positive or negative number.
	 * @param from The source voltage unit from which to convert
	 * @param to The target voltage unit to which to convert
	 * @return The converted voltage value in the target unit
	 * 
	 * @example
	 * val converter = VoltageConverter()
	 * // Convert 12.5 volts to millivolts
	 * val result = converter.convert(12.5, VoltageUnit.VOLT, VoltageUnit.MILLIVOLT) // 12500.0
	 */
	fun convert(value: Double, from: VoltageUnit, to: VoltageUnit): Double {
		return convertVoltageUseCase.convert(value, from, to)
	}
}

