package com.forestmusic.converter_android.features.electricity.domain

import com.forestmusic.converter_android.features.electricity.domain.models.ResistanceUnit
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Unit tests for resistance conversion.
 * Tests Ω ↔ kΩ ↔ MΩ with tolerance ≤ 0.0001.
 */
class ConvertResistanceUseCaseTest {
	private val useCase = ConvertResistanceUseCase()
	private val tolerance = 0.0001

	@Test
	fun testOhmToOhm() {
		assertEquals(100.0, useCase.convert(100.0, ResistanceUnit.OHM, ResistanceUnit.OHM), tolerance)
	}

	@Test
	fun testOhmToKiloohm() {
		// 1000 Ω = 1 kΩ
		assertEquals(1.0, useCase.convert(1000.0, ResistanceUnit.OHM, ResistanceUnit.KILOOHM), tolerance)
	}

	@Test
	fun testKiloohmToOhm() {
		// 1 kΩ = 1000 Ω
		assertEquals(1000.0, useCase.convert(1.0, ResistanceUnit.KILOOHM, ResistanceUnit.OHM), tolerance)
	}

	@Test
	fun testOhmToMegaohm() {
		// 1,000,000 Ω = 1 MΩ
		assertEquals(1.0, useCase.convert(1_000_000.0, ResistanceUnit.OHM, ResistanceUnit.MEGAOHM), tolerance)
	}

	@Test
	fun testMegaohmToOhm() {
		// 1 MΩ = 1,000,000 Ω
		assertEquals(1_000_000.0, useCase.convert(1.0, ResistanceUnit.MEGAOHM, ResistanceUnit.OHM), tolerance)
	}

	@Test
	fun testKiloohmToMegaohm() {
		// 1000 kΩ = 1 MΩ
		assertEquals(1.0, useCase.convert(1000.0, ResistanceUnit.KILOOHM, ResistanceUnit.MEGAOHM), tolerance)
	}

	@Test
	fun testMegaohmToKiloohm() {
		// 1 MΩ = 1000 kΩ
		assertEquals(1000.0, useCase.convert(1.0, ResistanceUnit.MEGAOHM, ResistanceUnit.KILOOHM), tolerance)
	}

	@Test
	fun testRoundTripConversion() {
		val original = 5000.0
		val toKiloohm = useCase.convert(original, ResistanceUnit.OHM, ResistanceUnit.KILOOHM)
		val backToOhm = useCase.convert(toKiloohm, ResistanceUnit.KILOOHM, ResistanceUnit.OHM)
		assertEquals(original, backToOhm, tolerance)
	}
}

