package com.example.converter_android.features.density.domain.models

/**
 * Enum representing density units for density measurements.
 * 
 * Density is a physical quantity that measures mass per unit volume.
 * In the International System of Units (SI), density is measured in
 * kilograms per cubic meter (kg/m³).
 * 
 * This enum provides nine common density units used in science, engineering,
 * and industry:
 * - KILOGRAM_PER_CUBIC_METER: The base SI unit (1 kg/m³ = 1 kg/m³)
 * - GRAM_PER_CUBIC_CENTIMETER: Commonly used (1 g/cm³ = 1000 kg/m³)
 * - GRAM_PER_CUBIC_METER: Very small density (1 g/m³ = 0.001 kg/m³)
 * - KILOGRAM_PER_LITER: Equivalent to g/cm³ (1 kg/L = 1000 kg/m³)
 * - GRAM_PER_LITER: Common in chemistry (1 g/L = 1 kg/m³)
 * - MILLIGRAM_PER_LITER: Very small density (1 mg/L = 0.001 kg/m³)
 * - POUND_PER_CUBIC_FOOT: Imperial unit (1 lb/ft³ ≈ 16.0185 kg/m³)
 * - POUND_PER_GALLON: Imperial unit (1 lb/gal ≈ 119.8264 kg/m³)
 * - SPECIFIC_GRAVITY: Dimensionless ratio, converted as SG × 1000 kg/m³
 * 
 * Note: Specific Gravity (SG) is the ratio of a substance's density to water's density.
 * Water has SG = 1.0, which equals 1000 kg/m³. To convert SG to kg/m³: multiply by 1000.
 * 
 * All conversions are performed relative to kg/m³ as the base unit.
 * 
 * @property displayNameRes Resource ID for the localized display name of the unit.
 * @property conversionFactorToKgPerM3 Conversion factor to convert this unit to kg/m³.
 */
enum class DensityUnit(
	val displayNameRes: Int,
	val conversionFactorToKgPerM3: Double
) {
	KILOGRAM_PER_CUBIC_METER(com.example.converter_android.R.string.unit_kilogram_per_cubic_meter, 1.0),
	GRAM_PER_CUBIC_CENTIMETER(com.example.converter_android.R.string.unit_gram_per_cubic_centimeter, 1000.0),
	GRAM_PER_CUBIC_METER(com.example.converter_android.R.string.unit_gram_per_cubic_meter, 0.001),
	KILOGRAM_PER_LITER(com.example.converter_android.R.string.unit_kilogram_per_liter, 1000.0),
	GRAM_PER_LITER(com.example.converter_android.R.string.unit_gram_per_liter, 1.0),
	MILLIGRAM_PER_LITER(com.example.converter_android.R.string.unit_milligram_per_liter, 0.001),
	POUND_PER_CUBIC_FOOT(com.example.converter_android.R.string.unit_pound_per_cubic_foot, 16.0185),
	POUND_PER_GALLON(com.example.converter_android.R.string.unit_pound_per_gallon, 119.8264),
	SPECIFIC_GRAVITY(com.example.converter_android.R.string.unit_specific_gravity, 1000.0) // SG * 1000 = kg/m³
}

