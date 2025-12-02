package com.forestmusic.converter_android.features.energy.domain

import com.forestmusic.converter_android.core.converters.UnitConverter
import com.forestmusic.converter_android.features.energy.domain.models.EnergyUnit

/**
 * Use case for converting energy values between different energy units.
 * 
 * This class implements the universal [UnitConverter] interface for [EnergyUnit],
 * providing a standardized way to convert energy measurements across different units.
 * 
 * The conversion process follows a two-step approach:
 * 1. Convert the input value from the source unit to the base unit (Joules)
 * 2. Convert the value from the base unit to the target unit
 * 
 * This approach ensures accuracy and consistency, as all conversions are performed
 * relative to a single base unit (J), eliminating potential rounding errors
 * that could occur with direct unit-to-unit conversions.
 * 
 * Supported units:
 * - Joule (J) - base unit
 * - Kilojoule (kJ) - 1000 J
 * - Megajoule (MJ) - 1,000,000 J
 * - Watt-hour (Wh) - 3600 J
 * - Kilowatt-hour (kWh) - 3,600,000 J
 * - Calorie (cal) - 4.184 J
 * - Kilocalorie (kcal) - 4184 J
 * - BTU - ≈ 1055.06 J
 * - Erg - 0.0000001 J
 * - Electron-volt (eV) - ≈ 1.602176634×10⁻¹⁹ J
 * 
 * @see UnitConverter
 * @see EnergyUnit
 */
class ConvertEnergyUseCase : UnitConverter<EnergyUnit> {
	/**
	 * Converts an energy value from one unit to another.
	 * 
	 * The conversion is performed through a two-step process:
	 * 1. Convert the value to Joules (base unit) by multiplying by the source unit's conversion factor
	 * 2. Convert from Joules to the target unit by dividing by the target unit's conversion factor
	 * 
	 * If the source and target units are the same, the value is returned unchanged
	 * without any calculation, optimizing performance for no-op conversions.
	 *
	 * @param value The energy value to convert. Can be any positive or negative number.
	 * @param from The source energy unit from which to convert
	 * @param to The target energy unit to which to convert
	 * @return The converted energy value in the target unit
	 */
	override fun convert(value: Double, from: EnergyUnit, to: EnergyUnit): Double {
		if (from == to) {
			return value
		}

		// Step 1: Convert to base unit (Joules)
		val valueInJoule = value * from.conversionFactorToJoule

		// Step 2: Convert from base unit to target unit
		return valueInJoule / to.conversionFactorToJoule
	}
}

