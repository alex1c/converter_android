package com.example.converter_android.core.utils

/**
 * Utility object for validating and sanitizing user input.
 * 
 * This object provides methods to validate numeric input strings and sanitize
 * them to ensure they are safe for parsing and calculation. It helps prevent
 * invalid input from causing errors or unexpected behavior in the application.
 * 
 * Validation rules:
 * - Allows digits (0-9)
 * - Allows single decimal point (.)
 * - Allows single minus sign (-) at the beginning
 * - Prevents multiple decimal points
 * - Prevents minus sign in the middle or end
 * - Prevents non-numeric characters
 * 
 * Usage:
 * ```kotlin
 * if (InputValidator.isValidNumber(input)) {
 *     val sanitized = InputValidator.sanitizeInput(input)
 *     // Process sanitized input
 * }
 * ```
 */
object InputValidator {
	/**
	 * Regular expression pattern for valid numeric input.
	 * 
	 * Pattern breakdown:
	 * - `^-?` - Optional minus sign at the start
	 * - `\\d*` - Zero or more digits
	 * - `\\.?` - Optional decimal point
	 * - `\\d*` - Zero or more digits after decimal point
	 * - `$` - End of string
	 * 
	 * Examples of valid inputs:
	 * - "123" - Integer
	 * - "-123" - Negative integer
	 * - "123.45" - Decimal number
	 * - "-123.45" - Negative decimal
	 * - ".5" - Decimal starting with point
	 * - "-.5" - Negative decimal starting with point
	 * - "0" - Zero
	 * - "" - Empty string (allowed for UX)
	 */
	private val VALID_NUMBER_PATTERN = Regex("^-?\\d*\\.?\\d*$")

	/**
	 * Maximum safe value for Double calculations.
	 * 
	 * Values larger than this may cause overflow or precision issues.
	 * This is approximately 1e15, which is well within Double's range
	 * but prevents extremely large numbers that could cause problems.
	 */
	const val MAX_SAFE_VALUE = 1e15

	/**
	 * Minimum safe value for Double calculations.
	 * 
	 * Values smaller than this may cause underflow or precision issues.
	 */
	const val MIN_SAFE_VALUE = -1e15

	/**
	 * Checks if the input string represents a valid numeric value.
	 * 
	 * This method validates the format of the input string without
	 * parsing it to a number. It checks that the string contains only
	 * valid numeric characters in a valid format.
	 * 
	 * @param input The input string to validate
	 * @return `true` if the input is a valid numeric format, `false` otherwise
	 * 
	 * @example
	 * ```
	 * InputValidator.isValidNumber("123.45") // true
	 * InputValidator.isValidNumber("-123") // true
	 * InputValidator.isValidNumber("abc") // false
	 * InputValidator.isValidNumber("12.34.56") // false
	 * InputValidator.isValidNumber("") // true (empty is allowed for UX)
	 * ```
	 */
	fun isValidNumber(input: String): Boolean {
		// Empty string is allowed for better UX (user is typing)
		if (input.isEmpty()) return true
		
		// Check if input matches valid number pattern
		return VALID_NUMBER_PATTERN.matches(input)
	}

	/**
	 * Sanitizes the input string by removing invalid characters.
	 * 
	 * This method removes all characters that are not digits, decimal point,
	 * or minus sign, ensuring the input is safe for parsing. It preserves
	 * the structure of valid numeric input while removing any invalid characters.
	 * 
	 * Note: This method does not validate the format (e.g., multiple decimal
	 * points), it only removes invalid characters. Use [isValidNumber] for
	 * format validation.
	 * 
	 * @param input The input string to sanitize
	 * @return The sanitized string containing only valid numeric characters
	 * 
	 * @example
	 * ```
	 * InputValidator.sanitizeInput("12a3.45") // "123.45"
	 * InputValidator.sanitizeInput("-12.34") // "-12.34"
	 * InputValidator.sanitizeInput("abc") // ""
	 * ```
	 */
	fun sanitizeInput(input: String): String {
		// Keep only digits, decimal point, and minus sign
		return input.filter { it.isDigit() || it == '.' || it == '-' }
	}

	/**
	 * Validates that a numeric value is within safe bounds.
	 * 
	 * This method checks if a parsed numeric value is within the safe range
	 * for calculations, preventing overflow, underflow, or precision issues.
	 * 
	 * @param value The numeric value to validate
	 * @return `true` if the value is within safe bounds, `false` otherwise
	 * 
	 * @example
	 * ```
	 * InputValidator.isWithinSafeBounds(1000.0) // true
	 * InputValidator.isWithinSafeBounds(1e16) // false (too large)
	 * InputValidator.isWithinSafeBounds(-1e16) // false (too small)
	 * ```
	 */
	fun isWithinSafeBounds(value: Double): Boolean {
		return value >= MIN_SAFE_VALUE && value <= MAX_SAFE_VALUE
	}

	/**
	 * Validates that a numeric value is not NaN or Infinity.
	 * 
	 * This method checks if a value is a valid finite number that can be
	 * safely used in calculations.
	 * 
	 * @param value The numeric value to validate
	 * @return `true` if the value is finite and valid, `false` otherwise
	 * 
	 * @example
	 * ```
	 * InputValidator.isValidFiniteNumber(100.0) // true
	 * InputValidator.isValidFiniteNumber(Double.NaN) // false
	 * InputValidator.isValidFiniteNumber(Double.POSITIVE_INFINITY) // false
	 * ```
	 */
	fun isValidFiniteNumber(value: Double): Boolean {
		return value.isFinite() && !value.isNaN()
	}
}

