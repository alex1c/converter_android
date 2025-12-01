package com.example.converter_android.features.power.domain

import com.example.converter_android.features.power.domain.models.PowerUnit
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for power conversion use case.
 * Tests conversion accuracy with tolerance ≤ 0.0001.
 */
class ConvertPowerUseCaseTest {
	private val useCase = ConvertPowerUseCase()
	private val tolerance = 0.0001

	@Test
	fun testWattToWatt() {
		val result = useCase.convert(100.0, PowerUnit.WATT, PowerUnit.WATT)
		assertEquals(100.0, result, tolerance)
	}

	@Test
	fun testKilowattToWatt() {
		// 1 kW = 1000 W
		val result = useCase.convert(1.0, PowerUnit.KILOWATT, PowerUnit.WATT)
		assertEquals(1000.0, result, tolerance)
	}

	@Test
	fun testWattToKilowatt() {
		// 1000 W = 1 kW
		val result = useCase.convert(1000.0, PowerUnit.WATT, PowerUnit.KILOWATT)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testMegawattToWatt() {
		// 1 MW = 1,000,000 W
		val result = useCase.convert(1.0, PowerUnit.MEGAWATT, PowerUnit.WATT)
		assertEquals(1_000_000.0, result, tolerance)
	}

	@Test
	fun testWattToMegawatt() {
		// 1,000,000 W = 1 MW
		val result = useCase.convert(1_000_000.0, PowerUnit.WATT, PowerUnit.MEGAWATT)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testHorsepowerMetricToWatt() {
		// 1 hp (metric) = 735.49875 W
		val result = useCase.convert(1.0, PowerUnit.HORSEPOWER_METRIC, PowerUnit.WATT)
		assertEquals(735.49875, result, tolerance)
	}

	@Test
	fun testWattToHorsepowerMetric() {
		// 735.49875 W = 1 hp (metric)
		val result = useCase.convert(735.49875, PowerUnit.WATT, PowerUnit.HORSEPOWER_METRIC)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testHorsepowerMechanicalToWatt() {
		// 1 hp (mechanical) = 745.699872 W
		val result = useCase.convert(1.0, PowerUnit.HORSEPOWER_MECHANICAL, PowerUnit.WATT)
		assertEquals(745.699872, result, tolerance)
	}

	@Test
	fun testWattToHorsepowerMechanical() {
		// 745.699872 W = 1 hp (mechanical)
		val result = useCase.convert(745.699872, PowerUnit.WATT, PowerUnit.HORSEPOWER_MECHANICAL)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testBtuPerHourToWatt() {
		// 1 BTU/h = 0.29307107 W
		val result = useCase.convert(1.0, PowerUnit.BTU_PER_HOUR, PowerUnit.WATT)
		assertEquals(0.29307107, result, tolerance)
	}

	@Test
	fun testWattToBtuPerHour() {
		// 0.29307107 W = 1 BTU/h
		val result = useCase.convert(0.29307107, PowerUnit.WATT, PowerUnit.BTU_PER_HOUR)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testCaloriePerSecondToWatt() {
		// 1 cal/s = 4.1868 W
		val result = useCase.convert(1.0, PowerUnit.CALORIE_PER_SECOND, PowerUnit.WATT)
		assertEquals(4.1868, result, tolerance)
	}

	@Test
	fun testWattToCaloriePerSecond() {
		// 4.1868 W = 1 cal/s
		val result = useCase.convert(4.1868, PowerUnit.WATT, PowerUnit.CALORIE_PER_SECOND)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testJoulePerSecondToWatt() {
		// 1 J/s = 1 W (equivalent)
		val result = useCase.convert(1.0, PowerUnit.JOULE_PER_SECOND, PowerUnit.WATT)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testWattToJoulePerSecond() {
		// 1 W = 1 J/s
		val result = useCase.convert(1.0, PowerUnit.WATT, PowerUnit.JOULE_PER_SECOND)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testFootPoundPerMinuteToWatt() {
		// 1 ft·lb/min = 0.0225969658 W
		val result = useCase.convert(1.0, PowerUnit.FOOT_POUND_PER_MINUTE, PowerUnit.WATT)
		assertEquals(0.0225969658, result, tolerance)
	}

	@Test
	fun testWattToFootPoundPerMinute() {
		// 0.0225969658 W = 1 ft·lb/min
		val result = useCase.convert(0.0225969658, PowerUnit.WATT, PowerUnit.FOOT_POUND_PER_MINUTE)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testKilowattToHorsepowerMetric() {
		// 1 kW = 1000 W = 1000 / 735.49875 hp (metric)
		val expectedHp = 1000.0 / 735.49875
		val result = useCase.convert(1.0, PowerUnit.KILOWATT, PowerUnit.HORSEPOWER_METRIC)
		assertEquals(expectedHp, result, tolerance)
	}

	@Test
	fun testHorsepowerMetricToKilowatt() {
		// 1 hp (metric) = 735.49875 W = 0.73549875 kW
		val result = useCase.convert(1.0, PowerUnit.HORSEPOWER_METRIC, PowerUnit.KILOWATT)
		assertEquals(0.73549875, result, tolerance)
	}

	@Test
	fun testHorsepowerMechanicalToKilowatt() {
		// 1 hp (mechanical) = 745.699872 W = 0.745699872 kW
		val result = useCase.convert(1.0, PowerUnit.HORSEPOWER_MECHANICAL, PowerUnit.KILOWATT)
		assertEquals(0.745699872, result, tolerance)
	}

	@Test
	fun testBtuPerHourToKilowatt() {
		// 1 BTU/h = 0.29307107 W = 0.00029307107 kW
		val result = useCase.convert(1.0, PowerUnit.BTU_PER_HOUR, PowerUnit.KILOWATT)
		assertEquals(0.00029307107, result, tolerance)
	}

	@Test
	fun testFootPoundPerMinuteToHorsepowerMechanical() {
		// 1 hp (mechanical) = 33000 ft·lb/min
		// 1 ft·lb/min = 1 / 33000 hp (mechanical)
		val expectedHp = 1.0 / 33000.0
		val result = useCase.convert(1.0, PowerUnit.FOOT_POUND_PER_MINUTE, PowerUnit.HORSEPOWER_MECHANICAL)
		assertEquals(expectedHp, result, tolerance)
	}

	@Test
	fun testCaloriePerSecondToBtuPerHour() {
		// 1 cal/s = 4.1868 W
		// 1 BTU/h = 0.29307107 W
		// 1 cal/s = 4.1868 / 0.29307107 BTU/h
		val expectedBtuH = 4.1868 / 0.29307107
		val result = useCase.convert(1.0, PowerUnit.CALORIE_PER_SECOND, PowerUnit.BTU_PER_HOUR)
		assertEquals(expectedBtuH, result, tolerance)
	}

	@Test
	fun testRoundTripConversion() {
		// Test round-trip: W -> kW -> W
		val originalValue = 1000.0
		val toKilowatt = useCase.convert(originalValue, PowerUnit.WATT, PowerUnit.KILOWATT)
		val backToWatt = useCase.convert(toKilowatt, PowerUnit.KILOWATT, PowerUnit.WATT)
		assertEquals(originalValue, backToWatt, tolerance)
	}

	@Test
	fun testComplexConversion() {
		// Test complex conversion: hp (metric) -> W -> kW -> hp (mechanical)
		val hpMetric = 1.0
		val toWatt = useCase.convert(hpMetric, PowerUnit.HORSEPOWER_METRIC, PowerUnit.WATT)
		assertEquals(735.49875, toWatt, tolerance)
		
		val toKilowatt = useCase.convert(toWatt, PowerUnit.WATT, PowerUnit.KILOWATT)
		assertEquals(0.73549875, toKilowatt, tolerance)
		
		val toHpMechanical = useCase.convert(toKilowatt, PowerUnit.KILOWATT, PowerUnit.HORSEPOWER_MECHANICAL)
		val expectedHpMechanical = 735.49875 / 745.699872
		assertEquals(expectedHpMechanical, toHpMechanical, tolerance)
	}

	@Test
	fun testCoefficientAccuracy() {
		// Test that all coefficients are correct by converting 1 unit to watts
		val testCases = mapOf(
			PowerUnit.WATT to 1.0,
			PowerUnit.KILOWATT to 1000.0,
			PowerUnit.MEGAWATT to 1_000_000.0,
			PowerUnit.HORSEPOWER_METRIC to 735.49875,
			PowerUnit.HORSEPOWER_MECHANICAL to 745.699872,
			PowerUnit.BTU_PER_HOUR to 0.29307107,
			PowerUnit.CALORIE_PER_SECOND to 4.1868,
			PowerUnit.JOULE_PER_SECOND to 1.0,
			PowerUnit.FOOT_POUND_PER_MINUTE to 0.0225969658
		)

		testCases.forEach { (unit, expectedWatts) ->
			val result = useCase.convert(1.0, unit, PowerUnit.WATT)
			assertEquals(
				"Coefficient for ${unit.name} is incorrect",
				expectedWatts,
				result,
				tolerance
			)
		}
	}
}

