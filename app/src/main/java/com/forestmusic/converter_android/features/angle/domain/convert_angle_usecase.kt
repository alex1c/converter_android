package com.forestmusic.converter_android.features.angle.domain

import com.forestmusic.converter_android.core.converters.UnitConverter
import com.forestmusic.converter_android.features.angle.domain.models.AngleUnit

/**
 * Use case for converting angle values between different angle units.
 * 
 * This class implements the universal [UnitConverter] interface for [AngleUnit],
 * providing a standardized way to convert angle measurements across different units.
 * 
 * The conversion process follows a two-step approach:
 * 1. Convert the input value from the source unit to the base unit (degrees)
 * 2. Convert the value from the base unit to the target unit
 * 
 * This approach ensures accuracy and consistency, as all conversions are performed
 * relative to a single base unit (degrees), eliminating potential rounding errors
 * that could occur with direct unit-to-unit conversions.
 * 
 * Supported units:
 * - Degree (°) - base unit
 * - Radian (rad) - 180°/π ≈ 57.2958°
 * - Grad (grad) - 0.9°
 * - Arc Minute (') - 1/60°
 * - Arc Second (") - 1/3600°
 * - Turn (turn) - 360°
 * 
 * @see UnitConverter
 * @see AngleUnit
 */
class ConvertAngleUseCase : UnitConverter<AngleUnit> {
	/**
	 * Converts an angle value from one unit to another.
	 * 
	 * The conversion is performed through a two-step process:
	 * 1. Convert the value to degrees (base unit) by multiplying by the source unit's conversion factor
	 * 2. Convert from degrees to the target unit by dividing by the target unit's conversion factor
	 * 
	 * If the source and target units are the same, the value is returned unchanged
	 * without any calculation, optimizing performance for no-op conversions.
	 *
	 * @param value The angle value to convert. Can be any positive or negative number.
	 * @param from The source angle unit from which to convert
	 * @param to The target angle unit to which to convert
	 * @return The converted angle value in the target unit
	 */
	override fun convert(value: Double, from: AngleUnit, to: AngleUnit): Double {
		if (from == to) {
			return value
		}

		// Step 1: Convert to base unit (degrees)
		val valueInDegrees = value * from.conversionFactorToDegree

		// Step 2: Convert from base unit to target unit
		return valueInDegrees / to.conversionFactorToDegree
	}
}

