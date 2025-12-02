package com.forestmusic.converter_android.features.pressure.domain

import com.forestmusic.converter_android.core.converters.UnitConverter
import com.forestmusic.converter_android.features.pressure.domain.models.PressureUnit

/**
 * Use case for converting pressure values between different pressure units.
 * 
 * This class implements the universal [UnitConverter] interface for [PressureUnit],
 * providing a standardized way to convert pressure measurements across different units.
 * 
 * The conversion process follows a two-step approach:
 * 1. Convert the input value from the source unit to the base unit (Pascals)
 * 2. Convert the value from the base unit to the target unit
 * 
 * This approach ensures accuracy and consistency, as all conversions are performed
 * relative to a single base unit (Pa), eliminating potential rounding errors
 * that could occur with direct unit-to-unit conversions.
 * 
 * Supported units:
 * - Pascal (Pa) - base unit
 * - Kilopascal (kPa) - 1000 Pa
 * - Megapascal (MPa) - 1,000,000 Pa
 * - Bar (bar) - 100,000 Pa
 * - Millibar (mbar) - 100 Pa
 * - Technical Atmosphere (at) - ≈ 98,066.5 Pa
 * - Standard Atmosphere (atm) - 101,325 Pa
 * - Millimeter of Mercury (mmHg) - ≈ 133.322 Pa
 * - Inch of Mercury (inHg) - ≈ 3386.39 Pa
 * - PSI (lbf/in²) - ≈ 6894.76 Pa
 * 
 * @see UnitConverter
 * @see PressureUnit
 */
class ConvertPressureUseCase : UnitConverter<PressureUnit> {
	/**
	 * Converts a pressure value from one unit to another.
	 * 
	 * The conversion is performed through a two-step process:
	 * 1. Convert the value to Pascals (base unit) by multiplying by the source unit's conversion factor
	 * 2. Convert from Pascals to the target unit by dividing by the target unit's conversion factor
	 * 
	 * If the source and target units are the same, the value is returned unchanged
	 * without any calculation, optimizing performance for no-op conversions.
	 *
	 * @param value The pressure value to convert. Can be any positive or negative number.
	 * @param from The source pressure unit from which to convert
	 * @param to The target pressure unit to which to convert
	 * @return The converted pressure value in the target unit
	 */
	override fun convert(value: Double, from: PressureUnit, to: PressureUnit): Double {
		if (from == to) {
			return value
		}

		// Step 1: Convert to base unit (Pascals)
		val valueInPascal = value * from.conversionFactorToPascal

		// Step 2: Convert from base unit to target unit
		return valueInPascal / to.conversionFactorToPascal
	}
}

