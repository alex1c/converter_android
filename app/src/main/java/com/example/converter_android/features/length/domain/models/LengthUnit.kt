package com.example.converter_android.features.length.domain.models

/**
 * Enum representing length units for distance measurements.
 * 
 * Length (or distance) is a fundamental physical quantity that measures the
 * extent of space between two points. In the International System of Units (SI),
 * length is measured in meters (m).
 * 
 * This enum provides nine common length units used in everyday life, science,
 * and navigation:
 * - METER: The base SI unit (1 m = 1 m)
 * - KILOMETER: One thousand meters (1 km = 1000 m)
 * - CENTIMETER: One hundredth of a meter (1 cm = 0.01 m)
 * - MILLIMETER: One thousandth of a meter (1 mm = 0.001 m)
 * - MILE: Imperial unit (1 mi = 1609.344 m)
 * - YARD: Imperial unit (1 yd = 0.9144 m)
 * - FOOT: Imperial unit (1 ft = 0.3048 m)
 * - INCH: Imperial unit (1 in = 0.0254 m)
 * - NAUTICAL_MILE: Navigation unit (1 nmi = 1852 m)
 * 
 * All conversions are performed relative to meters as the base unit.
 * To convert from any unit to meters, multiply by the conversion factor.
 * To convert from meters to any unit, divide by the conversion factor.
 * 
 * Conversion factors are based on international standards:
 * - Metric units (kilometer, centimeter, millimeter) use exact decimal relationships
 * - Imperial units (mile, yard, foot, inch) use precise conversion factors
 * - Nautical mile uses the international standard (exactly 1852 meters)
 * 
 * @property displayNameRes Resource ID for the localized display name of the unit.
 *                        Used in UI to display the unit name in the user's language.
 * @property conversionFactorToMeter Conversion factor to convert this unit to meters.
 *                                  To convert a value to m: value * conversionFactorToMeter
 *                                  To convert from m: value / conversionFactorToMeter
 * 
 * @example
 * // Convert 1000 meters to kilometers
 * val km = 1000.0 * LengthUnit.METER.conversionFactorToMeter / LengthUnit.KILOMETER.conversionFactorToMeter // 1.0 km
 * 
 * // Convert 1 mile to meters
 * val m = 1.0 * LengthUnit.MILE.conversionFactorToMeter // 1609.344 m
 */
enum class LengthUnit(
	/**
	 * Resource ID for the localized display name of this length unit.
	 * Used in UI to display the unit name in the user's language.
	 */
	val displayNameRes: Int,
	
	/**
	 * Conversion factor to convert this unit to meters (the base unit).
	 * 
	 * To convert a value from this unit to meters:
	 * valueInMeters = valueInThisUnit * conversionFactorToMeter
	 * 
	 * To convert a value from meters to this unit:
	 * valueInThisUnit = valueInMeters / conversionFactorToMeter
	 */
	val conversionFactorToMeter: Double
) {
	/**
	 * Meter (m) - The base SI unit for length.
	 * 
	 * One meter is defined as the length of the path travelled by light in vacuum
	 * during a time interval of 1/299,792,458 of a second.
	 * 
	 * Conversion factor: 1.0 (base unit, no conversion needed)
	 */
	METER(com.forestmusic.converter_android.R.string.unit_meter, 1.0),
	
	/**
	 * Kilometer (km) - One thousand meters.
	 * 
	 * Commonly used for long distances in everyday life, transportation, and geography.
	 * 1 kilometer = 1000 meters.
	 * 
	 * Conversion factor: 1000.0 (1 km = 1000 m)
	 */
	KILOMETER(com.forestmusic.converter_android.R.string.unit_kilometer, 1000.0),
	
	/**
	 * Centimeter (cm) - One hundredth of a meter.
	 * 
	 * Commonly used for medium-sized measurements in everyday life, construction,
	 * and science. 100 centimeters = 1 meter.
	 * 
	 * Conversion factor: 0.01 (1 cm = 0.01 m)
	 */
	CENTIMETER(com.forestmusic.converter_android.R.string.unit_centimeter, 0.01),
	
	/**
	 * Millimeter (mm) - One thousandth of a meter.
	 * 
	 * Used for small measurements in engineering, manufacturing, and precision work.
	 * 1000 millimeters = 1 meter.
	 * 
	 * Conversion factor: 0.001 (1 mm = 0.001 m)
	 */
	MILLIMETER(com.forestmusic.converter_android.R.string.unit_millimeter, 0.001),
	
	/**
	 * Mile (mi) - Imperial unit of length.
	 * 
	 * Commonly used in the United States and United Kingdom for road distances.
	 * The conversion factor is based on the international mile.
	 * 1 mile = 1609.344 meters (exactly).
	 * 
	 * Conversion factor: 1609.344 (1 mi = 1609.344 m)
	 */
	MILE(com.forestmusic.converter_android.R.string.unit_mile, 1609.344),
	
	/**
	 * Yard (yd) - Imperial unit of length.
	 * 
	 * Commonly used in the United States and United Kingdom. 3 feet = 1 yard.
	 * 1 yard = 0.9144 meters (exactly).
	 * 
	 * Conversion factor: 0.9144 (1 yd = 0.9144 m)
	 */
	YARD(com.forestmusic.converter_android.R.string.unit_yard, 0.9144),
	
	/**
	 * Foot (ft) - Imperial unit of length.
	 * 
	 * Commonly used in the United States and United Kingdom. 12 inches = 1 foot.
	 * 1 foot = 0.3048 meters (exactly).
	 * 
	 * Conversion factor: 0.3048 (1 ft = 0.3048 m)
	 */
	FOOT(com.forestmusic.converter_android.R.string.unit_foot, 0.3048),
	
	/**
	 * Inch (in) - Imperial unit of length.
	 * 
	 * Commonly used for small measurements in the United States and United Kingdom.
	 * 12 inches = 1 foot. 1 inch = 0.0254 meters (exactly).
	 * 
	 * Conversion factor: 0.0254 (1 in = 0.0254 m)
	 */
	INCH(com.forestmusic.converter_android.R.string.unit_inch, 0.0254),
	
	/**
	 * Nautical Mile (nmi) - Navigation unit of length.
	 * 
	 * Used in navigation and aviation. Defined as exactly 1852 meters by
	 * international agreement. One nautical mile equals approximately 1.15078
	 * statute miles.
	 * 
	 * Conversion factor: 1852.0 (1 nmi = 1852 m, exactly)
	 */
	NAUTICAL_MILE(com.forestmusic.converter_android.R.string.unit_nautical_mile, 1852.0)
}

