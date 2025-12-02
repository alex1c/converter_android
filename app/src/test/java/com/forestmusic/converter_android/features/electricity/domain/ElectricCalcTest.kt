package com.forestmusic.converter_android.features.electricity.domain

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for ElectricCalc.
 * Tests Ohm's law and power formulas with tolerance ≤ 0.0001.
 */
class ElectricCalcTest {
	private val tolerance = 0.0001

	@Test
	fun testVoltageCalculation() {
		// V = I × R
		// 2 A × 5 Ω = 10 V
		assertEquals(10.0, ElectricCalc.voltage(2.0, 5.0), tolerance)
		assertEquals(0.0, ElectricCalc.voltage(0.0, 5.0), tolerance)
		assertEquals(0.0, ElectricCalc.voltage(2.0, 0.0), tolerance)
	}

	@Test
	fun testCurrentCalculation() {
		// I = V / R
		// 10 V / 5 Ω = 2 A
		assertEquals(2.0, ElectricCalc.current(10.0, 5.0), tolerance)
		assertEquals(0.5, ElectricCalc.current(5.0, 10.0), tolerance)
	}

	@Test
	fun testResistanceCalculation() {
		// R = V / I
		// 10 V / 2 A = 5 Ω
		assertEquals(5.0, ElectricCalc.resistance(10.0, 2.0), tolerance)
		assertEquals(10.0, ElectricCalc.resistance(20.0, 2.0), tolerance)
	}

	@Test
	fun testPowerVI() {
		// P = V × I
		// 10 V × 2 A = 20 W
		assertEquals(20.0, ElectricCalc.powerVI(10.0, 2.0), tolerance)
		assertEquals(0.0, ElectricCalc.powerVI(0.0, 2.0), tolerance)
		assertEquals(0.0, ElectricCalc.powerVI(10.0, 0.0), tolerance)
	}

	@Test
	fun testPowerIR() {
		// P = I² × R
		// 2 A² × 5 Ω = 4 × 5 = 20 W
		assertEquals(20.0, ElectricCalc.powerIR(2.0, 5.0), tolerance)
		assertEquals(0.0, ElectricCalc.powerIR(0.0, 5.0), tolerance)
		assertEquals(0.0, ElectricCalc.powerIR(2.0, 0.0), tolerance)
	}

	@Test
	fun testPowerVR() {
		// P = V² / R
		// 10 V² / 5 Ω = 100 / 5 = 20 W
		assertEquals(20.0, ElectricCalc.powerVR(10.0, 5.0), tolerance)
		assertEquals(0.0, ElectricCalc.powerVR(0.0, 5.0), tolerance)
	}

	@Test
	fun testOhmsLawConsistency() {
		// Test that V = I × R, I = V / R, R = V / I are consistent
		val v = 12.0
		val i = 2.0
		val r = 6.0

		// V = I × R
		assertEquals(v, ElectricCalc.voltage(i, r), tolerance)
		// I = V / R
		assertEquals(i, ElectricCalc.current(v, r), tolerance)
		// R = V / I
		assertEquals(r, ElectricCalc.resistance(v, i), tolerance)
	}

	@Test
	fun testPowerFormulasConsistency() {
		// Test that all three power formulas give the same result
		val v = 12.0
		val i = 2.0
		val r = 6.0

		val powerVI = ElectricCalc.powerVI(v, i)
		val powerIR = ElectricCalc.powerIR(i, r)
		val powerVR = ElectricCalc.powerVR(v, r)

		assertEquals(24.0, powerVI, tolerance)
		assertEquals(24.0, powerIR, tolerance)
		assertEquals(24.0, powerVR, tolerance)
	}
}

