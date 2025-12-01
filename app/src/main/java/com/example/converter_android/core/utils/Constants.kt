package com.example.converter_android.core.utils

/**
 * Application-wide constants.
 * 
 * This object contains all constants used throughout the application.
 * Centralizing constants in a single location makes it easier to maintain
 * and update values that affect multiple parts of the application.
 * 
 * Constants are defined as compile-time constants using the `const val` keyword,
 * which means they are inlined at compile time and don't require object instantiation.
 * 
 * Usage:
 * These constants are used across the application for:
 * - UI formatting (decimal places, input validation)
 * - Animation timing
 * - Any other shared configuration values
 * 
 * When adding new constants:
 * - Use descriptive names that clearly indicate the constant's purpose
 * - Group related constants together
 * - Add JSDoc comments explaining the constant's purpose and usage
 * - Consider if the constant should be configurable (e.g., through user settings)
 */
object Constants {
	/**
	 * Number of decimal places to display in conversion results.
	 * 
	 * This constant controls the precision of displayed results across all
	 * conversion screens. Results are formatted to this many decimal places,
	 * and trailing zeros are typically removed for cleaner display.
	 * 
	 * Value: 6 decimal places
	 * 
	 * Rationale:
	 * - Provides sufficient precision for most use cases
	 * - Balances accuracy with readability
	 * - Prevents extremely long decimal representations
	 * 
	 * Usage:
	 * ```kotlin
	 * String.format("%.${Constants.RESULT_DECIMAL_PLACES}f", result)
	 * ```
	 * 
	 * Note: The actual displayed precision may be less if trailing zeros
	 * are removed during formatting.
	 */
	const val RESULT_DECIMAL_PLACES = 6

	/**
	 * Maximum length for user input in text fields.
	 * 
	 * This constant limits the maximum number of characters that can be entered
	 * in input fields to prevent extremely long numbers that could cause:
	 * - Performance issues during parsing
	 * - UI layout problems
	 * - Potential overflow or precision issues
	 * 
	 * Value: 15 characters
	 * 
	 * Rationale:
	 * - Allows for very large numbers (e.g., 999,999,999,999,999)
	 * - Prevents input of numbers that exceed Double precision
	 * - Maintains good UX by preventing excessive typing
	 * 
	 * Usage:
	 * ```kotlin
	 * if (newValue.length <= Constants.MAX_INPUT_LENGTH) {
	 *     viewModel.updateInputValue(newValue)
	 * }
	 * ```
	 * 
	 * Note: This limit applies to the total character count, including
	 * digits, decimal point, and minus sign.
	 */
	const val MAX_INPUT_LENGTH = 15

	/**
	 * Duration for UI animations in milliseconds.
	 * 
	 * This constant defines the standard animation duration used throughout
	 * the application for transitions, fades, and other animated effects.
	 * 
	 * Value: 300 milliseconds
	 * 
	 * Rationale:
	 * - Fast enough to feel responsive
	 * - Slow enough to be perceived and provide visual feedback
	 * - Matches Material Design guidelines for standard transitions
	 * 
	 * Usage:
	 * ```kotlin
	 * fadeIn(animationSpec = tween(Constants.ANIMATION_DURATION))
	 * ```
	 * 
	 * Note: This is a standard duration. Some animations may use different
	 * durations for specific effects (e.g., longer for complex transitions).
	 */
	const val ANIMATION_DURATION = 300
}

