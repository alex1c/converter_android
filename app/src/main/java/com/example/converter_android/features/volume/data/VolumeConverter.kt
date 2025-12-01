package com.example.converter_android.features.volume.data

import com.example.converter_android.features.volume.domain.ConvertVolumeUseCase
import com.example.converter_android.features.volume.domain.models.VolumeUnit

/**
 * Data layer converter for volume units.
 * 
 * This class acts as a wrapper around the domain layer's [ConvertVolumeUseCase],
 * providing a clean interface for the UI layer to perform volume conversions
 * without directly depending on domain use cases.
 * 
 * Following Clean Architecture principles, this class:
 * - Hides domain implementation details from the UI layer
 * - Provides a simple, focused interface for volume conversion
 * - Allows for easy testing and mocking in the UI layer
 * - Maintains separation of concerns between layers
 * 
 * The converter delegates all conversion logic to the domain use case,
 * ensuring that business rules and conversion algorithms remain in the
 * domain layer where they belong.
 * 
 * @see ConvertVolumeUseCase
 * @see VolumeUnit
 * @see com.example.converter_android.features.volume.ui.VolumeViewModel
 */
class VolumeConverter {
	/**
	 * The domain use case that performs the actual volume conversion.
	 * 
	 * This is initialized once when the converter is created and reused
	 * for all conversion operations, ensuring efficient memory usage.
	 */
	private val convertVolumeUseCase = ConvertVolumeUseCase()

	/**
	 * Converts a volume value from one unit to another.
	 * 
	 * This method provides a simple interface for converting volume values
	 * between different units. It delegates the actual conversion logic to
	 * the domain use case, maintaining proper layer separation.
	 *
	 * @param value The volume value to convert. Can be any positive or negative number.
	 * @param from The source volume unit from which to convert
	 * @param to The target volume unit to which to convert
	 * @return The converted volume value in the target unit
	 * 
	 * @example
	 * val converter = VolumeConverter()
	 * // Convert 2.5 liters to US gallons
	 * val result = converter.convert(2.5, VolumeUnit.LITER, VolumeUnit.GALLON_US) // ≈ 0.66043
	 */
	fun convert(value: Double, from: VolumeUnit, to: VolumeUnit): Double {
		return convertVolumeUseCase.convert(value, from, to)
	}
}

