package com.forestmusic.converter_android.features.volume.domain.models

/**
 * Enum representing volume units for capacity/volume measurements.
 * 
 * Volume is a derived physical quantity that measures the amount of three-dimensional
 * space occupied by a substance. In the metric system, volume is commonly measured
 * in liters (L), which is equivalent to one cubic decimeter.
 * 
 * This enum provides ten common volume units used in everyday life, cooking, science,
 * and industry:
 * - LITER: The base unit (1 L = 1 L)
 * - MILLILITER: One thousandth of a liter (1 mL = 0.001 L)
 * - CUBIC_METER: One thousand liters (1 m³ = 1000 L)
 * - CUBIC_CENTIMETER: One thousandth of a liter (1 cm³ = 0.001 L, equivalent to 1 mL)
 * - GALLON_US: US liquid gallon (1 US gal ≈ 3.78541 L)
 * - GALLON_UK: UK/Imperial gallon (1 UK gal ≈ 4.54609 L)
 * - PINT: US liquid pint (1 pt ≈ 0.473176 L)
 * - CUP: US customary cup (1 cup ≈ 0.236588 L)
 * - TABLESPOON: US tablespoon (1 tbsp ≈ 0.0147868 L)
 * - TEASPOON: US teaspoon (1 tsp ≈ 0.00492892 L)
 * 
 * All conversions are performed relative to liters as the base unit.
 * To convert from any unit to liters, multiply by the conversion factor.
 * To convert from liters to any unit, divide by the conversion factor.
 * 
 * Conversion factors are based on international standards:
 * - Metric units (liter, milliliter, cubic meter, cubic centimeter) use exact decimal relationships
 * - US customary units (gallon, pint, cup, tablespoon, teaspoon) use precise conversion factors
 * - UK/Imperial gallon uses the exact imperial standard
 * 
 * Note: 1 cubic centimeter (cm³) = 1 milliliter (mL) = 0.001 liter
 * 
 * @property displayNameRes Resource ID for the localized display name of the unit.
 *                        Used in UI to display the unit name in the user's language.
 * @property conversionFactorToLiter Conversion factor to convert this unit to liters.
 *                                  To convert a value to L: value * conversionFactorToLiter
 *                                  To convert from L: value / conversionFactorToLiter
 * 
 * @example
 * // Convert 1000 milliliters to liters
 * val liters = 1000.0 * VolumeUnit.MILLILITER.conversionFactorToLiter // 1.0 L
 * 
 * // Convert 1 US gallon to liters
 * val liters = 1.0 * VolumeUnit.GALLON_US.conversionFactorToLiter // ≈ 3.78541 L
 */
enum class VolumeUnit(
	/**
	 * Resource ID for the localized display name of this volume unit.
	 * Used in UI to display the unit name in the user's language.
	 */
	val displayNameRes: Int,
	
	/**
	 * Conversion factor to convert this unit to liters (the base unit).
	 * 
	 * To convert a value from this unit to liters:
	 * valueInLiters = valueInThisUnit * conversionFactorToLiter
	 * 
	 * To convert a value from liters to this unit:
	 * valueInThisUnit = valueInLiters / conversionFactorToLiter
	 */
	val conversionFactorToLiter: Double
) {
	/**
	 * Liter (L) - The base unit for volume.
	 * 
	 * One liter is defined as one cubic decimeter (1 L = 1 dm³).
	 * It is the most commonly used unit for volume in the metric system.
	 * 
	 * Conversion factor: 1.0 (base unit, no conversion needed)
	 */
	LITER(com.forestmusic.converter_android.R.string.unit_liter, 1.0),
	
	/**
	 * Milliliter (mL) - One thousandth of a liter.
	 * 
	 * Commonly used for small volume measurements in cooking, medicine, and chemistry.
	 * 1000 milliliters = 1 liter. 1 milliliter = 1 cubic centimeter.
	 * 
	 * Conversion factor: 0.001 (1 mL = 0.001 L)
	 */
	MILLILITER(com.forestmusic.converter_android.R.string.unit_milliliter, 0.001),
	
	/**
	 * Cubic Meter (m³) - One thousand liters.
	 * 
	 * Used for large volume measurements in construction, shipping, and industry.
	 * 1 cubic meter = 1000 liters = 1,000,000 cubic centimeters.
	 * 
	 * Conversion factor: 1000.0 (1 m³ = 1000 L)
	 */
	CUBIC_METER(com.forestmusic.converter_android.R.string.unit_cubic_meter, 1000.0),
	
	/**
	 * Cubic Centimeter (cm³) - One thousandth of a liter.
	 * 
	 * Also known as milliliter. Used in science and engineering for precise measurements.
	 * 1 cubic centimeter = 1 milliliter = 0.001 liter.
	 * 
	 * Conversion factor: 0.001 (1 cm³ = 0.001 L)
	 */
	CUBIC_CENTIMETER(com.forestmusic.converter_android.R.string.unit_cubic_centimeter, 0.001),
	
	/**
	 * US Gallon (US gal) - US liquid gallon.
	 * 
	 * Commonly used in the United States for fuel, beverages, and large containers.
	 * 1 US gallon ≈ 3.78541 liters. 4 quarts = 1 US gallon.
	 * 
	 * Conversion factor: 3.78541 (1 US gal ≈ 3.78541 L)
	 */
	GALLON_US(com.forestmusic.converter_android.R.string.unit_gallon_us, 3.78541),
	
	/**
	 * UK/Imperial Gallon (UK gal) - Imperial gallon.
	 * 
	 * Used in the United Kingdom and some Commonwealth countries.
	 * 1 UK gallon ≈ 4.54609 liters. Larger than the US gallon.
	 * 
	 * Conversion factor: 4.54609 (1 UK gal ≈ 4.54609 L)
	 */
	GALLON_UK(com.forestmusic.converter_android.R.string.unit_gallon_uk, 4.54609),
	
	/**
	 * Pint (pt) - US liquid pint.
	 * 
	 * Commonly used in the United States for beverages.
	 * 1 pint ≈ 0.473176 liters. 2 cups = 1 pint, 8 pints = 1 US gallon.
	 * 
	 * Conversion factor: 0.473176 (1 pt ≈ 0.473176 L)
	 */
	PINT(com.forestmusic.converter_android.R.string.unit_pint, 0.473176),
	
	/**
	 * Cup (cup) - US customary cup.
	 * 
	 * Commonly used in cooking and recipes in the United States.
	 * 1 cup ≈ 0.236588 liters. 2 cups = 1 pint, 16 cups = 1 US gallon.
	 * 
	 * Conversion factor: 0.236588 (1 cup ≈ 0.236588 L)
	 */
	CUP(com.forestmusic.converter_android.R.string.unit_cup, 0.236588),
	
	/**
	 * Tablespoon (tbsp) - US tablespoon.
	 * 
	 * Used in cooking and recipes. 1 tablespoon ≈ 0.0147868 liters.
	 * 3 teaspoons = 1 tablespoon, 16 tablespoons = 1 cup.
	 * 
	 * Conversion factor: 0.0147868 (1 tbsp ≈ 0.0147868 L)
	 */
	TABLESPOON(com.forestmusic.converter_android.R.string.unit_tablespoon, 0.0147868),
	
	/**
	 * Teaspoon (tsp) - US teaspoon.
	 * 
	 * Used in cooking and recipes for small measurements.
	 * 1 teaspoon ≈ 0.00492892 liters. 3 teaspoons = 1 tablespoon.
	 * 
	 * Conversion factor: 0.00492892 (1 tsp ≈ 0.00492892 L)
	 */
	TEASPOON(com.forestmusic.converter_android.R.string.unit_teaspoon, 0.00492892)
}

