package com.example.converter_android.features.angle.domain.models

import kotlin.math.PI

/**
 * Enum representing angle units for angular measurements.
 * 
 * An angle is a measure of rotation or the amount of turn between two lines
 * that meet at a point. Angles can be measured in various units, with degrees
 * being the most common in everyday use.
 * 
 * This enum provides six common angle units used in mathematics, engineering,
 * navigation, and astronomy:
 * - DEGREE: The most common unit (1° = 1°)
 * - RADIAN: SI derived unit, used in mathematics (1 rad = 180°/π ≈ 57.2958°)
 * - GRAD: Also called "gon", used in some European countries (1 grad = 0.9°)
 * - ARC_MINUTE: One sixtieth of a degree (1' = 1/60°)
 * - ARC_SECOND: One sixtieth of an arc minute (1" = 1/3600°)
 * - TURN: Full rotation (1 turn = 360° = 2π rad)
 * 
 * Conversion relationships:
 * - 1 turn = 360° = 2π rad = 400 grad
 * - 1° = 60' = 3600"
 * - 1 rad = 180°/π ≈ 57.2958°
 * - 1 grad = 0.9° = π/200 rad
 * 
 * All conversions are performed relative to degrees as the base unit.
 * 
 * @property displayNameRes Resource ID for the localized display name of the unit.
 * @property conversionFactorToDegree Conversion factor to convert this unit to degrees.
 */
enum class AngleUnit(
	val displayNameRes: Int,
	val conversionFactorToDegree: Double
) {
	DEGREE(com.forestmusic.converter_android.R.string.unit_degree, 1.0),
	RADIAN(com.forestmusic.converter_android.R.string.unit_radian, 180.0 / PI),
	GRAD(com.forestmusic.converter_android.R.string.unit_grad, 0.9),
	ARC_MINUTE(com.forestmusic.converter_android.R.string.unit_arc_minute, 1.0 / 60.0),
	ARC_SECOND(com.forestmusic.converter_android.R.string.unit_arc_second, 1.0 / 3600.0),
	TURN(com.forestmusic.converter_android.R.string.unit_turn, 360.0)
}

