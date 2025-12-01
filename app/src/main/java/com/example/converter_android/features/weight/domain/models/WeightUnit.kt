package com.example.converter_android.features.weight.domain.models

/**
 * Enum representing weight units for mass measurements.
 * 
 * Weight (or mass) is a fundamental physical quantity that measures the amount
 * of matter in an object. In the International System of Units (SI), mass is
 * measured in kilograms (kg).
 * 
 * This enum provides six common weight units used in everyday life, science,
 * and industry:
 * - KILOGRAM: The base SI unit (1 kg = 1 kg)
 * - GRAM: One thousandth of a kilogram (1 g = 0.001 kg)
 * - TON: One thousand kilograms (1 t = 1000 kg)
 * - POUND: Imperial unit (1 lb ≈ 0.453592 kg)
 * - OUNCE: Imperial unit (1 oz ≈ 0.0283495 kg)
 * - MILLIGRAM: One millionth of a kilogram (1 mg = 0.000001 kg)
 * 
 * All conversions are performed relative to kilograms as the base unit.
 * To convert from any unit to kilograms, multiply by the conversion factor.
 * To convert from kilograms to any unit, divide by the conversion factor.
 * 
 * Conversion factors are based on international standards:
 * - Metric units (gram, ton, milligram) use exact decimal relationships
 * - Imperial units (pound, ounce) use precise conversion factors
 * 
 * @property displayNameRes Resource ID for the localized display name of the unit.
 *                        Used in UI to display the unit name in the user's language.
 * @property conversionFactorToKg Conversion factor to convert this unit to kilograms.
 *                               To convert a value to kg: value * conversionFactorToKg
 *                               To convert from kg: value / conversionFactorToKg
 * 
 * @example
 * // Convert 1000 grams to kilograms
 * val kg = 1000.0 * WeightUnit.GRAM.conversionFactorToKg // 1.0 kg
 * 
 * // Convert 2 pounds to kilograms
 * val kg = 2.0 * WeightUnit.POUND.conversionFactorToKg // ≈ 0.907184 kg
 */
enum class WeightUnit(
	/**
	 * Resource ID for the localized display name of this weight unit.
	 * Used in UI to display the unit name in the user's language.
	 */
	val displayNameRes: Int,
	
	/**
	 * Conversion factor to convert this unit to kilograms (the base unit).
	 * 
	 * To convert a value from this unit to kilograms:
	 * valueInKg = valueInThisUnit * conversionFactorToKg
	 * 
	 * To convert a value from kilograms to this unit:
	 * valueInThisUnit = valueInKg / conversionFactorToKg
	 */
	val conversionFactorToKg: Double
) {
	/**
	 * Kilogram (kg) - The base SI unit for mass.
	 * 
	 * One kilogram is defined as the mass of the international prototype
	 * of the kilogram, a platinum-iridium cylinder kept at the International
	 * Bureau of Weights and Measures.
	 * 
	 * Conversion factor: 1.0 (base unit, no conversion needed)
	 */
	KILOGRAM(com.example.converter_android.R.string.unit_kilogram, 1.0),
	
	/**
	 * Gram (g) - One thousandth of a kilogram.
	 * 
	 * Commonly used for small mass measurements in cooking, science, and
	 * everyday life. 1000 grams = 1 kilogram.
	 * 
	 * Conversion factor: 0.001 (1 g = 0.001 kg)
	 */
	GRAM(com.example.converter_android.R.string.unit_gram, 0.001),
	
	/**
	 * Ton (t) - One thousand kilograms (metric ton).
	 * 
	 * Also known as metric ton. Commonly used for large mass measurements
	 * in industry, shipping, and heavy machinery. 1 ton = 1000 kg.
	 * 
	 * Conversion factor: 1000.0 (1 t = 1000 kg)
	 */
	TON(com.example.converter_android.R.string.unit_ton, 1000.0),
	
	/**
	 * Pound (lb) - Imperial unit of mass.
	 * 
	 * Commonly used in the United States and United Kingdom. The conversion
	 * factor is based on the international avoirdupois pound.
	 * 1 pound ≈ 0.453592 kilograms.
	 * 
	 * Conversion factor: 0.453592 (1 lb ≈ 0.453592 kg)
	 */
	POUND(com.example.converter_android.R.string.unit_pound, 0.453592),
	
	/**
	 * Ounce (oz) - Imperial unit of mass.
	 * 
	 * Commonly used for small measurements, especially in cooking and
	 * postal services. 16 ounces = 1 pound.
	 * 1 ounce ≈ 0.0283495 kilograms.
	 * 
	 * Conversion factor: 0.0283495 (1 oz ≈ 0.0283495 kg)
	 */
	OUNCE(com.example.converter_android.R.string.unit_ounce, 0.0283495),
	
	/**
	 * Milligram (mg) - One millionth of a kilogram.
	 * 
	 * Used for very small mass measurements in medicine, chemistry, and
	 * scientific research. 1,000,000 milligrams = 1 kilogram.
	 * 
	 * Conversion factor: 0.000001 (1 mg = 0.000001 kg)
	 */
	MILLIGRAM(com.example.converter_android.R.string.unit_milligram, 0.000001)
}

