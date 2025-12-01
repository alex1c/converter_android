package com.example.converter_android.features.energy.domain.models

/**
 * Enum representing energy units for energy measurements.
 * 
 * Energy is a fundamental physical quantity that measures the capacity to do work.
 * In the International System of Units (SI), energy is measured in Joules (J),
 * where 1 J = 1 N·m = 1 kg·m²/s².
 * 
 * This enum provides ten common energy units used in physics, chemistry, engineering,
 * and everyday life:
 * - JOULE: The base SI unit (1 J = 1 N·m)
 * - KILOJOULE: One thousand joules (1 kJ = 1000 J)
 * - MEGAJOULE: One million joules (1 MJ = 1,000,000 J)
 * - WATT_HOUR: Energy unit (1 Wh = 3600 J)
 * - KILOWATT_HOUR: Commonly used for electrical energy (1 kWh = 3,600,000 J)
 * - CALORIE: Used in nutrition and chemistry (1 cal = 4.184 J)
 * - KILOCALORIE: Food energy unit (1 kcal = 4184 J)
 * - BTU: British Thermal Unit, imperial unit (1 BTU ≈ 1055.06 J)
 * - ERG: CGS unit, very small (1 erg = 0.0000001 J)
 * - ELECTRON_VOLT: Used in particle physics (1 eV ≈ 1.602176634×10⁻¹⁹ J)
 * 
 * All conversions are performed relative to Joules as the base unit.
 * 
 * @property displayNameRes Resource ID for the localized display name of the unit.
 * @property conversionFactorToJoule Conversion factor to convert this unit to Joules.
 */
enum class EnergyUnit(
	val displayNameRes: Int,
	val conversionFactorToJoule: Double
) {
	JOULE(com.example.converter_android.R.string.unit_joule, 1.0),
	KILOJOULE(com.example.converter_android.R.string.unit_kilojoule, 1000.0),
	MEGAJOULE(com.example.converter_android.R.string.unit_megajoule, 1_000_000.0),
	WATT_HOUR(com.example.converter_android.R.string.unit_watt_hour, 3600.0),
	KILOWATT_HOUR(com.example.converter_android.R.string.unit_kilowatt_hour, 3_600_000.0),
	CALORIE(com.example.converter_android.R.string.unit_calorie, 4.184),
	KILOCALORIE(com.example.converter_android.R.string.unit_kilocalorie, 4184.0),
	BTU(com.example.converter_android.R.string.unit_btu, 1055.06),
	ERG(com.example.converter_android.R.string.unit_erg, 0.0000001),
	ELECTRON_VOLT(com.example.converter_android.R.string.unit_electron_volt, 1.602176634e-19)
}

