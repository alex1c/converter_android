package com.forestmusic.converter_android.features.electricity.domain

import com.forestmusic.converter_android.core.converters.UnitConverter
import com.forestmusic.converter_android.features.electricity.domain.models.VoltageUnit

/**
 * Use case for converting voltage values between different voltage units.
 * 
 * This class implements the universal [UnitConverter] interface for [VoltageUnit],
 * providing a standardized way to convert voltage measurements across different units.
 * 
 * The conversion process follows a two-step approach:
 * 1. Convert the input value from the source unit to the base unit (volts)
 * 2. Convert the value from the base unit to the target unit
 * 
 * This approach ensures accuracy and consistency, as all conversions are performed
 * relative to a single base unit (volts), eliminating potential rounding errors
 * that could occur with direct unit-to-unit conversions.
 * 
 * Supported units:
 * - Volt (V) - base unit
 * - Millivolt (mV) - 0.001 V
 * - Kilovolt (kV) - 1000 V
 * 
 * @see UnitConverter
 * @see VoltageUnit
 * 
 * @example
 * val useCase = ConvertVoltageUseCase()
 * // Convert 5 kilovolts to volts
 * val result = useCase.convert(5.0, VoltageUnit.KILOVOLT, VoltageUnit.VOLT) // 5000.0
 * // Convert 5000 millivolts to kilovolts
 * val result2 = useCase.convert(5000.0, VoltageUnit.MILLIVOLT, VoltageUnit.KILOVOLT) // 0.005
 */
class ConvertVoltageUseCase : UnitConverter<VoltageUnit> {
	/**
	 * Converts a voltage value from one unit to another.
	 * 
	 * The conversion is performed through a two-step process:
	 * 1. Convert the value to volts (base unit) by multiplying by the source unit's conversion factor
	 * 2. Convert from volts to the target unit by dividing by the target unit's conversion factor
	 * 
	 * If the source and target units are the same, the value is returned unchanged
	 * without any calculation, optimizing performance for no-op conversions.
	 *
	 * @param value The voltage value to convert. Can be any positive or negative number.
	 * @param from The source voltage unit from which to convert
	 * @param to The target voltage unit to which to convert
	 * @return The converted voltage value in the target unit
	 * 
	 * @example
	 * // Convert 12.5 volts to millivolts
	 * val millivolts = convert(12.5, VoltageUnit.VOLT, VoltageUnit.MILLIVOLT) // 12500.0
	 * 
	 * // Convert 1000 millivolts to kilovolts
	 * val kilovolts = convert(1000.0, VoltageUnit.MILLIVOLT, VoltageUnit.KILOVOLT) // 0.001
	 */
	override fun convert(value: Double, from: VoltageUnit, to: VoltageUnit): Double {
		// Optimization: if source and target units are the same, return value unchanged
		if (from == to) return value

		// Step 1: Convert to base unit (volts)
		// Multiply by the conversion factor of the source unit
		val valueInVolts = value * from.conversionFactorToVolt

		// Step 2: Convert from base unit to target unit
		// Divide by the conversion factor of the target unit
		return valueInVolts / to.conversionFactorToVolt
	}
}

