package com.example.converter_android.features.weight.domain

import com.example.converter_android.core.converters.UnitConverter
import com.example.converter_android.features.weight.domain.models.WeightUnit

/**
 * Use case for converting weight values between different weight units.
 * 
 * This class implements the universal [UnitConverter] interface for [WeightUnit],
 * providing a standardized way to convert weight measurements across different units.
 * 
 * The conversion process follows a two-step approach:
 * 1. Convert the input value from the source unit to the base unit (kilograms)
 * 2. Convert the value from the base unit to the target unit
 * 
 * This approach ensures accuracy and consistency, as all conversions are performed
 * relative to a single base unit (kilograms), eliminating potential rounding errors
 * that could occur with direct unit-to-unit conversions.
 * 
 * Supported units:
 * - Kilogram (kg) - base unit
 * - Gram (g) - 0.001 kg
 * - Ton (t) - 1000 kg
 * - Pound (lb) - ≈ 0.453592 kg
 * - Ounce (oz) - ≈ 0.0283495 kg
 * - Milligram (mg) - 0.000001 kg
 * 
 * @see UnitConverter
 * @see WeightUnit
 * 
 * @example
 * val useCase = ConvertWeightUseCase()
 * // Convert 1000 grams to kilograms
 * val result = useCase.convert(1000.0, WeightUnit.GRAM, WeightUnit.KILOGRAM) // 1.0
 * // Convert 2 pounds to grams
 * val result2 = useCase.convert(2.0, WeightUnit.POUND, WeightUnit.GRAM) // ≈ 907.184
 */
class ConvertWeightUseCase : UnitConverter<WeightUnit> {
	/**
	 * Converts a weight value from one unit to another.
	 * 
	 * The conversion is performed through a two-step process:
	 * 1. Convert the value to kilograms (base unit) by multiplying by the source unit's conversion factor
	 * 2. Convert from kilograms to the target unit by dividing by the target unit's conversion factor
	 * 
	 * If the source and target units are the same, the value is returned unchanged
	 * without any calculation, optimizing performance for no-op conversions.
	 * 
	 * Conversion accuracy:
	 * - Metric units (gram, ton, milligram) use exact decimal relationships
	 * - Imperial units (pound, ounce) use precise conversion factors with high accuracy
	 * - All calculations use Double precision for maximum accuracy
	 *
	 * @param value The weight value to convert. Can be any positive or negative number.
	 * @param from The source weight unit from which to convert
	 * @param to The target weight unit to which to convert
	 * @return The converted weight value in the target unit
	 * 
	 * @example
	 * // Convert 5 kilograms to pounds
	 * val pounds = convert(5.0, WeightUnit.KILOGRAM, WeightUnit.POUND) // ≈ 11.0231
	 * 
	 * // Convert 16 ounces to pounds
	 * val pounds = convert(16.0, WeightUnit.OUNCE, WeightUnit.POUND) // 1.0
	 */
	override fun convert(value: Double, from: WeightUnit, to: WeightUnit): Double {
		// Optimization: if source and target units are the same, return value unchanged
		if (from == to) {
			return value
		}

		// Step 1: Convert to base unit (kilograms)
		// Multiply by the conversion factor of the source unit
		val valueInKg = value * from.conversionFactorToKg

		// Step 2: Convert from base unit to target unit
		// Divide by the conversion factor of the target unit
		return valueInKg / to.conversionFactorToKg
	}
}

