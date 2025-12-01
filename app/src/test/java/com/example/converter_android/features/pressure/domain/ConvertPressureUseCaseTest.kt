package com.example.converter_android.features.pressure.domain

import com.example.converter_android.features.pressure.domain.models.PressureUnit
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for pressure conversion use case.
 * Tests conversion accuracy with tolerance ≤ 0.0001.
 */
class ConvertPressureUseCaseTest {
	private val useCase = ConvertPressureUseCase()
	private val tolerance = 0.0001

	@Test
	fun testPascalToPascal() {
		val result = useCase.convert(100.0, PressureUnit.PASCAL, PressureUnit.PASCAL)
		assertEquals(100.0, result, tolerance)
	}

	@Test
	fun testPascalToKilopascal() {
		val result = useCase.convert(1000.0, PressureUnit.PASCAL, PressureUnit.KILOPASCAL)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testKilopascalToPascal() {
		val result = useCase.convert(1.0, PressureUnit.KILOPASCAL, PressureUnit.PASCAL)
		assertEquals(1000.0, result, tolerance)
	}

	@Test
	fun testPascalToBar() {
		val result = useCase.convert(100000.0, PressureUnit.PASCAL, PressureUnit.BAR)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testBarToPascal() {
		val result = useCase.convert(1.0, PressureUnit.BAR, PressureUnit.PASCAL)
		assertEquals(100000.0, result, tolerance)
	}

	@Test
	fun testAtmosphereStandardToPascal() {
		val result = useCase.convert(1.0, PressureUnit.ATMOSPHERE_STANDARD, PressureUnit.PASCAL)
		assertEquals(101325.0, result, tolerance)
	}

	@Test
	fun testPascalToAtmosphereStandard() {
		val result = useCase.convert(101325.0, PressureUnit.PASCAL, PressureUnit.ATMOSPHERE_STANDARD)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testAtmosphereTechnicalToPascal() {
		val result = useCase.convert(1.0, PressureUnit.ATMOSPHERE_TECHNICAL, PressureUnit.PASCAL)
		assertEquals(98066.5, result, tolerance)
	}

	@Test
	fun testPascalToAtmosphereTechnical() {
		val result = useCase.convert(98066.5, PressureUnit.PASCAL, PressureUnit.ATMOSPHERE_TECHNICAL)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testMillimeterMercuryToPascal() {
		val result = useCase.convert(760.0, PressureUnit.MILLIMETER_MERCURY, PressureUnit.PASCAL)
		// 760 mmHg ≈ 101325 Pa (standard atmosphere)
		assertEquals(101325.0, result, 1.0) // Slightly higher tolerance for this conversion
	}

	@Test
	fun testPascalToMillimeterMercury() {
		val result = useCase.convert(101325.0, PressureUnit.PASCAL, PressureUnit.MILLIMETER_MERCURY)
		// 101325 Pa ≈ 760 mmHg
		assertEquals(760.0, result, 1.0) // Slightly higher tolerance for this conversion
	}

	@Test
	fun testPSIToPascal() {
		val result = useCase.convert(1.0, PressureUnit.PSI, PressureUnit.PASCAL)
		assertEquals(6894.76, result, tolerance)
	}

	@Test
	fun testPascalToPSI() {
		val result = useCase.convert(6894.76, PressureUnit.PASCAL, PressureUnit.PSI)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testInchMercuryToPascal() {
		val result = useCase.convert(1.0, PressureUnit.INCH_MERCURY, PressureUnit.PASCAL)
		assertEquals(3386.39, result, tolerance)
	}

	@Test
	fun testPascalToInchMercury() {
		val result = useCase.convert(3386.39, PressureUnit.PASCAL, PressureUnit.INCH_MERCURY)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testBarToPSI() {
		// 1 bar = 100000 Pa, 1 PSI = 6894.76 Pa
		// 1 bar = 100000 / 6894.76 ≈ 14.5038 PSI
		val result = useCase.convert(1.0, PressureUnit.BAR, PressureUnit.PSI)
		assertEquals(14.5038, result, 0.01) // Higher tolerance for complex conversion
	}

	@Test
	fun testPSIToBar() {
		// 1 PSI = 6894.76 Pa, 1 bar = 100000 Pa
		// 1 PSI = 6894.76 / 100000 ≈ 0.0689476 bar
		val result = useCase.convert(1.0, PressureUnit.PSI, PressureUnit.BAR)
		assertEquals(0.0689476, result, tolerance)
	}

	@Test
	fun testMillibarToPascal() {
		val result = useCase.convert(1000.0, PressureUnit.MILLIBAR, PressureUnit.PASCAL)
		assertEquals(100000.0, result, tolerance)
	}

	@Test
	fun testPascalToMillibar() {
		val result = useCase.convert(100000.0, PressureUnit.PASCAL, PressureUnit.MILLIBAR)
		assertEquals(1000.0, result, tolerance)
	}

	@Test
	fun testMegapascalToPascal() {
		val result = useCase.convert(1.0, PressureUnit.MEGAPASCAL, PressureUnit.PASCAL)
		assertEquals(1000000.0, result, tolerance)
	}

	@Test
	fun testPascalToMegapascal() {
		val result = useCase.convert(1000000.0, PressureUnit.PASCAL, PressureUnit.MEGAPASCAL)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testConversionAccuracy() {
		// Test round-trip conversion: Pa -> kPa -> Pa
		val originalValue = 1000.0
		val toKilopascal = useCase.convert(originalValue, PressureUnit.PASCAL, PressureUnit.KILOPASCAL)
		val backToPascal = useCase.convert(toKilopascal, PressureUnit.KILOPASCAL, PressureUnit.PASCAL)
		assertEquals(originalValue, backToPascal, tolerance)
	}

	@Test
	fun testComplexConversion() {
		// Test complex conversion: atm -> bar -> PSI -> Pa
		val atmValue = 1.0
		val toBar = useCase.convert(atmValue, PressureUnit.ATMOSPHERE_STANDARD, PressureUnit.BAR)
		val toPSI = useCase.convert(toBar, PressureUnit.BAR, PressureUnit.PSI)
		val toPascal = useCase.convert(toPSI, PressureUnit.PSI, PressureUnit.PASCAL)
		// 1 atm = 101325 Pa
		assertEquals(101325.0, toPascal, 1.0) // Slightly higher tolerance for multiple conversions
	}
}

