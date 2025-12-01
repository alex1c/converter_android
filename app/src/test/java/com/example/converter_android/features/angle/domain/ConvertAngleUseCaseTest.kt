package com.example.converter_android.features.angle.domain

import com.example.converter_android.features.angle.domain.models.AngleUnit
import kotlin.math.PI
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for angle conversion use case.
 * Tests conversion accuracy with tolerance ≤ 0.0001.
 */
class ConvertAngleUseCaseTest {
	private val useCase = ConvertAngleUseCase()
	private val tolerance = 0.0001

	@Test
	fun testDegreeToDegree() {
		val result = useCase.convert(100.0, AngleUnit.DEGREE, AngleUnit.DEGREE)
		assertEquals(100.0, result, tolerance)
	}

	@Test
	fun testDegreesToRadians() {
		// 180 degrees = π radians
		val result = useCase.convert(180.0, AngleUnit.DEGREE, AngleUnit.RADIAN)
		assertEquals(PI, result, tolerance)
	}

	@Test
	fun testRadiansToDegrees() {
		// π radians = 180 degrees
		val result = useCase.convert(PI, AngleUnit.RADIAN, AngleUnit.DEGREE)
		assertEquals(180.0, result, tolerance)
	}

	@Test
	fun testDegreesToGrads() {
		// 90 degrees = 100 grads
		val result = useCase.convert(90.0, AngleUnit.DEGREE, AngleUnit.GRAD)
		assertEquals(100.0, result, tolerance)
	}

	@Test
	fun testGradsToDegrees() {
		// 100 grads = 90 degrees
		val result = useCase.convert(100.0, AngleUnit.GRAD, AngleUnit.DEGREE)
		assertEquals(90.0, result, tolerance)
	}

	@Test
	fun testDegreesToArcMinutes() {
		// 1 degree = 60 arc minutes
		val result = useCase.convert(1.0, AngleUnit.DEGREE, AngleUnit.ARC_MINUTE)
		assertEquals(60.0, result, tolerance)
	}

	@Test
	fun testArcMinutesToDegrees() {
		// 60 arc minutes = 1 degree
		val result = useCase.convert(60.0, AngleUnit.ARC_MINUTE, AngleUnit.DEGREE)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testDegreesToArcSeconds() {
		// 1 degree = 3600 arc seconds
		val result = useCase.convert(1.0, AngleUnit.DEGREE, AngleUnit.ARC_SECOND)
		assertEquals(3600.0, result, tolerance)
	}

	@Test
	fun testArcSecondsToDegrees() {
		// 3600 arc seconds = 1 degree
		val result = useCase.convert(3600.0, AngleUnit.ARC_SECOND, AngleUnit.DEGREE)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testRadiansToTurns() {
		// 2π radians = 1 turn
		val result = useCase.convert(2 * PI, AngleUnit.RADIAN, AngleUnit.TURN)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testTurnsToRadians() {
		// 1 turn = 2π radians
		val result = useCase.convert(1.0, AngleUnit.TURN, AngleUnit.RADIAN)
		assertEquals(2 * PI, result, tolerance)
	}

	@Test
	fun testDegreeToTurn() {
		// 360 degrees = 1 turn
		val result = useCase.convert(360.0, AngleUnit.DEGREE, AngleUnit.TURN)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testTurnToDegree() {
		// 1 turn = 360 degrees
		val result = useCase.convert(1.0, AngleUnit.TURN, AngleUnit.DEGREE)
		assertEquals(360.0, result, tolerance)
	}

	@Test
	fun testArcMinuteToArcSecond() {
		// 1 arc minute = 60 arc seconds
		val result = useCase.convert(1.0, AngleUnit.ARC_MINUTE, AngleUnit.ARC_SECOND)
		assertEquals(60.0, result, tolerance)
	}

	@Test
	fun testArcSecondToArcMinute() {
		// 60 arc seconds = 1 arc minute
		val result = useCase.convert(60.0, AngleUnit.ARC_SECOND, AngleUnit.ARC_MINUTE)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testGradToTurn() {
		// 400 grads = 1 turn
		val result = useCase.convert(400.0, AngleUnit.GRAD, AngleUnit.TURN)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testTurnToGrad() {
		// 1 turn = 400 grads
		val result = useCase.convert(1.0, AngleUnit.TURN, AngleUnit.GRAD)
		assertEquals(400.0, result, tolerance)
	}

	@Test
	fun testConversionAccuracy() {
		// Test round-trip conversion: deg -> rad -> deg
		val originalValue = 90.0
		val toRadian = useCase.convert(originalValue, AngleUnit.DEGREE, AngleUnit.RADIAN)
		val backToDegree = useCase.convert(toRadian, AngleUnit.RADIAN, AngleUnit.DEGREE)
		assertEquals(originalValue, backToDegree, tolerance)
	}

	@Test
	fun testComplexConversion() {
		// Test complex conversion: turn -> rad -> grad -> deg
		val turnValue = 1.0
		val toRadian = useCase.convert(turnValue, AngleUnit.TURN, AngleUnit.RADIAN)
		assertEquals(2 * PI, toRadian, tolerance)
		
		val toGrad = useCase.convert(toRadian, AngleUnit.RADIAN, AngleUnit.GRAD)
		assertEquals(400.0, toGrad, tolerance)
		
		val toDegree = useCase.convert(toGrad, AngleUnit.GRAD, AngleUnit.DEGREE)
		assertEquals(360.0, toDegree, tolerance)
	}

	@Test
	fun testCoefficientAccuracy() {
		// Test that all coefficients are correct by converting 1 unit to degrees
		val testCases = mapOf(
			AngleUnit.DEGREE to 1.0,
			AngleUnit.RADIAN to 180.0 / PI,
			AngleUnit.GRAD to 0.9,
			AngleUnit.ARC_MINUTE to 1.0 / 60.0,
			AngleUnit.ARC_SECOND to 1.0 / 3600.0,
			AngleUnit.TURN to 360.0
		)

		testCases.forEach { (unit, expectedDegrees) ->
			val result = useCase.convert(1.0, unit, AngleUnit.DEGREE)
			assertEquals(
				"Coefficient for ${unit.name} is incorrect",
				expectedDegrees,
				result,
				tolerance
			)
		}
	}

	@Test
	fun testSpecificValues() {
		// Test specific known values
		// 45 degrees = π/4 radians
		val result = useCase.convert(45.0, AngleUnit.DEGREE, AngleUnit.RADIAN)
		assertEquals(PI / 4, result, tolerance)
		
		// 30 degrees = π/6 radians
		val result2 = useCase.convert(30.0, AngleUnit.DEGREE, AngleUnit.RADIAN)
		assertEquals(PI / 6, result2, tolerance)
		
		// 60 degrees = π/3 radians
		val result3 = useCase.convert(60.0, AngleUnit.DEGREE, AngleUnit.RADIAN)
		assertEquals(PI / 3, result3, tolerance)
	}
}

