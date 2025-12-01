package com.example.converter_android.features.torque.domain.models

/**
 * Enum representing torque units for rotational force measurements.
 * 
 * Torque (also called moment of force) is a physical quantity that measures
 * the tendency of a force to rotate an object about an axis. In the International
 * System of Units (SI), torque is measured in Newton-meters (N·m).
 * 
 * This enum provides nine common torque units used in automotive, engineering,
 * and mechanical applications:
 * - NEWTON_METER: The base SI unit (1 N·m = 1 N·m)
 * - MILLINEWTON_METER: One thousandth of a N·m (1 mN·m = 0.001 N·m)
 * - KILONEWTON_METER: One thousand N·m (1 kN·m = 1000 N·m)
 * - DECANEWTON_METER: Ten N·m (1 daN·m = 10 N·m)
 * - POUND_FOOT: Imperial unit (1 lbf·ft ≈ 1.355818 N·m)
 * - POUND_INCH: Imperial unit (1 lbf·in ≈ 0.1129848 N·m)
 * - OUNCE_INCH: Imperial unit (1 ozf·in ≈ 0.00706155 N·m)
 * - KILOGRAM_FORCE_METER: Metric unit (1 kgf·m = 9.80665 N·m, exactly)
 * - KILOGRAM_FORCE_CENTIMETER: Metric unit (1 kgf·cm = 0.0980665 N·m)
 * 
 * Note: Kilogram-force (kgf) is a unit of force where 1 kgf = 9.80665 N (standard gravity).
 * Torque in kgf·m is commonly used in automotive specifications, especially in metric countries.
 * 
 * Common torque references:
 * - Small electric motor: ~0.1-1 N·m
 * - Car engine: ~200-500 N·m
 * - Industrial motor: ~1000+ N·m
 * 
 * All conversions are performed relative to Newton-meters as the base unit.
 * 
 * @property displayNameRes Resource ID for the localized display name of the unit.
 * @property conversionFactorToNewtonMeter Conversion factor to convert this unit to N·m.
 */
enum class TorqueUnit(
	val displayNameRes: Int,
	val conversionFactorToNewtonMeter: Double
) {
	NEWTON_METER(com.forestmusic.converter_android.R.string.unit_newton_meter, 1.0),
	MILLINEWTON_METER(com.forestmusic.converter_android.R.string.unit_millinewton_meter, 0.001),
	KILONEWTON_METER(com.forestmusic.converter_android.R.string.unit_kilonewton_meter, 1000.0),
	DECANEWTON_METER(com.forestmusic.converter_android.R.string.unit_decanewton_meter, 10.0),
	POUND_FOOT(com.forestmusic.converter_android.R.string.unit_pound_foot, 1.355818),
	POUND_INCH(com.forestmusic.converter_android.R.string.unit_pound_inch, 0.1129848),
	OUNCE_INCH(com.forestmusic.converter_android.R.string.unit_ounce_inch, 0.00706155),
	KILOGRAM_FORCE_METER(com.forestmusic.converter_android.R.string.unit_kilogram_force_meter, 9.80665),
	KILOGRAM_FORCE_CENTIMETER(com.forestmusic.converter_android.R.string.unit_kilogram_force_centimeter, 0.0980665)
}

