package com.example.converter_android.features.speed.domain.models

/**
 * Enum representing speed units for velocity measurements.
 * 
 * Speed (or velocity) is a derived physical quantity that measures the rate of change
 * of position with respect to time. In the International System of Units (SI), speed
 * is measured in meters per second (m/s).
 * 
 * This enum provides five common speed units used in everyday life, transportation,
 * and navigation:
 * - METER_PER_SECOND: The base SI unit (1 m/s = 1 m/s)
 * - KILOMETER_PER_HOUR: Commonly used for road speeds (1 km/h ≈ 0.277778 m/s)
 * - MILE_PER_HOUR: Imperial unit for road speeds (1 mph ≈ 0.44704 m/s)
 * - KNOT: Navigation unit for maritime and aviation speeds (1 kn ≈ 0.514444 m/s)
 * - FOOT_PER_SECOND: Imperial unit (1 ft/s = 0.3048 m/s)
 * 
 * All conversions are performed relative to meters per second as the base unit.
 * To convert from any unit to m/s, multiply by the conversion factor.
 * To convert from m/s to any unit, divide by the conversion factor.
 * 
 * Conversion factors are based on international standards:
 * - Metric units use exact or precise decimal relationships
 * - Imperial units use precise conversion factors
 * - Knot uses the international nautical mile standard
 * 
 * Common speed references:
 * - Walking speed: ~1.4 m/s (~5 km/h)
 * - Running speed: ~3-5 m/s (~10-18 km/h)
 * - Highway speed: ~30 m/s (~108 km/h or ~67 mph)
 * - Sound speed: ~343 m/s (~1235 km/h)
 * 
 * @property displayNameRes Resource ID for the localized display name of the unit.
 *                        Used in UI to display the unit name in the user's language.
 * @property conversionFactorToMps Conversion factor to convert this unit to meters per second.
 *                                To convert a value to m/s: value * conversionFactorToMps
 *                                To convert from m/s: value / conversionFactorToMps
 * 
 * @example
 * // Convert 100 km/h to m/s
 * val mps = 100.0 * SpeedUnit.KILOMETER_PER_HOUR.conversionFactorToMps // ≈ 27.7778 m/s
 * 
 * // Convert 60 mph to km/h
 * val kmh = 60.0 * SpeedUnit.MILE_PER_HOUR.conversionFactorToMps / SpeedUnit.KILOMETER_PER_HOUR.conversionFactorToMps // ≈ 96.56 km/h
 */
enum class SpeedUnit(
	/**
	 * Resource ID for the localized display name of this speed unit.
	 * Used in UI to display the unit name in the user's language.
	 */
	val displayNameRes: Int,
	
	/**
	 * Conversion factor to convert this unit to meters per second (the base unit).
	 * 
	 * To convert a value from this unit to meters per second:
	 * valueInMps = valueInThisUnit * conversionFactorToMps
	 * 
	 * To convert a value from meters per second to this unit:
	 * valueInThisUnit = valueInMps / conversionFactorToMps
	 */
	val conversionFactorToMps: Double
) {
	/**
	 * Meter per second (m/s) - The base SI unit for speed.
	 * 
	 * One meter per second is the speed of an object that travels one meter
	 * in one second. It is the standard unit for speed in the metric system.
	 * 
	 * Conversion factor: 1.0 (base unit, no conversion needed)
	 */
	METER_PER_SECOND(com.forestmusic.converter_android.R.string.unit_meter_per_second, 1.0),
	
	/**
	 * Kilometer per hour (km/h) - Commonly used for road speeds.
	 * 
	 * Widely used worldwide for vehicle speeds, wind speeds, and general
	 * speed measurements. 1 km/h = 1000 m / 3600 s ≈ 0.277778 m/s.
	 * 
	 * Conversion factor: 0.277778 (1 km/h ≈ 0.277778 m/s)
	 * 
	 * @example
	 * 100 km/h ≈ 27.78 m/s
	 * 50 km/h ≈ 13.89 m/s
	 */
	KILOMETER_PER_HOUR(com.forestmusic.converter_android.R.string.unit_kilometer_per_hour, 0.277778),
	
	/**
	 * Mile per hour (mph) - Imperial unit for road speeds.
	 * 
	 * Commonly used in the United States and United Kingdom for vehicle speeds.
	 * 1 mph = 1609.344 m / 3600 s ≈ 0.44704 m/s.
	 * 
	 * Conversion factor: 0.44704 (1 mph ≈ 0.44704 m/s)
	 * 
	 * @example
	 * 60 mph ≈ 26.82 m/s ≈ 96.56 km/h
	 * 30 mph ≈ 13.41 m/s ≈ 48.28 km/h
	 */
	MILE_PER_HOUR(com.forestmusic.converter_android.R.string.unit_mile_per_hour, 0.44704),
	
	/**
	 * Knot (kn) - Navigation unit for maritime and aviation speeds.
	 * 
	 * One knot equals one nautical mile per hour. Used in navigation, maritime,
	 * and aviation applications. 1 knot = 1852 m / 3600 s ≈ 0.514444 m/s.
	 * 
	 * Conversion factor: 0.514444 (1 kn ≈ 0.514444 m/s)
	 * 
	 * @example
	 * 1 knot ≈ 0.514 m/s ≈ 1.852 km/h ≈ 1.151 mph
	 */
	KNOT(com.forestmusic.converter_android.R.string.unit_knot, 0.514444),
	
	/**
	 * Foot per second (ft/s) - Imperial unit for speed.
	 * 
	 * Used in some engineering and scientific applications in the United States.
	 * 1 ft/s = 0.3048 m / 1 s = 0.3048 m/s (exactly).
	 * 
	 * Conversion factor: 0.3048 (1 ft/s = 0.3048 m/s, exactly)
	 */
	FOOT_PER_SECOND(com.forestmusic.converter_android.R.string.unit_foot_per_second, 0.3048)
}

