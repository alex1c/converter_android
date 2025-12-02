package com.forestmusic.converter_android.features.power.domain

import com.forestmusic.converter_android.core.converters.UnitConverter
import com.forestmusic.converter_android.features.power.domain.models.PowerUnit

/**
 * Use case for converting power values between different power units.
 * 
 * This class implements the universal [UnitConverter] interface for [PowerUnit],
 * providing a standardized way to convert power measurements across different units.
 * 
 * The conversion process follows a two-step approach:
 * 1. Convert the input value from the source unit to the base unit (Watts)
 * 2. Convert the value from the base unit to the target unit
 * 
 * This approach ensures accuracy and consistency, as all conversions are performed
 * relative to a single base unit (W), eliminating potential rounding errors
 * that could occur with direct unit-to-unit conversions.
 * 
 * Supported units:
 * - Watt (W) - base unit
 * - Kilowatt (kW) - 1000 W
 * - Megawatt (MW) - 1,000,000 W
 * - Metric Horsepower (PS) - ≈ 735.49875 W
 * - Mechanical Horsepower (hp) - ≈ 745.699872 W
 * - BTU per hour (BTU/h) - ≈ 0.29307107 W
 * - Calorie per second (cal/s) - 4.1868 W
 * - Joule per second (J/s) - 1 W (equivalent to watt)
 * - Foot-pound per minute (ft·lb/min) - ≈ 0.0225969658 W
 * 
 * @see UnitConverter
 * @see PowerUnit
 */
class ConvertPowerUseCase : UnitConverter<PowerUnit> {
	/**
	 * Converts a power value from one unit to another.
	 * 
	 * The conversion is performed through a two-step process:
	 * 1. Convert the value to Watts (base unit) by multiplying by the source unit's conversion factor
	 * 2. Convert from Watts to the target unit by dividing by the target unit's conversion factor
	 * 
	 * If the source and target units are the same, the value is returned unchanged
	 * without any calculation, optimizing performance for no-op conversions.
	 *
	 * @param value The power value to convert. Can be any positive or negative number.
	 * @param from The source power unit from which to convert
	 * @param to The target power unit to which to convert
	 * @return The converted power value in the target unit
	 */
	override fun convert(value: Double, from: PowerUnit, to: PowerUnit): Double {
		if (from == to) {
			return value
		}

		// Step 1: Convert to base unit (Watts)
		val valueInWatts = value * from.conversionFactorToWatt

		// Step 2: Convert from base unit to target unit
		return valueInWatts / to.conversionFactorToWatt
	}
}

