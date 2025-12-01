package com.example.converter_android.features.electricity.domain

/**
 * Calculator for electrical parameters using Ohm's law and power formulas.
 * 
 * This object provides static methods for calculating electrical quantities
 * based on fundamental electrical laws:
 * 
 * Ohm's Law establishes the relationship between voltage (V), current (I), and resistance (R):
 * - V = I × R (voltage equals current times resistance)
 * - I = V / R (current equals voltage divided by resistance)
 * - R = V / I (resistance equals voltage divided by current)
 * 
 * Power formulas calculate electrical power (P) in watts:
 * - P = V × I (power equals voltage times current)
 * - P = I² × R (power equals current squared times resistance)
 * - P = V² / R (power equals voltage squared divided by resistance)
 * 
 * All calculations use SI base units:
 * - Voltage: Volts (V)
 * - Current: Amperes (A)
 * - Resistance: Ohms (Ω)
 * - Power: Watts (W)
 */
object ElectricCalc {
	/**
	 * Calculates voltage using Ohm's law: V = I × R
	 * 
	 * This method implements the fundamental relationship where voltage is directly
	 * proportional to both current and resistance. When current flows through a
	 * conductor with resistance, a voltage drop occurs across it.
	 *
	 * @param i Current in amperes (A). Must be a positive value for meaningful results.
	 * @param r Resistance in ohms (Ω). Must be a positive value for meaningful results.
	 * @return Voltage in volts (V). Result is always positive when inputs are positive.
	 * 
	 * @example
	 * // Calculate voltage for 2A current through 5Ω resistance
	 * val voltage = ElectricCalc.voltage(2.0, 5.0) // Returns 10.0 V
	 */
	fun voltage(i: Double, r: Double): Double = i * r

	/**
	 * Calculates current using Ohm's law: I = V / R
	 * 
	 * This method determines the current flowing through a circuit when voltage
	 * and resistance are known. Current is inversely proportional to resistance
	 * and directly proportional to voltage.
	 *
	 * @param v Voltage in volts (V). Must be a positive value for meaningful results.
	 * @param r Resistance in ohms (Ω). Must be a positive, non-zero value to avoid division by zero.
	 * @return Current in amperes (A). Result is always positive when inputs are positive.
	 * 
	 * @example
	 * // Calculate current for 12V voltage across 6Ω resistance
	 * val current = ElectricCalc.current(12.0, 6.0) // Returns 2.0 A
	 */
	fun current(v: Double, r: Double): Double = v / r

	/**
	 * Calculates resistance using Ohm's law: R = V / I
	 * 
	 * This method determines the resistance of a circuit element when voltage
	 * and current are known. Resistance is directly proportional to voltage
	 * and inversely proportional to current.
	 *
	 * @param v Voltage in volts (V). Must be a positive value for meaningful results.
	 * @param i Current in amperes (A). Must be a positive, non-zero value to avoid division by zero.
	 * @return Resistance in ohms (Ω). Result is always positive when inputs are positive.
	 * 
	 * @example
	 * // Calculate resistance for 24V voltage with 3A current
	 * val resistance = ElectricCalc.resistance(24.0, 3.0) // Returns 8.0 Ω
	 */
	fun resistance(v: Double, i: Double): Double = v / i

	/**
	 * Calculates power using voltage and current: P = V × I
	 * 
	 * This is the most fundamental power formula, directly multiplying voltage
	 * and current. It represents the rate at which electrical energy is consumed
	 * or produced in a circuit.
	 *
	 * @param v Voltage in volts (V). Must be a positive value for meaningful results.
	 * @param i Current in amperes (A). Must be a positive value for meaningful results.
	 * @return Power in watts (W). Result is always positive when inputs are positive.
	 * 
	 * @example
	 * // Calculate power for 12V voltage and 2A current
	 * val power = ElectricCalc.powerVI(12.0, 2.0) // Returns 24.0 W
	 */
	fun powerVI(v: Double, i: Double): Double = v * i

	/**
	 * Calculates power using current and resistance: P = I² × R
	 * 
	 * This formula is derived from the basic power formula (P = V × I) by substituting
	 * V = I × R from Ohm's law. It's particularly useful when voltage is unknown
	 * but current and resistance are known. The power is proportional to the square
	 * of the current, making it sensitive to current changes.
	 *
	 * @param i Current in amperes (A). Must be a positive value for meaningful results.
	 * @param r Resistance in ohms (Ω). Must be a positive value for meaningful results.
	 * @return Power in watts (W). Result is always positive when inputs are positive.
	 * 
	 * @example
	 * // Calculate power for 2A current through 6Ω resistance
	 * val power = ElectricCalc.powerIR(2.0, 6.0) // Returns 24.0 W
	 */
	fun powerIR(i: Double, r: Double): Double = i * i * r

	/**
	 * Calculates power using voltage and resistance: P = V² / R
	 * 
	 * This formula is derived from the basic power formula (P = V × I) by substituting
	 * I = V / R from Ohm's law. It's particularly useful when current is unknown
	 * but voltage and resistance are known. The power is proportional to the square
	 * of the voltage, making it sensitive to voltage changes.
	 *
	 * @param v Voltage in volts (V). Must be a positive value for meaningful results.
	 * @param r Resistance in ohms (Ω). Must be a positive, non-zero value to avoid division by zero.
	 * @return Power in watts (W). Result is always positive when inputs are positive.
	 * 
	 * @example
	 * // Calculate power for 12V voltage across 6Ω resistance
	 * val power = ElectricCalc.powerVR(12.0, 6.0) // Returns 24.0 W
	 */
	fun powerVR(v: Double, r: Double): Double = v * v / r
}

