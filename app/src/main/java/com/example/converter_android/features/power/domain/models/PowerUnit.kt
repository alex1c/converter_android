package com.example.converter_android.features.power.domain.models

/**
 * Enum representing power units for power measurements.
 * 
 * Power is a physical quantity that measures the rate at which work is done
 * or energy is transferred. In the International System of Units (SI), power
 * is measured in Watts (W), where 1 W = 1 J/s = 1 N·m/s.
 * 
 * This enum provides nine common power units used in engineering, automotive,
 * HVAC, and everyday applications:
 * - WATT: The base SI unit (1 W = 1 J/s)
 * - KILOWATT: One thousand watts (1 kW = 1000 W)
 * - MEGAWATT: One million watts (1 MW = 1,000,000 W)
 * - HORSEPOWER_METRIC: Metric horsepower (1 PS ≈ 735.49875 W)
 * - HORSEPOWER_MECHANICAL: Mechanical/imperial horsepower (1 hp ≈ 745.699872 W)
 * - BTU_PER_HOUR: British Thermal Unit per hour (1 BTU/h ≈ 0.29307107 W)
 * - CALORIE_PER_SECOND: Power unit (1 cal/s = 4.1868 W)
 * - JOULE_PER_SECOND: Equivalent to watt (1 J/s = 1 W)
 * - FOOT_POUND_PER_MINUTE: Imperial unit (1 ft·lb/min ≈ 0.0225969658 W)
 * 
 * Note: There are two types of horsepower:
 * - Metric horsepower (PS): Used in Europe, ≈ 735.5 W
 * - Mechanical horsepower (hp): Used in US/UK, ≈ 745.7 W
 * 
 * All conversions are performed relative to Watts as the base unit.
 * 
 * @property displayNameRes Resource ID for the localized display name of the unit.
 * @property conversionFactorToWatt Conversion factor to convert this unit to Watts.
 */
enum class PowerUnit(
	val displayNameRes: Int,
	val conversionFactorToWatt: Double
) {
	WATT(com.forestmusic.converter_android.R.string.unit_watt, 1.0),
	KILOWATT(com.forestmusic.converter_android.R.string.unit_kilowatt, 1000.0),
	MEGAWATT(com.forestmusic.converter_android.R.string.unit_megawatt, 1_000_000.0),
	HORSEPOWER_METRIC(com.forestmusic.converter_android.R.string.unit_horsepower_metric, 735.49875),
	HORSEPOWER_MECHANICAL(com.forestmusic.converter_android.R.string.unit_horsepower_mechanical, 745.699872),
	BTU_PER_HOUR(com.forestmusic.converter_android.R.string.unit_btu_per_hour, 0.29307107),
	CALORIE_PER_SECOND(com.forestmusic.converter_android.R.string.unit_calorie_per_second, 4.1868),
	JOULE_PER_SECOND(com.forestmusic.converter_android.R.string.unit_joule_per_second, 1.0),
	FOOT_POUND_PER_MINUTE(com.forestmusic.converter_android.R.string.unit_foot_pound_per_minute, 0.0225969658)
}

