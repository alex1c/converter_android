package com.example.converter_android.features.torque.domain

import com.example.converter_android.core.converters.UnitConverter
import com.example.converter_android.features.torque.domain.models.TorqueUnit

/**
 * Use case for converting torque values between different torque units.
 * 
 * This class implements the universal [UnitConverter] interface for [TorqueUnit],
 * providing a standardized way to convert torque measurements across different units.
 * 
 * The conversion process follows a two-step approach:
 * 1. Convert the input value from the source unit to the base unit (Newton-meters)
 * 2. Convert the value from the base unit to the target unit
 * 
 * This approach ensures accuracy and consistency, as all conversions are performed
 * relative to a single base unit (N·m), eliminating potential rounding errors
 * that could occur with direct unit-to-unit conversions.
 * 
 * Supported units:
 * - Newton-meter (N·m) - base unit
 * - Millinewton-meter (mN·m) - 0.001 N·m
 * - Kilonewton-meter (kN·m) - 1000 N·m
 * - Decanewton-meter (daN·m) - 10 N·m
 * - Pound-foot (lbf·ft) - ≈ 1.355818 N·m
 * - Pound-inch (lbf·in) - ≈ 0.1129848 N·m
 * - Ounce-inch (ozf·in) - ≈ 0.00706155 N·m
 * - Kilogram-force meter (kgf·m) - 9.80665 N·m
 * - Kilogram-force centimeter (kgf·cm) - 0.0980665 N·m
 * 
 * @see UnitConverter
 * @see TorqueUnit
 */
class ConvertTorqueUseCase : UnitConverter<TorqueUnit> {
	/**
	 * Converts a torque value from one unit to another.
	 * 
	 * The conversion is performed through a two-step process:
	 * 1. Convert the value to Newton-meters (base unit) by multiplying by the source unit's conversion factor
	 * 2. Convert from Newton-meters to the target unit by dividing by the target unit's conversion factor
	 * 
	 * If the source and target units are the same, the value is returned unchanged
	 * without any calculation, optimizing performance for no-op conversions.
	 *
	 * @param value The torque value to convert. Can be any positive or negative number.
	 * @param from The source torque unit from which to convert
	 * @param to The target torque unit to which to convert
	 * @return The converted torque value in the target unit
	 */
	override fun convert(value: Double, from: TorqueUnit, to: TorqueUnit): Double {
		if (from == to) {
			return value
		}

		// Step 1: Convert to base unit (Newton-meters)
		val valueInNewtonMeter = value * from.conversionFactorToNewtonMeter

		// Step 2: Convert from base unit to target unit
		return valueInNewtonMeter / to.conversionFactorToNewtonMeter
	}
}

