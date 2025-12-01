package com.example.converter_android.features.density.domain

import com.example.converter_android.core.converters.UnitConverter
import com.example.converter_android.features.density.domain.models.DensityUnit

/**
 * Use case for converting density values between different density units.
 * 
 * This class implements the universal [UnitConverter] interface for [DensityUnit],
 * providing a standardized way to convert density measurements across different units.
 * 
 * The conversion process follows a two-step approach:
 * 1. Convert the input value from the source unit to the base unit (kg/m³)
 * 2. Convert the value from the base unit to the target unit
 * 
 * This approach ensures accuracy and consistency, as all conversions are performed
 * relative to a single base unit (kg/m³), eliminating potential rounding errors
 * that could occur with direct unit-to-unit conversions.
 * 
 * Supported units:
 * - Kilogram per cubic meter (kg/m³) - base unit
 * - Gram per cubic centimeter (g/cm³) - 1000 kg/m³
 * - Gram per cubic meter (g/m³) - 0.001 kg/m³
 * - Kilogram per liter (kg/L) - 1000 kg/m³
 * - Gram per liter (g/L) - 1 kg/m³
 * - Milligram per liter (mg/L) - 0.001 kg/m³
 * - Pound per cubic foot (lb/ft³) - ≈ 16.0185 kg/m³
 * - Pound per gallon (lb/gal) - ≈ 119.8264 kg/m³
 * - Specific Gravity (SG) - SG × 1000 = kg/m³
 * 
 * @see UnitConverter
 * @see DensityUnit
 */
class ConvertDensityUseCase : UnitConverter<DensityUnit> {
	/**
	 * Converts a density value from one unit to another.
	 * 
	 * The conversion is performed through a two-step process:
	 * 1. Convert the value to kg/m³ (base unit) by multiplying by the source unit's conversion factor
	 * 2. Convert from kg/m³ to the target unit by dividing by the target unit's conversion factor
	 * 
	 * If the source and target units are the same, the value is returned unchanged
	 * without any calculation, optimizing performance for no-op conversions.
	 *
	 * @param value The density value to convert. Can be any positive or negative number.
	 * @param from The source density unit from which to convert
	 * @param to The target density unit to which to convert
	 * @return The converted density value in the target unit
	 */
	override fun convert(value: Double, from: DensityUnit, to: DensityUnit): Double {
		if (from == to) {
			return value
		}

		// Step 1: Convert to base unit (kg/m³)
		val valueInKgPerM3 = value * from.conversionFactorToKgPerM3

		// Step 2: Convert from base unit to target unit
		return valueInKgPerM3 / to.conversionFactorToKgPerM3
	}
}

