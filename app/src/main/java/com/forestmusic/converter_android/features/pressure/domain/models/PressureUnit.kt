package com.forestmusic.converter_android.features.pressure.domain.models

/**
 * Enum representing pressure units for pressure measurements.
 * 
 * Pressure is a physical quantity that measures the force applied perpendicular
 * to the surface of an object per unit area. In the International System of Units (SI),
 * pressure is measured in Pascals (Pa), where 1 Pa = 1 N/m².
 * 
 * This enum provides ten common pressure units used in engineering, meteorology,
 * medicine, and industry:
 * - PASCAL: The base SI unit (1 Pa = 1 N/m²)
 * - KILOPASCAL: One thousand pascals (1 kPa = 1000 Pa)
 * - MEGAPASCAL: One million pascals (1 MPa = 1,000,000 Pa)
 * - BAR: Metric unit (1 bar = 100,000 Pa)
 * - MILLIBAR: One thousandth of a bar (1 mbar = 100 Pa)
 * - ATMOSPHERE_TECHNICAL: Technical atmosphere (1 at ≈ 98,066.5 Pa)
 * - ATMOSPHERE_STANDARD: Standard atmosphere (1 atm = 101,325 Pa, exactly)
 * - MILLIMETER_MERCURY: mmHg, used in medicine (1 mmHg ≈ 133.322 Pa)
 * - INCH_MERCURY: inHg, used in meteorology (1 inHg ≈ 3386.39 Pa)
 * - PSI: Pound per square inch, imperial unit (1 psi ≈ 6894.76 Pa)
 * 
 * All conversions are performed relative to Pascals as the base unit.
 * To convert from any unit to Pa, multiply by the conversion factor.
 * To convert from Pa to any unit, divide by the conversion factor.
 * 
 * Common pressure references:
 * - Atmospheric pressure at sea level: ~101,325 Pa (~101.325 kPa, ~1 atm)
 * - Tire pressure: ~200-300 kPa (~29-43 psi)
 * - Blood pressure (systolic): ~16 kPa (~120 mmHg)
 * 
 * @property displayNameRes Resource ID for the localized display name of the unit.
 * @property conversionFactorToPascal Conversion factor to convert this unit to Pascals.
 */
enum class PressureUnit(
	val displayNameRes: Int,
	val conversionFactorToPascal: Double
) {
	PASCAL(com.forestmusic.converter_android.R.string.unit_pascal, 1.0),
	KILOPASCAL(com.forestmusic.converter_android.R.string.unit_kilopascal, 1000.0),
	MEGAPASCAL(com.forestmusic.converter_android.R.string.unit_megapascal, 1_000_000.0),
	BAR(com.forestmusic.converter_android.R.string.unit_bar, 100_000.0),
	MILLIBAR(com.forestmusic.converter_android.R.string.unit_millibar, 100.0),
	ATMOSPHERE_TECHNICAL(com.forestmusic.converter_android.R.string.unit_atmosphere_technical, 98_066.5),
	ATMOSPHERE_STANDARD(com.forestmusic.converter_android.R.string.unit_atmosphere_standard, 101_325.0),
	MILLIMETER_MERCURY(com.forestmusic.converter_android.R.string.unit_millimeter_mercury, 133.322),
	INCH_MERCURY(com.forestmusic.converter_android.R.string.unit_inch_mercury, 3386.39),
	PSI(com.forestmusic.converter_android.R.string.unit_psi, 6894.76)
}

