package com.forestmusic.converter_android.features.electricity.domain

import com.forestmusic.converter_android.core.converters.UnitConverter
import com.forestmusic.converter_android.features.electricity.domain.models.CurrentUnit

/**
 * Use case for converting electric current values between different current units.
 * 
 * This class implements the universal [UnitConverter] interface for [CurrentUnit],
 * providing a standardized way to convert current measurements across different units.
 * 
 * The conversion process follows a two-step approach:
 * 1. Convert the input value from the source unit to the base unit (amperes)
 * 2. Convert the value from the base unit to the target unit
 * 
 * This approach ensures accuracy and consistency, as all conversions are performed
 * relative to a single base unit (amperes), eliminating potential rounding errors
 * that could occur with direct unit-to-unit conversions.
 * 
 * Supported units:
 * - Ampere (A) - base unit
 * - Milliampere (mA) - 0.001 A
 * - Kiloampere (kA) - 1000 A
 * 
 * @see UnitConverter
 * @see CurrentUnit
 * 
 * @example
 * val useCase = ConvertCurrentUseCase()
 * // Convert 2 kiloamperes to amperes
 * val result = useCase.convert(2.0, CurrentUnit.KILOAMPERE, CurrentUnit.AMPERE) // 2000.0
 * // Convert 500 milliamperes to kiloamperes
 * val result2 = useCase.convert(500.0, CurrentUnit.MILLIAMPERE, CurrentUnit.KILOAMPERE) // 0.0005
 */
class ConvertCurrentUseCase : UnitConverter<CurrentUnit> {
	/**
	 * Converts a current value from one unit to another.
	 * 
	 * The conversion is performed through a two-step process:
	 * 1. Convert the value to amperes (base unit) by multiplying by the source unit's conversion factor
	 * 2. Convert from amperes to the target unit by dividing by the target unit's conversion factor
	 * 
	 * If the source and target units are the same, the value is returned unchanged
	 * without any calculation, optimizing performance for no-op conversions.
	 *
	 * @param value The current value to convert. Can be any positive or negative number.
	 * @param from The source current unit from which to convert
	 * @param to The target current unit to which to convert
	 * @return The converted current value in the target unit
	 * 
	 * @example
	 * // Convert 2.5 amperes to milliamperes
	 * val milliamperes = convert(2.5, CurrentUnit.AMPERE, CurrentUnit.MILLIAMPERE) // 2500.0
	 * 
	 * // Convert 1000 milliamperes to kiloamperes
	 * val kiloamperes = convert(1000.0, CurrentUnit.MILLIAMPERE, CurrentUnit.KILOAMPERE) // 0.001
	 */
	override fun convert(value: Double, from: CurrentUnit, to: CurrentUnit): Double {
		// Optimization: if source and target units are the same, return value unchanged
		if (from == to) return value

		// Step 1: Convert to base unit (amperes)
		// Multiply by the conversion factor of the source unit
		val valueInAmperes = value * from.conversionFactorToAmpere

		// Step 2: Convert from base unit to target unit
		// Divide by the conversion factor of the target unit
		return valueInAmperes / to.conversionFactorToAmpere
	}
}

