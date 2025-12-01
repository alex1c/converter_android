package com.example.converter_android.features.length.domain

import com.example.converter_android.core.converters.UnitConverter
import com.example.converter_android.features.length.domain.models.LengthUnit

/**
 * Use case for converting length values between different length units.
 * 
 * This class implements the universal [UnitConverter] interface for [LengthUnit],
 * providing a standardized way to convert length measurements across different units.
 * 
 * The conversion process follows a two-step approach:
 * 1. Convert the input value from the source unit to the base unit (meters)
 * 2. Convert the value from the base unit to the target unit
 * 
 * This approach ensures accuracy and consistency, as all conversions are performed
 * relative to a single base unit (meters), eliminating potential rounding errors
 * that could occur with direct unit-to-unit conversions.
 * 
 * Supported units:
 * - Meter (m) - base unit
 * - Kilometer (km) - 1000 m
 * - Centimeter (cm) - 0.01 m
 * - Millimeter (mm) - 0.001 m
 * - Mile (mi) - 1609.344 m
 * - Yard (yd) - 0.9144 m
 * - Foot (ft) - 0.3048 m
 * - Inch (in) - 0.0254 m
 * - Nautical Mile (nmi) - 1852 m
 * 
 * @see UnitConverter
 * @see LengthUnit
 * 
 * @example
 * val useCase = ConvertLengthUseCase()
 * // Convert 1000 meters to kilometers
 * val result = useCase.convert(1000.0, LengthUnit.METER, LengthUnit.KILOMETER) // 1.0
 * // Convert 1 mile to feet
 * val result2 = useCase.convert(1.0, LengthUnit.MILE, LengthUnit.FOOT) // ≈ 5280.0
 */
class ConvertLengthUseCase : UnitConverter<LengthUnit> {
	/**
	 * Converts a length value from one unit to another.
	 * 
	 * The conversion is performed through a two-step process:
	 * 1. Convert the value to meters (base unit) by multiplying by the source unit's conversion factor
	 * 2. Convert from meters to the target unit by dividing by the target unit's conversion factor
	 * 
	 * If the source and target units are the same, the value is returned unchanged
	 * without any calculation, optimizing performance for no-op conversions.
	 * 
	 * Conversion accuracy:
	 * - Metric units (kilometer, centimeter, millimeter) use exact decimal relationships
	 * - Imperial units (mile, yard, foot, inch) use precise conversion factors
	 * - Nautical mile uses the exact international standard (1852 m)
	 * - All calculations use Double precision for maximum accuracy
	 *
	 * @param value The length value to convert. Can be any positive or negative number.
	 * @param from The source length unit from which to convert
	 * @param to The target length unit to which to convert
	 * @return The converted length value in the target unit
	 * 
	 * @example
	 * // Convert 5 kilometers to miles
	 * val miles = convert(5.0, LengthUnit.KILOMETER, LengthUnit.MILE) // ≈ 3.10686
	 * 
	 * // Convert 12 inches to feet
	 * val feet = convert(12.0, LengthUnit.INCH, LengthUnit.FOOT) // 1.0
	 */
	override fun convert(value: Double, from: LengthUnit, to: LengthUnit): Double {
		// Optimization: if source and target units are the same, return value unchanged
		if (from == to) {
			return value
		}

		// Step 1: Convert to base unit (meters)
		// Multiply by the conversion factor of the source unit
		val valueInMeters = value * from.conversionFactorToMeter

		// Step 2: Convert from base unit to target unit
		// Divide by the conversion factor of the target unit
		return valueInMeters / to.conversionFactorToMeter
	}
}

