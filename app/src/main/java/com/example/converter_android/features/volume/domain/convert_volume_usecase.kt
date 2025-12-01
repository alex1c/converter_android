package com.example.converter_android.features.volume.domain

import com.example.converter_android.core.converters.UnitConverter
import com.example.converter_android.features.volume.domain.models.VolumeUnit

/**
 * Use case for converting volume values between different volume units.
 * 
 * This class implements the universal [UnitConverter] interface for [VolumeUnit],
 * providing a standardized way to convert volume measurements across different units.
 * 
 * The conversion process follows a two-step approach:
 * 1. Convert the input value from the source unit to the base unit (liters)
 * 2. Convert the value from the base unit to the target unit
 * 
 * This approach ensures accuracy and consistency, as all conversions are performed
 * relative to a single base unit (liters), eliminating potential rounding errors
 * that could occur with direct unit-to-unit conversions.
 * 
 * Supported units:
 * - Liter (L) - base unit
 * - Milliliter (mL) - 0.001 L
 * - Cubic Meter (m³) - 1000 L
 * - Cubic Centimeter (cm³) - 0.001 L
 * - US Gallon (US gal) - ≈ 3.78541 L
 * - UK Gallon (UK gal) - ≈ 4.54609 L
 * - Pint (pt) - ≈ 0.473176 L
 * - Cup (cup) - ≈ 0.236588 L
 * - Tablespoon (tbsp) - ≈ 0.0147868 L
 * - Teaspoon (tsp) - ≈ 0.00492892 L
 * 
 * @see UnitConverter
 * @see VolumeUnit
 * 
 * @example
 * val useCase = ConvertVolumeUseCase()
 * // Convert 1000 milliliters to liters
 * val result = useCase.convert(1000.0, VolumeUnit.MILLILITER, VolumeUnit.LITER) // 1.0
 * // Convert 1 US gallon to liters
 * val result2 = useCase.convert(1.0, VolumeUnit.GALLON_US, VolumeUnit.LITER) // ≈ 3.78541
 */
class ConvertVolumeUseCase : UnitConverter<VolumeUnit> {
	/**
	 * Converts a volume value from one unit to another.
	 * 
	 * The conversion is performed through a two-step process:
	 * 1. Convert the value to liters (base unit) by multiplying by the source unit's conversion factor
	 * 2. Convert from liters to the target unit by dividing by the target unit's conversion factor
	 * 
	 * If the source and target units are the same, the value is returned unchanged
	 * without any calculation, optimizing performance for no-op conversions.
	 * 
	 * Conversion accuracy:
	 * - Metric units (liter, milliliter, cubic meter, cubic centimeter) use exact decimal relationships
	 * - US customary units (gallon, pint, cup, tablespoon, teaspoon) use precise conversion factors
	 * - UK gallon uses the exact imperial standard
	 * - All calculations use Double precision for maximum accuracy
	 *
	 * @param value The volume value to convert. Can be any positive or negative number.
	 * @param from The source volume unit from which to convert
	 * @param to The target volume unit to which to convert
	 * @return The converted volume value in the target unit
	 * 
	 * @example
	 * // Convert 2.5 liters to milliliters
	 * val milliliters = convert(2.5, VolumeUnit.LITER, VolumeUnit.MILLILITER) // 2500.0
	 * 
	 * // Convert 1 UK gallon to US gallons
	 * val usGallons = convert(1.0, VolumeUnit.GALLON_UK, VolumeUnit.GALLON_US) // ≈ 1.20095
	 */
	override fun convert(value: Double, from: VolumeUnit, to: VolumeUnit): Double {
		// Optimization: if source and target units are the same, return value unchanged
		if (from == to) {
			return value
		}

		// Step 1: Convert to base unit (liters)
		// Multiply by the conversion factor of the source unit
		val valueInLiters = value * from.conversionFactorToLiter

		// Step 2: Convert from base unit to target unit
		// Divide by the conversion factor of the target unit
		return valueInLiters / to.conversionFactorToLiter
	}
}

