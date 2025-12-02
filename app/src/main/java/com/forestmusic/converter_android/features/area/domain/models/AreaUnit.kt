package com.forestmusic.converter_android.features.area.domain.models

/**
 * Enum representing area units for surface area measurements.
 * 
 * Area is a derived physical quantity that measures the extent of a two-dimensional
 * surface. In the International System of Units (SI), area is measured in square meters (m²).
 * 
 * This enum provides nine common area units used in everyday life, construction,
 * agriculture, and real estate:
 * - SQUARE_METER: The base SI unit (1 m² = 1 m²)
 * - SQUARE_KILOMETER: One million square meters (1 km² = 1,000,000 m²)
 * - SQUARE_CENTIMETER: One ten-thousandth of a square meter (1 cm² = 0.0001 m²)
 * - SQUARE_MILLIMETER: One millionth of a square meter (1 mm² = 0.000001 m²)
 * - HECTARE: Metric unit for large areas (1 ha = 10,000 m²)
 * - ARE: Metric unit for land area (1 a = 100 m², also known as "сотка" in Russian)
 * - SQUARE_FOOT: Imperial unit (1 ft² ≈ 0.092903 m²)
 * - SQUARE_YARD: Imperial unit (1 yd² ≈ 0.836127 m²)
 * - ACRE: Imperial unit for large land areas (1 ac ≈ 4046.8564224 m²)
 * 
 * All conversions are performed relative to square meters as the base unit.
 * To convert from any unit to m², multiply by the conversion factor.
 * To convert from m² to any unit, divide by the conversion factor.
 * 
 * Conversion factors are based on international standards:
 * - Metric units use exact decimal relationships
 * - Imperial units use precise conversion factors
 * - Hectare and are are exact metric units
 * 
 * Common area references:
 * - Small room: ~10-20 m²
 * - Average apartment: ~50-100 m²
 * - Football field: ~7,140 m² (~0.714 ha)
 * - 1 acre: ~4,047 m²
 * 
 * @property displayNameRes Resource ID for the localized display name of the unit.
 *                        Used in UI to display the unit name in the user's language.
 * @property conversionFactorToSquareMeter Conversion factor to convert this unit to square meters.
 *                                        To convert a value to m²: value * conversionFactorToSquareMeter
 *                                        To convert from m²: value / conversionFactorToSquareMeter
 * 
 * @example
 * // Convert 1 hectare to square meters
 * val m2 = 1.0 * AreaUnit.HECTARE.conversionFactorToSquareMeter // 10,000.0 m²
 * 
 * // Convert 1 acre to hectares
 * val ha = 1.0 * AreaUnit.ACRE.conversionFactorToSquareMeter / AreaUnit.HECTARE.conversionFactorToSquareMeter // ≈ 0.4047 ha
 */
enum class AreaUnit(
	/**
	 * Resource ID for the localized display name of this area unit.
	 * Used in UI to display the unit name in the user's language.
	 */
	val displayNameRes: Int,
	
	/**
	 * Conversion factor to convert this unit to square meters (the base unit).
	 * 
	 * To convert a value from this unit to square meters:
	 * valueInM2 = valueInThisUnit * conversionFactorToSquareMeter
	 * 
	 * To convert a value from square meters to this unit:
	 * valueInThisUnit = valueInM2 / conversionFactorToSquareMeter
	 */
	val conversionFactorToSquareMeter: Double
) {
	/**
	 * Square Meter (m²) - The base SI unit for area.
	 * 
	 * One square meter is the area of a square with sides of one meter.
	 * It is the standard unit for area in the metric system.
	 * 
	 * Conversion factor: 1.0 (base unit, no conversion needed)
	 */
	SQUARE_METER(com.forestmusic.converter_android.R.string.unit_square_meter, 1.0),
	
	/**
	 * Square Kilometer (km²) - One million square meters.
	 * 
	 * Used for very large areas such as cities, countries, and geographic regions.
	 * 1 km² = 1,000,000 m² = 100 hectares.
	 * 
	 * Conversion factor: 1,000,000.0 (1 km² = 1,000,000 m²)
	 */
	SQUARE_KILOMETER(com.forestmusic.converter_android.R.string.unit_square_kilometer, 1_000_000.0),
	
	/**
	 * Square Centimeter (cm²) - One ten-thousandth of a square meter.
	 * 
	 * Used for small area measurements in science, engineering, and everyday objects.
	 * 10,000 cm² = 1 m².
	 * 
	 * Conversion factor: 0.0001 (1 cm² = 0.0001 m²)
	 */
	SQUARE_CENTIMETER(com.forestmusic.converter_android.R.string.unit_square_centimeter, 0.0001),
	
	/**
	 * Square Millimeter (mm²) - One millionth of a square meter.
	 * 
	 * Used for very small area measurements in precision engineering and manufacturing.
	 * 1,000,000 mm² = 1 m².
	 * 
	 * Conversion factor: 0.000001 (1 mm² = 0.000001 m²)
	 */
	SQUARE_MILLIMETER(com.forestmusic.converter_android.R.string.unit_square_millimeter, 0.000001),
	
	/**
	 * Hectare (ha) - Metric unit for large land areas.
	 * 
	 * Commonly used in agriculture, forestry, and land measurement.
	 * 1 hectare = 10,000 m² = 0.01 km². 100 hectares = 1 km².
	 * 
	 * Conversion factor: 10,000.0 (1 ha = 10,000 m², exactly)
	 */
	HECTARE(com.forestmusic.converter_android.R.string.unit_hectare, 10_000.0),
	
	/**
	 * Are (a) - Metric unit for land area.
	 * 
	 * Also known as "сотка" in Russian. Used for small to medium land plots.
	 * 1 are = 100 m² = 0.01 hectare. 100 ares = 1 hectare.
	 * 
	 * Conversion factor: 100.0 (1 a = 100 m², exactly)
	 */
	ARE(com.forestmusic.converter_android.R.string.unit_are, 100.0),
	
	/**
	 * Square Foot (ft²) - Imperial unit for area.
	 * 
	 * Commonly used in the United States and United Kingdom for real estate,
	 * construction, and interior measurements. 1 ft² ≈ 0.092903 m².
	 * 
	 * Conversion factor: 0.092903 (1 ft² ≈ 0.092903 m²)
	 */
	SQUARE_FOOT(com.forestmusic.converter_android.R.string.unit_square_foot, 0.092903),
	
	/**
	 * Square Yard (yd²) - Imperial unit for area.
	 * 
	 * Used in the United States and United Kingdom, especially for carpeting
	 * and flooring. 1 yd² ≈ 0.836127 m². 9 ft² = 1 yd².
	 * 
	 * Conversion factor: 0.836127 (1 yd² ≈ 0.836127 m²)
	 */
	SQUARE_YARD(com.forestmusic.converter_android.R.string.unit_square_yard, 0.836127),
	
	/**
	 * Acre (ac) - Imperial unit for large land areas.
	 * 
	 * Commonly used in the United States and United Kingdom for agricultural
	 * and real estate measurements. 1 acre ≈ 4046.8564224 m² ≈ 0.4047 hectares.
	 * 640 acres = 1 square mile.
	 * 
	 * Conversion factor: 4046.8564224 (1 ac ≈ 4046.8564224 m²)
	 */
	ACRE(com.forestmusic.converter_android.R.string.unit_acre, 4046.8564224)
}

