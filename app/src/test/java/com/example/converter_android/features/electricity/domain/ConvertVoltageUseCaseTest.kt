package com.example.converter_android.features.electricity.domain

import com.example.converter_android.features.electricity.domain.models.VoltageUnit
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for voltage conversion.
 * Tests V ↔ mV ↔ kV with tolerance ≤ 0.0001.
 */
class ConvertVoltageUseCaseTest {
	private val useCase = ConvertVoltageUseCase()
	private val tolerance = 0.0001

	@Test
	fun testVoltToVolt() {
		assertEquals(100.0, useCase.convert(100.0, VoltageUnit.VOLT, VoltageUnit.VOLT), tolerance)
	}

	@Test
	fun testVoltToMillivolt() {
		// 1 V = 1000 mV
		assertEquals(1000.0, useCase.convert(1.0, VoltageUnit.VOLT, VoltageUnit.MILLIVOLT), tolerance)
	}

	@Test
	fun testMillivoltToVolt() {
		// 1000 mV = 1 V
		assertEquals(1.0, useCase.convert(1000.0, VoltageUnit.MILLIVOLT, VoltageUnit.VOLT), tolerance)
	}

	@Test
	fun testVoltToKilovolt() {
		// 1000 V = 1 kV
		assertEquals(1.0, useCase.convert(1000.0, VoltageUnit.VOLT, VoltageUnit.KILOVOLT), tolerance)
	}

	@Test
	fun testKilovoltToVolt() {
		// 1 kV = 1000 V
		assertEquals(1000.0, useCase.convert(1.0, VoltageUnit.KILOVOLT, VoltageUnit.VOLT), tolerance)
	}

	@Test
	fun testMillivoltToKilovolt() {
		// 1,000,000 mV = 1 kV
		assertEquals(1.0, useCase.convert(1_000_000.0, VoltageUnit.MILLIVOLT, VoltageUnit.KILOVOLT), tolerance)
	}

	@Test
	fun testKilovoltToMillivolt() {
		// 1 kV = 1,000,000 mV
		assertEquals(1_000_000.0, useCase.convert(1.0, VoltageUnit.KILOVOLT, VoltageUnit.MILLIVOLT), tolerance)
	}

	@Test
	fun testRoundTripConversion() {
		val original = 12.5
		val toMillivolt = useCase.convert(original, VoltageUnit.VOLT, VoltageUnit.MILLIVOLT)
		val backToVolt = useCase.convert(toMillivolt, VoltageUnit.MILLIVOLT, VoltageUnit.VOLT)
		assertEquals(original, backToVolt, tolerance)
	}
}

