package com.example.converter_android.features.torque.data

import com.example.converter_android.features.torque.domain.ConvertTorqueUseCase
import com.example.converter_android.features.torque.domain.models.TorqueUnit

/**
 * Data layer converter for torque units.
 * 
 * This class acts as a wrapper around the domain layer's [ConvertTorqueUseCase],
 * providing a clean interface for the UI layer to perform torque conversions
 * without directly depending on domain use cases.
 * 
 * Following Clean Architecture principles, this class:
 * - Hides domain implementation details from the UI layer
 * - Provides a simple, focused interface for torque conversion
 * - Allows for easy testing and mocking in the UI layer
 * - Maintains separation of concerns between layers
 * 
 * The converter delegates all conversion logic to the domain use case,
 * ensuring that business rules and conversion algorithms remain in the
 * domain layer where they belong.
 * 
 * @see ConvertTorqueUseCase
 * @see TorqueUnit
 * @see com.example.converter_android.features.torque.ui.TorqueViewModel
 */
class TorqueConverter {
	/**
	 * The domain use case that performs the actual torque conversion.
	 * 
	 * This is initialized once when the converter is created and reused
	 * for all conversion operations, ensuring efficient memory usage.
	 */
	private val convertTorqueUseCase = ConvertTorqueUseCase()

	/**
	 * Converts a torque value from one unit to another.
	 * 
	 * This method provides a simple interface for converting torque values
	 * between different units. It delegates the actual conversion logic to
	 * the domain use case, maintaining proper layer separation.
	 *
	 * @param value The torque value to convert. Can be any positive or negative number.
	 * @param from The source torque unit from which to convert
	 * @param to The target torque unit to which to convert
	 * @return The converted torque value in the target unit
	 */
	fun convert(value: Double, from: TorqueUnit, to: TorqueUnit): Double {
		return convertTorqueUseCase.convert(value, from, to)
	}
}

