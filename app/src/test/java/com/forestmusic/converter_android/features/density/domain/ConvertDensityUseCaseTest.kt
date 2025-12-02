package com.forestmusic.converter_android.features.density.domain

import com.forestmusic.converter_android.features.density.domain.models.DensityUnit
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for density conversion use case.
 * Tests conversion accuracy with tolerance ≤ 0.0001.
 */
class ConvertDensityUseCaseTest {
	private val useCase = ConvertDensityUseCase()
	private val tolerance = 0.0001

	@Test
	fun testKilogramPerCubicMeterToKilogramPerCubicMeter() {
		val result = useCase.convert(100.0, DensityUnit.KILOGRAM_PER_CUBIC_METER, DensityUnit.KILOGRAM_PER_CUBIC_METER)
		assertEquals(100.0, result, tolerance)
	}

	@Test
	fun testGramPerCubicCentimeterToKilogramPerCubicMeter() {
		// 1 g/cm³ = 1000 kg/m³
		val result = useCase.convert(1.0, DensityUnit.GRAM_PER_CUBIC_CENTIMETER, DensityUnit.KILOGRAM_PER_CUBIC_METER)
		assertEquals(1000.0, result, tolerance)
	}

	@Test
	fun testKilogramPerCubicMeterToGramPerCubicCentimeter() {
		// 1000 kg/m³ = 1 g/cm³
		val result = useCase.convert(1000.0, DensityUnit.KILOGRAM_PER_CUBIC_METER, DensityUnit.GRAM_PER_CUBIC_CENTIMETER)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testGramPerLiterToMilligramPerLiter() {
		// 1 g/L = 1000 mg/L
		val result = useCase.convert(1.0, DensityUnit.GRAM_PER_LITER, DensityUnit.MILLIGRAM_PER_LITER)
		assertEquals(1000.0, result, tolerance)
	}

	@Test
	fun testMilligramPerLiterToGramPerLiter() {
		// 1000 mg/L = 1 g/L
		val result = useCase.convert(1000.0, DensityUnit.MILLIGRAM_PER_LITER, DensityUnit.GRAM_PER_LITER)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testPoundPerCubicFootToKilogramPerCubicMeter() {
		// 1 lb/ft³ = 16.0185 kg/m³
		val result = useCase.convert(1.0, DensityUnit.POUND_PER_CUBIC_FOOT, DensityUnit.KILOGRAM_PER_CUBIC_METER)
		assertEquals(16.0185, result, tolerance)
	}

	@Test
	fun testKilogramPerCubicMeterToPoundPerCubicFoot() {
		// 16.0185 kg/m³ = 1 lb/ft³
		val result = useCase.convert(16.0185, DensityUnit.KILOGRAM_PER_CUBIC_METER, DensityUnit.POUND_PER_CUBIC_FOOT)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testSpecificGravityToKilogramPerCubicMeter() {
		// 1 SG = 1000 kg/m³ (density of water)
		val result = useCase.convert(1.0, DensityUnit.SPECIFIC_GRAVITY, DensityUnit.KILOGRAM_PER_CUBIC_METER)
		assertEquals(1000.0, result, tolerance)
	}

	@Test
	fun testKilogramPerCubicMeterToSpecificGravity() {
		// 1000 kg/m³ = 1 SG
		val result = useCase.convert(1000.0, DensityUnit.KILOGRAM_PER_CUBIC_METER, DensityUnit.SPECIFIC_GRAVITY)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testGramPerCubicMeterToKilogramPerCubicMeter() {
		// 1000 g/m³ = 1 kg/m³
		val result = useCase.convert(1000.0, DensityUnit.GRAM_PER_CUBIC_METER, DensityUnit.KILOGRAM_PER_CUBIC_METER)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testKilogramPerCubicMeterToGramPerCubicMeter() {
		// 1 kg/m³ = 1000 g/m³
		val result = useCase.convert(1.0, DensityUnit.KILOGRAM_PER_CUBIC_METER, DensityUnit.GRAM_PER_CUBIC_METER)
		assertEquals(1000.0, result, tolerance)
	}

	@Test
	fun testKilogramPerLiterToKilogramPerCubicMeter() {
		// 1 kg/L = 1000 kg/m³
		val result = useCase.convert(1.0, DensityUnit.KILOGRAM_PER_LITER, DensityUnit.KILOGRAM_PER_CUBIC_METER)
		assertEquals(1000.0, result, tolerance)
	}

	@Test
	fun testKilogramPerCubicMeterToKilogramPerLiter() {
		// 1000 kg/m³ = 1 kg/L
		val result = useCase.convert(1000.0, DensityUnit.KILOGRAM_PER_CUBIC_METER, DensityUnit.KILOGRAM_PER_LITER)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testGramPerLiterToKilogramPerCubicMeter() {
		// 1 g/L = 1 kg/m³
		val result = useCase.convert(1.0, DensityUnit.GRAM_PER_LITER, DensityUnit.KILOGRAM_PER_CUBIC_METER)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testKilogramPerCubicMeterToGramPerLiter() {
		// 1 kg/m³ = 1 g/L
		val result = useCase.convert(1.0, DensityUnit.KILOGRAM_PER_CUBIC_METER, DensityUnit.GRAM_PER_LITER)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testPoundPerGallonToKilogramPerCubicMeter() {
		// 1 lb/gal = 119.8264 kg/m³
		val result = useCase.convert(1.0, DensityUnit.POUND_PER_GALLON, DensityUnit.KILOGRAM_PER_CUBIC_METER)
		assertEquals(119.8264, result, tolerance)
	}

	@Test
	fun testKilogramPerCubicMeterToPoundPerGallon() {
		// 119.8264 kg/m³ = 1 lb/gal
		val result = useCase.convert(119.8264, DensityUnit.KILOGRAM_PER_CUBIC_METER, DensityUnit.POUND_PER_GALLON)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testSpecificGravityToGramPerCubicCentimeter() {
		// 1 SG = 1 g/cm³
		val result = useCase.convert(1.0, DensityUnit.SPECIFIC_GRAVITY, DensityUnit.GRAM_PER_CUBIC_CENTIMETER)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testGramPerCubicCentimeterToSpecificGravity() {
		// 1 g/cm³ = 1 SG
		val result = useCase.convert(1.0, DensityUnit.GRAM_PER_CUBIC_CENTIMETER, DensityUnit.SPECIFIC_GRAVITY)
		assertEquals(1.0, result, tolerance)
	}

	@Test
	fun testConversionAccuracy() {
		// Test round-trip conversion: kg/m³ -> g/cm³ -> kg/m³
		val originalValue = 1000.0
		val toGramPerCm3 = useCase.convert(originalValue, DensityUnit.KILOGRAM_PER_CUBIC_METER, DensityUnit.GRAM_PER_CUBIC_CENTIMETER)
		val backToKgPerM3 = useCase.convert(toGramPerCm3, DensityUnit.GRAM_PER_CUBIC_CENTIMETER, DensityUnit.KILOGRAM_PER_CUBIC_METER)
		assertEquals(originalValue, backToKgPerM3, tolerance)
	}

	@Test
	fun testComplexConversion() {
		// Test complex conversion: g/cm³ -> kg/m³ -> lb/ft³ -> SG
		val gPerCm3Value = 1.0
		val toKgPerM3 = useCase.convert(gPerCm3Value, DensityUnit.GRAM_PER_CUBIC_CENTIMETER, DensityUnit.KILOGRAM_PER_CUBIC_METER)
		assertEquals(1000.0, toKgPerM3, tolerance)
		
		val toLbPerFt3 = useCase.convert(toKgPerM3, DensityUnit.KILOGRAM_PER_CUBIC_METER, DensityUnit.POUND_PER_CUBIC_FOOT)
		assertEquals(62.428, toLbPerFt3, 0.1) // Higher tolerance for complex conversion
		
		val toSG = useCase.convert(toLbPerFt3, DensityUnit.POUND_PER_CUBIC_FOOT, DensityUnit.SPECIFIC_GRAVITY)
		assertEquals(1.0, toSG, 0.01) // Higher tolerance for multiple conversions
	}

	@Test
	fun testCoefficientAccuracy() {
		// Test that all coefficients are correct by converting 1 unit to kg/m³
		val testCases = mapOf(
			DensityUnit.KILOGRAM_PER_CUBIC_METER to 1.0,
			DensityUnit.GRAM_PER_CUBIC_CENTIMETER to 1000.0,
			DensityUnit.GRAM_PER_CUBIC_METER to 0.001,
			DensityUnit.KILOGRAM_PER_LITER to 1000.0,
			DensityUnit.GRAM_PER_LITER to 1.0,
			DensityUnit.MILLIGRAM_PER_LITER to 0.001,
			DensityUnit.POUND_PER_CUBIC_FOOT to 16.0185,
			DensityUnit.POUND_PER_GALLON to 119.8264,
			DensityUnit.SPECIFIC_GRAVITY to 1000.0
		)

		testCases.forEach { (unit, expectedKgPerM3) ->
			val result = useCase.convert(1.0, unit, DensityUnit.KILOGRAM_PER_CUBIC_METER)
			assertEquals(
				"Coefficient for ${unit.name} is incorrect",
				expectedKgPerM3,
				result,
				tolerance
			)
		}
	}
}

