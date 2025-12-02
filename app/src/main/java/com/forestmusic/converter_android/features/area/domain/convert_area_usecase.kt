package com.forestmusic.converter_android.features.area.domain

import com.forestmusic.converter_android.core.converters.UnitConverter
import com.forestmusic.converter_android.features.area.domain.models.AreaUnit

/**
 * Use case for converting area values between different area units.
 * 
 * This class implements the universal [UnitConverter] interface for [AreaUnit],
 * providing a standardized way to convert area measurements across different units.
 * 
 * The conversion process follows a two-step approach:
 * 1. Convert the input value from the source unit to the base unit (square meters)
 * 2. Convert the value from the base unit to the target unit
 * 
 * This approach ensures accuracy and consistency, as all conversions are performed
 * relative to a single base unit (m²), eliminating potential rounding errors
 * that could occur with direct unit-to-unit conversions.
 * 
 * Supported units:
 * - Square Meter (m²) - base unit
 * - Square Kilometer (km²) - 1,000,000 m²
 * - Square Centimeter (cm²) - 0.0001 m²
 * - Square Millimeter (mm²) - 0.000001 m²
 * - Hectare (ha) - 10,000 m²
 * - Are (a) - 100 m²
 * - Square Foot (ft²) - ≈ 0.092903 m²
 * - Square Yard (yd²) - ≈ 0.836127 m²
 * - Acre (ac) - ≈ 4046.8564224 m²
 * 
 * @see UnitConverter
 * @see AreaUnit
 */
class ConvertAreaUseCase : UnitConverter<AreaUnit> {
	/**
	 * Converts an area value from one unit to another.
	 * 
	 * The conversion is performed through a two-step process:
	 * 1. Convert the value to square meters (base unit) by multiplying by the source unit's conversion factor
	 * 2. Convert from square meters to the target unit by dividing by the target unit's conversion factor
	 * 
	 * If the source and target units are the same, the value is returned unchanged
	 * without any calculation, optimizing performance for no-op conversions.
	 *
	 * @param value The area value to convert. Can be any positive or negative number.
	 * @param from The source area unit from which to convert
	 * @param to The target area unit to which to convert
	 * @return The converted area value in the target unit
	 */
	override fun convert(value: Double, from: AreaUnit, to: AreaUnit): Double {
		if (from == to) {
			return value
		}

		// Step 1: Convert to base unit (square meters)
		val valueInSquareMeters = value * from.conversionFactorToSquareMeter

		// Step 2: Convert from base unit to target unit
		return valueInSquareMeters / to.conversionFactorToSquareMeter
	}
}

