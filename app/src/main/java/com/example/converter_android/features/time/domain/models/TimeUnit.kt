package com.example.converter_android.features.time.domain.models

/**
 * Enum representing time units for time measurements.
 * 
 * Time is a fundamental physical quantity that measures the duration between events.
 * In the International System of Units (SI), time is measured in seconds (s).
 * 
 * This enum provides eight common time units used in everyday life, science, and
 * calendar systems:
 * - MILLISECOND: One thousandth of a second (1 ms = 0.001 s)
 * - SECOND: The base SI unit (1 s = 1 s)
 * - MINUTE: 60 seconds (1 min = 60 s)
 * - HOUR: 3600 seconds (1 h = 3600 s)
 * - DAY: 86400 seconds (1 day = 86400 s = 24 hours)
 * - WEEK: 604800 seconds (1 week = 604800 s = 7 days)
 * - MONTH: Average calendar month (1 month ≈ 2,629,746 s ≈ 30.436875 days)
 * - YEAR: Average calendar year (1 year ≈ 31,556,952 s ≈ 365.2425 days)
 * 
 * Note: Month and year use average values based on the Gregorian calendar.
 * Actual month lengths vary (28-31 days), and leap years affect year length.
 * 
 * All conversions are performed relative to seconds as the base unit.
 * 
 * @property displayNameRes Resource ID for the localized display name of the unit.
 * @property conversionFactorToSecond Conversion factor to convert this unit to seconds.
 */
enum class TimeUnit(
	val displayNameRes: Int,
	val conversionFactorToSecond: Double
) {
	MILLISECOND(com.example.converter_android.R.string.unit_millisecond, 0.001),
	SECOND(com.example.converter_android.R.string.unit_second, 1.0),
	MINUTE(com.example.converter_android.R.string.unit_minute, 60.0),
	HOUR(com.example.converter_android.R.string.unit_hour, 3600.0),
	DAY(com.example.converter_android.R.string.unit_day, 86400.0),
	WEEK(com.example.converter_android.R.string.unit_week, 604800.0),
	MONTH(com.example.converter_android.R.string.unit_month, 2_629_746.0), // 30.436875 days
	YEAR(com.example.converter_android.R.string.unit_year, 31_556_952.0) // 365.2425 days
}

