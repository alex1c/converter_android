package com.example.converter_android.features.electricity.domain

import com.example.converter_android.features.electricity.domain.models.CurrentUnit
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for current conversion.
 * Tests A ↔ mA ↔ kA with tolerance ≤ 0.0001.
 */
class ConvertCurrentUseCaseTest {
	private val useCase = ConvertCurrentUseCase()
	private val tolerance = 0.0001

	@Test
	fun testAmpereToAmpere() {
		assertEquals(100.0, useCase.convert(100.0, CurrentUnit.AMPERE, CurrentUnit.AMPERE), tolerance)
	}

	@Test
	fun testAmpereToMilliampere() {
		// 1 A = 1000 mA
		assertEquals(1000.0, useCase.convert(1.0, CurrentUnit.AMPERE, CurrentUnit.MILLIAMPERE), tolerance)
	}

	@Test
	fun testMilliampereToAmpere() {
		// 1000 mA = 1 A
		assertEquals(1.0, useCase.convert(1000.0, CurrentUnit.MILLIAMPERE, CurrentUnit.AMPERE), tolerance)
	}

	@Test
	fun testAmpereToKiloampere() {
		// 1000 A = 1 kA
		assertEquals(1.0, useCase.convert(1000.0, CurrentUnit.AMPERE, CurrentUnit.KILOAMPERE), tolerance)
	}

	@Test
	fun testKiloampereToAmpere() {
		// 1 kA = 1000 A
		assertEquals(1000.0, useCase.convert(1.0, CurrentUnit.KILOAMPERE, CurrentUnit.AMPERE), tolerance)
	}

	@Test
	fun testMilliampereToKiloampere() {
		// 1,000,000 mA = 1 kA
		assertEquals(1.0, useCase.convert(1_000_000.0, CurrentUnit.MILLIAMPERE, CurrentUnit.KILOAMPERE), tolerance)
	}

	@Test
	fun testKiloampereToMilliampere() {
		// 1 kA = 1,000,000 mA
		assertEquals(1_000_000.0, useCase.convert(1.0, CurrentUnit.KILOAMPERE, CurrentUnit.MILLIAMPERE), tolerance)
	}

	@Test
	fun testRoundTripConversion() {
		val original = 2.5
		val toMilliampere = useCase.convert(original, CurrentUnit.AMPERE, CurrentUnit.MILLIAMPERE)
		val backToAmpere = useCase.convert(toMilliampere, CurrentUnit.MILLIAMPERE, CurrentUnit.AMPERE)
		assertEquals(original, backToAmpere, tolerance)
	}
}

