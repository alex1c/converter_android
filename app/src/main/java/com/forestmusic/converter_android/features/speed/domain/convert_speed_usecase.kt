package com.forestmusic.converter_android.features.speed.domain

import com.forestmusic.converter_android.core.converters.UnitConverter
import com.forestmusic.converter_android.features.speed.domain.models.SpeedUnit

/**
 * Use case for converting speed values between different speed units.
 * 
 * This class implements the universal [UnitConverter] interface for [SpeedUnit],
 * providing a standardized way to convert speed measurements across different units.
 * 
 * The conversion process follows a two-step approach:
 * 1. Convert the input value from the source unit to the base unit (meters per second)
 * 2. Convert the value from the base unit to the target unit
 * 
 * This approach ensures accuracy and consistency, as all conversions are performed
 * relative to a single base unit (m/s), eliminating potential rounding errors
 * that could occur with direct unit-to-unit conversions.
 * 
 * Supported units:
 * - Meter per second (m/s) - base unit
 * - Kilometer per hour (km/h) - ≈ 0.277778 m/s
 * - Mile per hour (mph) - ≈ 0.44704 m/s
 * - Knot (kn) - ≈ 0.514444 m/s
 * - Foot per second (ft/s) - 0.3048 m/s
 * 
 * @see UnitConverter
 * @see SpeedUnit
 */
class ConvertSpeedUseCase : UnitConverter<SpeedUnit> {
	/**
	 * Converts a speed value from one unit to another.
	 * 
	 * The conversion is performed through a two-step process:
	 * 1. Convert the value to meters per second (base unit) by multiplying by the source unit's conversion factor
	 * 2. Convert from meters per second to the target unit by dividing by the target unit's conversion factor
	 * 
	 * If the source and target units are the same, the value is returned unchanged
	 * without any calculation, optimizing performance for no-op conversions.
	 *
	 * @param value The speed value to convert. Can be any positive or negative number.
	 * @param from The source speed unit from which to convert
	 * @param to The target speed unit to which to convert
	 * @return The converted speed value in the target unit
	 */
	override fun convert(value: Double, from: SpeedUnit, to: SpeedUnit): Double {
		if (from == to) {
			return value
		}

		// Step 1: Convert to base unit (meters per second)
		val valueInMps = value * from.conversionFactorToMps

		// Step 2: Convert from base unit to target unit
		return valueInMps / to.conversionFactorToMps
	}
}

