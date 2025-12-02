package com.forestmusic.converter_android.features.electricity.domain

import com.forestmusic.converter_android.core.converters.UnitConverter
import com.forestmusic.converter_android.features.electricity.domain.models.ResistanceUnit

/**
 * Use case for converting electrical resistance values between different resistance units.
 * 
 * This class implements the universal [UnitConverter] interface for [ResistanceUnit],
 * providing a standardized way to convert resistance measurements across different units.
 * 
 * The conversion process follows a two-step approach:
 * 1. Convert the input value from the source unit to the base unit (ohms)
 * 2. Convert the value from the base unit to the target unit
 * 
 * This approach ensures accuracy and consistency, as all conversions are performed
 * relative to a single base unit (ohms), eliminating potential rounding errors
 * that could occur with direct unit-to-unit conversions.
 * 
 * Supported units:
 * - Ohm (Ω) - base unit
 * - Kiloohm (kΩ) - 1000 Ω
 * - Megaohm (MΩ) - 1,000,000 Ω
 * 
 * @see UnitConverter
 * @see ResistanceUnit
 * 
 * @example
 * val useCase = ConvertResistanceUseCase()
 * // Convert 5 kiloohms to ohms
 * val result = useCase.convert(5.0, ResistanceUnit.KILOOHM, ResistanceUnit.OHM) // 5000.0
 * // Convert 2 megaohms to kiloohms
 * val result2 = useCase.convert(2.0, ResistanceUnit.MEGAOHM, ResistanceUnit.KILOOHM) // 2000.0
 */
class ConvertResistanceUseCase : UnitConverter<ResistanceUnit> {
	/**
	 * Converts a resistance value from one unit to another.
	 * 
	 * The conversion is performed through a two-step process:
	 * 1. Convert the value to ohms (base unit) by multiplying by the source unit's conversion factor
	 * 2. Convert from ohms to the target unit by dividing by the target unit's conversion factor
	 * 
	 * If the source and target units are the same, the value is returned unchanged
	 * without any calculation, optimizing performance for no-op conversions.
	 *
	 * @param value The resistance value to convert. Can be any positive or negative number.
	 * @param from The source resistance unit from which to convert
	 * @param to The target resistance unit to which to convert
	 * @return The converted resistance value in the target unit
	 * 
	 * @example
	 * // Convert 5000 ohms to kiloohms
	 * val kiloohms = convert(5000.0, ResistanceUnit.OHM, ResistanceUnit.KILOOHM) // 5.0
	 * 
	 * // Convert 1 megaohm to kiloohms
	 * val kiloohms = convert(1.0, ResistanceUnit.MEGAOHM, ResistanceUnit.KILOOHM) // 1000.0
	 */
	override fun convert(value: Double, from: ResistanceUnit, to: ResistanceUnit): Double {
		// Optimization: if source and target units are the same, return value unchanged
		if (from == to) return value

		// Step 1: Convert to base unit (ohms)
		// Multiply by the conversion factor of the source unit
		val valueInOhms = value * from.conversionFactorToOhm

		// Step 2: Convert from base unit to target unit
		// Divide by the conversion factor of the target unit
		return valueInOhms / to.conversionFactorToOhm
	}
}

