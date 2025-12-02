package com.forestmusic.converter_android.features.energy.domain

import com.forestmusic.converter_android.features.energy.domain.models.EnergyUnit
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for energy conversion use case.
 * Tests conversion accuracy with tolerance ≤ 0.0001.
 */
class ConvertEnergyUseCaseTest {
	private val useCase = ConvertEnergyUseCase()
	private val tolerance = 0.0001

	@Test
	fun testJouleToJoule() {
		val result = useCase.convert(100.0, EnergyUnit.JOULE, EnergyUnit.JOULE)
		assertEquals(100.0, result, tolerance)
	}

	@Test
	fun testJouleToKilojoule() {
		val result = useCase.convert(1000.0, EnergyUnit.JOULE, EnergyUnit.KILOJOULE)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testKilojouleToJoule() {
		val result = useCase.convert(1.0, EnergyUnit.KILOJOULE, EnergyUnit.JOULE)
		assertEquals(1000.0, result, tolerance)
	}

	@Test
	fun testJouleToMegajoule() {
		val result = useCase.convert(1_000_000.0, EnergyUnit.JOULE, EnergyUnit.MEGAJOULE)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testMegajouleToJoule() {
		val result = useCase.convert(1.0, EnergyUnit.MEGAJOULE, EnergyUnit.JOULE)
		assertEquals(1_000_000.0, result, tolerance)
	}

	@Test
	fun testWattHourToJoule() {
		val result = useCase.convert(1.0, EnergyUnit.WATT_HOUR, EnergyUnit.JOULE)
		assertEquals(3600.0, result, tolerance)
	}

	@Test
	fun testJouleToWattHour() {
		val result = useCase.convert(3600.0, EnergyUnit.JOULE, EnergyUnit.WATT_HOUR)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testKilowattHourToJoule() {
		val result = useCase.convert(1.0, EnergyUnit.KILOWATT_HOUR, EnergyUnit.JOULE)
		assertEquals(3_600_000.0, result, tolerance)
	}

	@Test
	fun testJouleToKilowattHour() {
		val result = useCase.convert(3_600_000.0, EnergyUnit.JOULE, EnergyUnit.KILOWATT_HOUR)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testCalorieToJoule() {
		val result = useCase.convert(1.0, EnergyUnit.CALORIE, EnergyUnit.JOULE)
		assertEquals(4.184, result, tolerance)
	}

	@Test
	fun testJouleToCalorie() {
		val result = useCase.convert(4.184, EnergyUnit.JOULE, EnergyUnit.CALORIE)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testKilocalorieToJoule() {
		val result = useCase.convert(1.0, EnergyUnit.KILOCALORIE, EnergyUnit.JOULE)
		assertEquals(4184.0, result, tolerance)
	}

	@Test
	fun testJouleToKilocalorie() {
		val result = useCase.convert(4184.0, EnergyUnit.JOULE, EnergyUnit.KILOCALORIE)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testBTUToJoule() {
		val result = useCase.convert(1.0, EnergyUnit.BTU, EnergyUnit.JOULE)
		assertEquals(1055.06, result, tolerance)
	}

	@Test
	fun testJouleToBTU() {
		val result = useCase.convert(1055.06, EnergyUnit.JOULE, EnergyUnit.BTU)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testErgToJoule() {
		val result = useCase.convert(10_000_000.0, EnergyUnit.ERG, EnergyUnit.JOULE)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testJouleToErg() {
		val result = useCase.convert(1.0, EnergyUnit.JOULE, EnergyUnit.ERG)
		assertEquals(10_000_000.0, result, tolerance)
	}

	@Test
	fun testElectronVoltToJoule() {
		val result = useCase.convert(1.0, EnergyUnit.ELECTRON_VOLT, EnergyUnit.JOULE)
		assertEquals(1.602176634e-19, result, 1e-25) // Very small tolerance for eV
	}

	@Test
	fun testJouleToElectronVolt() {
		val result = useCase.convert(1.602176634e-19, EnergyUnit.JOULE, EnergyUnit.ELECTRON_VOLT)
		assertEquals(1.0, result, 1e-10) // Higher tolerance for very small values
	}

	@Test
	fun testKilowattHourToCalorie() {
		// 1 kWh = 3,600,000 J = 3,600,000 / 4.184 ≈ 860,421 cal
		val result = useCase.convert(1.0, EnergyUnit.KILOWATT_HOUR, EnergyUnit.CALORIE)
		assertEquals(860421.0, result, 10.0) // Higher tolerance for complex conversion
	}

	@Test
	fun testCalorieToKilowattHour() {
		// 1 cal = 4.184 J = 4.184 / 3,600,000 ≈ 0.000001162 kWh
		val result = useCase.convert(1.0, EnergyUnit.CALORIE, EnergyUnit.KILOWATT_HOUR)
		assertEquals(0.000001162, result, tolerance)
	}

	@Test
	fun testBTUToKilowattHour() {
		// 1 BTU = 1055.06 J = 1055.06 / 3,600,000 ≈ 0.000293072 kWh
		val result = useCase.convert(1.0, EnergyUnit.BTU, EnergyUnit.KILOWATT_HOUR)
		assertEquals(0.000293072, result, tolerance)
	}

	@Test
	fun testKilowattHourToBTU() {
		// 1 kWh = 3,600,000 J = 3,600,000 / 1055.06 ≈ 3412.14 BTU
		val result = useCase.convert(1.0, EnergyUnit.KILOWATT_HOUR, EnergyUnit.BTU)
		assertEquals(3412.14, result, 0.1) // Higher tolerance for complex conversion
	}

	@Test
	fun testConversionAccuracy() {
		// Test round-trip conversion: J -> kJ -> J
		val originalValue = 1000.0
		val toKilojoule = useCase.convert(originalValue, EnergyUnit.JOULE, EnergyUnit.KILOJOULE)
		val backToJoule = useCase.convert(toKilojoule, EnergyUnit.KILOJOULE, EnergyUnit.JOULE)
		assertEquals(originalValue, backToJoule, tolerance)
	}

	@Test
	fun testComplexConversion() {
		// Test complex conversion: kWh -> J -> cal -> BTU
		val kWhValue = 1.0
		val toJoule = useCase.convert(kWhValue, EnergyUnit.KILOWATT_HOUR, EnergyUnit.JOULE)
		val toCalorie = useCase.convert(toJoule, EnergyUnit.JOULE, EnergyUnit.CALORIE)
		val toBTU = useCase.convert(toCalorie, EnergyUnit.CALORIE, EnergyUnit.BTU)
		// 1 kWh = 3,600,000 J = 860,421 cal ≈ 3412.14 BTU
		assertEquals(3412.14, toBTU, 1.0) // Higher tolerance for multiple conversions
	}

	@Test
	fun testCoefficientAccuracy() {
		// Test that all coefficients are correct by converting 1 unit to Joules
		val testCases = mapOf(
			EnergyUnit.JOULE to 1.0,
			EnergyUnit.KILOJOULE to 1000.0,
			EnergyUnit.MEGAJOULE to 1_000_000.0,
			EnergyUnit.WATT_HOUR to 3600.0,
			EnergyUnit.KILOWATT_HOUR to 3_600_000.0,
			EnergyUnit.CALORIE to 4.184,
			EnergyUnit.KILOCALORIE to 4184.0,
			EnergyUnit.BTU to 1055.06,
			EnergyUnit.ERG to 0.0000001,
			EnergyUnit.ELECTRON_VOLT to 1.602176634e-19
		)

		testCases.forEach { (unit, expectedJoules) ->
			val result = useCase.convert(1.0, unit, EnergyUnit.JOULE)
			val toleranceForTest = if (unit == EnergyUnit.ELECTRON_VOLT) 1e-25 else tolerance
			assertEquals(
				"Coefficient for ${unit.name} is incorrect",
				expectedJoules,
				result,
				toleranceForTest
			)
		}
	}
}

