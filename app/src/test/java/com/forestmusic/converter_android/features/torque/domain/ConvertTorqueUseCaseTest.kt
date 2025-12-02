package com.forestmusic.converter_android.features.torque.domain

import com.forestmusic.converter_android.features.torque.domain.models.TorqueUnit
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for torque conversion use case.
 * Tests conversion accuracy with tolerance ≤ 0.0001.
 */
class ConvertTorqueUseCaseTest {
	private val useCase = ConvertTorqueUseCase()
	private val tolerance = 0.0001

	@Test
	fun testNewtonMeterToNewtonMeter() {
		val result = useCase.convert(100.0, TorqueUnit.NEWTON_METER, TorqueUnit.NEWTON_METER)
		assertEquals(100.0, result, tolerance)
	}

	@Test
	fun testMillinewtonMeterToNewtonMeter() {
		// 1000 mN·m = 1 N·m
		val result = useCase.convert(1000.0, TorqueUnit.MILLINEWTON_METER, TorqueUnit.NEWTON_METER)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testNewtonMeterToMillinewtonMeter() {
		// 1 N·m = 1000 mN·m
		val result = useCase.convert(1.0, TorqueUnit.NEWTON_METER, TorqueUnit.MILLINEWTON_METER)
		assertEquals(1000.0, result, tolerance)
	}

	@Test
	fun testKilonewtonMeterToNewtonMeter() {
		// 1 kN·m = 1000 N·m
		val result = useCase.convert(1.0, TorqueUnit.KILONEWTON_METER, TorqueUnit.NEWTON_METER)
		assertEquals(1000.0, result, tolerance)
	}

	@Test
	fun testNewtonMeterToKilonewtonMeter() {
		// 1000 N·m = 1 kN·m
		val result = useCase.convert(1000.0, TorqueUnit.NEWTON_METER, TorqueUnit.KILONEWTON_METER)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testDecanewtonMeterToNewtonMeter() {
		// 1 daN·m = 10 N·m
		val result = useCase.convert(1.0, TorqueUnit.DECANEWTON_METER, TorqueUnit.NEWTON_METER)
		assertEquals(10.0, result, tolerance)
	}

	@Test
	fun testNewtonMeterToDecanewtonMeter() {
		// 10 N·m = 1 daN·m
		val result = useCase.convert(10.0, TorqueUnit.NEWTON_METER, TorqueUnit.DECANEWTON_METER)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testPoundFootToNewtonMeter() {
		// 1 lbf·ft = 1.355818 N·m
		val result = useCase.convert(1.0, TorqueUnit.POUND_FOOT, TorqueUnit.NEWTON_METER)
		assertEquals(1.355818, result, tolerance)
	}

	@Test
	fun testNewtonMeterToPoundFoot() {
		// 1.355818 N·m = 1 lbf·ft
		val result = useCase.convert(1.355818, TorqueUnit.NEWTON_METER, TorqueUnit.POUND_FOOT)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testPoundInchToNewtonMeter() {
		// 1 lbf·in = 0.1129848 N·m
		val result = useCase.convert(1.0, TorqueUnit.POUND_INCH, TorqueUnit.NEWTON_METER)
		assertEquals(0.1129848, result, tolerance)
	}

	@Test
	fun testNewtonMeterToPoundInch() {
		// 0.1129848 N·m = 1 lbf·in
		val result = useCase.convert(0.1129848, TorqueUnit.NEWTON_METER, TorqueUnit.POUND_INCH)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testOunceInchToNewtonMeter() {
		// 1 ozf·in = 0.00706155 N·m
		val result = useCase.convert(1.0, TorqueUnit.OUNCE_INCH, TorqueUnit.NEWTON_METER)
		assertEquals(0.00706155, result, tolerance)
	}

	@Test
	fun testNewtonMeterToOunceInch() {
		// 0.00706155 N·m = 1 ozf·in
		val result = useCase.convert(0.00706155, TorqueUnit.NEWTON_METER, TorqueUnit.OUNCE_INCH)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testKilogramForceMeterToNewtonMeter() {
		// 1 kgf·m = 9.80665 N·m
		val result = useCase.convert(1.0, TorqueUnit.KILOGRAM_FORCE_METER, TorqueUnit.NEWTON_METER)
		assertEquals(9.80665, result, tolerance)
	}

	@Test
	fun testNewtonMeterToKilogramForceMeter() {
		// 9.80665 N·m = 1 kgf·m
		val result = useCase.convert(9.80665, TorqueUnit.NEWTON_METER, TorqueUnit.KILOGRAM_FORCE_METER)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testKilogramForceCentimeterToNewtonMeter() {
		// 1 kgf·cm = 0.0980665 N·m
		val result = useCase.convert(1.0, TorqueUnit.KILOGRAM_FORCE_CENTIMETER, TorqueUnit.NEWTON_METER)
		assertEquals(0.0980665, result, tolerance)
	}

	@Test
	fun testNewtonMeterToKilogramForceCentimeter() {
		// 0.0980665 N·m = 1 kgf·cm
		val result = useCase.convert(0.0980665, TorqueUnit.NEWTON_METER, TorqueUnit.KILOGRAM_FORCE_CENTIMETER)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testPoundFootToPoundInch() {
		// 1 lbf·ft = 12 lbf·in
		// 1 lbf·ft = 1.355818 N·m
		// 1 lbf·in = 0.1129848 N·m
		// 1 lbf·ft = 1.355818 / 0.1129848 lbf·in = 12.0 lbf·in
		val result = useCase.convert(1.0, TorqueUnit.POUND_FOOT, TorqueUnit.POUND_INCH)
		assertEquals(12.0, result, tolerance)
	}

	@Test
	fun testPoundInchToPoundFoot() {
		// 12 lbf·in = 1 lbf·ft
		val result = useCase.convert(12.0, TorqueUnit.POUND_INCH, TorqueUnit.POUND_FOOT)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testKilogramForceMeterToKilogramForceCentimeter() {
		// 1 kgf·m = 100 kgf·cm
		val result = useCase.convert(1.0, TorqueUnit.KILOGRAM_FORCE_METER, TorqueUnit.KILOGRAM_FORCE_CENTIMETER)
		assertEquals(100.0, result, tolerance)
	}

	@Test
	fun testKilogramForceCentimeterToKilogramForceMeter() {
		// 100 kgf·cm = 1 kgf·m
		val result = useCase.convert(100.0, TorqueUnit.KILOGRAM_FORCE_CENTIMETER, TorqueUnit.KILOGRAM_FORCE_METER)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testRoundTripConversion() {
		// Test round-trip: N·m -> lbf·ft -> N·m
		val originalValue = 10.0
		val toPoundFoot = useCase.convert(originalValue, TorqueUnit.NEWTON_METER, TorqueUnit.POUND_FOOT)
		val backToNewtonMeter = useCase.convert(toPoundFoot, TorqueUnit.POUND_FOOT, TorqueUnit.NEWTON_METER)
		assertEquals(originalValue, backToNewtonMeter, tolerance)
	}

	@Test
	fun testComplexConversion() {
		// Test complex conversion: kgf·m -> N·m -> lbf·ft -> lbf·in
		val kgfM = 1.0
		val toNewtonMeter = useCase.convert(kgfM, TorqueUnit.KILOGRAM_FORCE_METER, TorqueUnit.NEWTON_METER)
		assertEquals(9.80665, toNewtonMeter, tolerance)
		
		val toPoundFoot = useCase.convert(toNewtonMeter, TorqueUnit.NEWTON_METER, TorqueUnit.POUND_FOOT)
		val expectedPoundFoot = 9.80665 / 1.355818
		assertEquals(expectedPoundFoot, toPoundFoot, tolerance)
		
		val toPoundInch = useCase.convert(toPoundFoot, TorqueUnit.POUND_FOOT, TorqueUnit.POUND_INCH)
		assertEquals(12.0, toPoundInch, tolerance)
	}

	@Test
	fun testCoefficientAccuracy() {
		// Test that all coefficients are correct by converting 1 unit to Newton-meter
		val testCases = mapOf(
			TorqueUnit.NEWTON_METER to 1.0,
			TorqueUnit.MILLINEWTON_METER to 0.001,
			TorqueUnit.KILONEWTON_METER to 1000.0,
			TorqueUnit.DECANEWTON_METER to 10.0,
			TorqueUnit.POUND_FOOT to 1.355818,
			TorqueUnit.POUND_INCH to 0.1129848,
			TorqueUnit.OUNCE_INCH to 0.00706155,
			TorqueUnit.KILOGRAM_FORCE_METER to 9.80665,
			TorqueUnit.KILOGRAM_FORCE_CENTIMETER to 0.0980665
		)

		testCases.forEach { (unit, expectedNewtonMeter) ->
			val result = useCase.convert(1.0, unit, TorqueUnit.NEWTON_METER)
			assertEquals(
				"Coefficient for ${unit.name} is incorrect",
				expectedNewtonMeter,
				result,
				tolerance
			)
		}
	}
}

