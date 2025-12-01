package com.example.converter_android.features.time.domain

import com.example.converter_android.core.converters.UnitConverter
import com.example.converter_android.features.time.domain.models.TimeUnit

/**
 * Use case for converting time values between different time units.
 * 
 * This class implements the universal [UnitConverter] interface for [TimeUnit],
 * providing a standardized way to convert time measurements across different units.
 * 
 * The conversion process follows a two-step approach:
 * 1. Convert the input value from the source unit to the base unit (seconds)
 * 2. Convert the value from the base unit to the target unit
 * 
 * This approach ensures accuracy and consistency, as all conversions are performed
 * relative to a single base unit (s), eliminating potential rounding errors
 * that could occur with direct unit-to-unit conversions.
 * 
 * Supported units:
 * - Millisecond (ms) - 0.001 s
 * - Second (s) - base unit
 * - Minute (min) - 60 s
 * - Hour (h) - 3600 s
 * - Day (d) - 86400 s
 * - Week (wk) - 604800 s
 * - Month (mo) - ≈ 2,629,746 s (average)
 * - Year (yr) - ≈ 31,556,952 s (average)
 * 
 * Note: Month and year use average values. Actual lengths vary due to calendar differences.
 * 
 * @see UnitConverter
 * @see TimeUnit
 */
class ConvertTimeUseCase : UnitConverter<TimeUnit> {
	/**
	 * Converts a time value from one unit to another.
	 * 
	 * The conversion is performed through a two-step process:
	 * 1. Convert the value to seconds (base unit) by multiplying by the source unit's conversion factor
	 * 2. Convert from seconds to the target unit by dividing by the target unit's conversion factor
	 * 
	 * If the source and target units are the same, the value is returned unchanged
	 * without any calculation, optimizing performance for no-op conversions.
	 *
	 * @param value The time value to convert. Can be any positive or negative number.
	 * @param from The source time unit from which to convert
	 * @param to The target time unit to which to convert
	 * @return The converted time value in the target unit
	 */
	override fun convert(value: Double, from: TimeUnit, to: TimeUnit): Double {
		if (from == to) {
			return value
		}

		// Step 1: Convert to base unit (seconds)
		val valueInSeconds = value * from.conversionFactorToSecond

		// Step 2: Convert from base unit to target unit
		return valueInSeconds / to.conversionFactorToSecond
	}
}

