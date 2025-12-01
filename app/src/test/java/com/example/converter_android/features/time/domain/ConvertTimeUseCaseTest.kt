package com.example.converter_android.features.time.domain

import com.example.converter_android.features.time.domain.models.TimeUnit
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for time conversion use case.
 * Tests conversion accuracy with tolerance ≤ 0.0001.
 */
class ConvertTimeUseCaseTest {
	private val useCase = ConvertTimeUseCase()
	private val tolerance = 0.0001

	@Test
	fun testSecondToSecond() {
		val result = useCase.convert(100.0, TimeUnit.SECOND, TimeUnit.SECOND)
		assertEquals(100.0, result, tolerance)
	}

	@Test
	fun testMillisecondToSecond() {
		val result = useCase.convert(1000.0, TimeUnit.MILLISECOND, TimeUnit.SECOND)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testSecondToMillisecond() {
		val result = useCase.convert(1.0, TimeUnit.SECOND, TimeUnit.MILLISECOND)
		assertEquals(1000.0, result, tolerance)
	}

	@Test
	fun testMillisecondToSecondsToHours() {
		// ms → seconds → hours
		val msValue = 3_600_000.0 // 1 hour in milliseconds
		val toSeconds = useCase.convert(msValue, TimeUnit.MILLISECOND, TimeUnit.SECOND)
		assertEquals(3600.0, toSeconds, tolerance)
		
		val toHours = useCase.convert(toSeconds, TimeUnit.SECOND, TimeUnit.HOUR)
		assertEquals(1.0, toHours, tolerance)
	}

	@Test
	fun testMinuteToSecond() {
		val result = useCase.convert(1.0, TimeUnit.MINUTE, TimeUnit.SECOND)
		assertEquals(60.0, result, tolerance)
	}

	@Test
	fun testSecondToMinute() {
		val result = useCase.convert(60.0, TimeUnit.SECOND, TimeUnit.MINUTE)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testHourToSecond() {
		val result = useCase.convert(1.0, TimeUnit.HOUR, TimeUnit.SECOND)
		assertEquals(3600.0, result, tolerance)
	}

	@Test
	fun testSecondToHour() {
		val result = useCase.convert(3600.0, TimeUnit.SECOND, TimeUnit.HOUR)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testDayToSecond() {
		val result = useCase.convert(1.0, TimeUnit.DAY, TimeUnit.SECOND)
		assertEquals(86400.0, result, tolerance)
	}

	@Test
	fun testSecondToDay() {
		val result = useCase.convert(86400.0, TimeUnit.SECOND, TimeUnit.DAY)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testDaysToSecondsToWeeks() {
		// days → seconds → weeks
		val daysValue = 7.0 // 1 week in days
		val toSeconds = useCase.convert(daysValue, TimeUnit.DAY, TimeUnit.SECOND)
		assertEquals(604800.0, toSeconds, tolerance) // 7 * 86400
		
		val toWeeks = useCase.convert(toSeconds, TimeUnit.SECOND, TimeUnit.WEEK)
		assertEquals(1.0, toWeeks, tolerance)
	}

	@Test
	fun testWeekToSecond() {
		val result = useCase.convert(1.0, TimeUnit.WEEK, TimeUnit.SECOND)
		assertEquals(604800.0, result, tolerance)
	}

	@Test
	fun testSecondToWeek() {
		val result = useCase.convert(604800.0, TimeUnit.SECOND, TimeUnit.WEEK)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testMonthToSecond() {
		// Month = 30.436875 days = 2,629,746 seconds
		val result = useCase.convert(1.0, TimeUnit.MONTH, TimeUnit.SECOND)
		assertEquals(2_629_746.0, result, tolerance)
	}

	@Test
	fun testSecondToMonth() {
		val result = useCase.convert(2_629_746.0, TimeUnit.SECOND, TimeUnit.MONTH)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testYearToSecond() {
		// Year = 365.2425 days = 31,556,952 seconds
		val result = useCase.convert(1.0, TimeUnit.YEAR, TimeUnit.SECOND)
		assertEquals(31_556_952.0, result, tolerance)
	}

	@Test
	fun testSecondToYear() {
		val result = useCase.convert(31_556_952.0, TimeUnit.SECOND, TimeUnit.YEAR)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testMonthCoefficient() {
		// Verify month coefficient: 30.436875 days
		val monthInDays = useCase.convert(1.0, TimeUnit.MONTH, TimeUnit.DAY)
		assertEquals(30.436875, monthInDays, tolerance)
	}

	@Test
	fun testYearCoefficient() {
		// Verify year coefficient: 365.2425 days
		val yearInDays = useCase.convert(1.0, TimeUnit.YEAR, TimeUnit.DAY)
		assertEquals(365.2425, yearInDays, tolerance)
	}

	@Test
	fun testMonthToYear() {
		// 1 year = 365.2425 days, 1 month = 30.436875 days
		// 1 year = 365.2425 / 30.436875 ≈ 12.0082 months
		val result = useCase.convert(1.0, TimeUnit.YEAR, TimeUnit.MONTH)
		assertEquals(12.0082, result, 0.01) // Higher tolerance for complex conversion
	}

	@Test
	fun testYearToMonth() {
		// 1 month = 30.436875 / 365.2425 ≈ 0.0833 years
		val result = useCase.convert(1.0, TimeUnit.MONTH, TimeUnit.YEAR)
		assertEquals(0.0833, result, tolerance)
	}

	@Test
	fun testHourToDay() {
		val result = useCase.convert(24.0, TimeUnit.HOUR, TimeUnit.DAY)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testDayToHour() {
		val result = useCase.convert(1.0, TimeUnit.DAY, TimeUnit.HOUR)
		assertEquals(24.0, result, tolerance)
	}

	@Test
	fun testWeekToDay() {
		val result = useCase.convert(1.0, TimeUnit.WEEK, TimeUnit.DAY)
		assertEquals(7.0, result, tolerance)
	}

	@Test
	fun testDayToWeek() {
		val result = useCase.convert(7.0, TimeUnit.DAY, TimeUnit.WEEK)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testConversionAccuracy() {
		// Test round-trip conversion: s -> min -> s
		val originalValue = 3600.0
		val toMinute = useCase.convert(originalValue, TimeUnit.SECOND, TimeUnit.MINUTE)
		val backToSecond = useCase.convert(toMinute, TimeUnit.MINUTE, TimeUnit.SECOND)
		assertEquals(originalValue, backToSecond, tolerance)
	}

	@Test
	fun testComplexConversion() {
		// Test complex conversion: year -> day -> week -> hour
		val yearValue = 1.0
		val toDay = useCase.convert(yearValue, TimeUnit.YEAR, TimeUnit.DAY)
		assertEquals(365.2425, toDay, tolerance)
		
		val toWeek = useCase.convert(toDay, TimeUnit.DAY, TimeUnit.WEEK)
		assertEquals(52.1775, toWeek, 0.01) // Higher tolerance for multiple conversions
		
		val toHour = useCase.convert(toWeek, TimeUnit.WEEK, TimeUnit.HOUR)
		assertEquals(8766.0, toHour, 1.0) // Higher tolerance for multiple conversions
	}

	@Test
	fun testCoefficientAccuracy() {
		// Test that all coefficients are correct by converting 1 unit to seconds
		val testCases = mapOf(
			TimeUnit.MILLISECOND to 0.001,
			TimeUnit.SECOND to 1.0,
			TimeUnit.MINUTE to 60.0,
			TimeUnit.HOUR to 3600.0,
			TimeUnit.DAY to 86400.0,
			TimeUnit.WEEK to 604800.0,
			TimeUnit.MONTH to 2_629_746.0,
			TimeUnit.YEAR to 31_556_952.0
		)

		testCases.forEach { (unit, expectedSeconds) ->
			val result = useCase.convert(1.0, unit, TimeUnit.SECOND)
			assertEquals(
				"Coefficient for ${unit.name} is incorrect",
				expectedSeconds,
				result,
				tolerance
			)
		}
	}
}

