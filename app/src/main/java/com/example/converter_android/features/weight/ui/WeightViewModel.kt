package com.example.converter_android.features.weight.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.converter_android.core.utils.Constants
import com.example.converter_android.core.utils.InputValidator
import com.example.converter_android.features.weight.data.WeightConverter
import com.example.converter_android.features.weight.data.WeightUnits
import com.example.converter_android.features.weight.domain.models.WeightUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for weight conversion screen.
 * 
 * This ViewModel manages the state and business logic for the weight conversion
 * feature. It follows the MVVM (Model-View-ViewModel) pattern and uses Kotlin
 * Coroutines and StateFlow for reactive state management.
 * 
 * Responsibilities:
 * - Managing UI state (input value, units, result)
 * - Handling user input and unit selection
 * - Performing weight conversions
 * - Formatting and displaying results
 * 
 * State Management:
 * - Uses [StateFlow] to expose UI state to the composable screen
 * - State is immutable and updated through copy operations
 * - All state changes trigger automatic UI updates through Compose's state observation
 * 
 * Conversion Logic:
 * - Automatically recalculates result when input or units change
 * - Handles edge cases (empty input, invalid numbers)
 * - Formats results with appropriate precision
 * 
 * @see WeightUiState
 * @see WeightConverter
 * @see WeightUnit
 */
class WeightViewModel : ViewModel() {
	/**
	 * Converter instance for performing weight conversions.
	 * Initialized once and reused for all conversion operations.
	 */
	private val weightConverter = WeightConverter()

	/**
	 * Internal mutable state flow that holds the current UI state.
	 * This is private to prevent external modification. External access
	 * is provided through the read-only [uiState] property.
	 */
	private val _uiState = MutableStateFlow(WeightUiState())
	
	/**
	 * Public read-only state flow that exposes the current UI state.
	 * The UI layer observes this flow to reactively update when state changes.
	 */
	val uiState: StateFlow<WeightUiState> = _uiState.asStateFlow()

	/**
	 * Initialization block that sets up the default state when the ViewModel is created.
	 * 
	 * This initializes:
	 * - Default "from" unit (Kilogram)
	 * - Default "to" unit (Gram)
	 * - Available units list for dropdown menus
	 */
	init {
		// Initialize with default units
		_uiState.value = WeightUiState(
			fromUnit = WeightUnits.getDefaultFromUnit(),
			toUnit = WeightUnits.getDefaultToUnit(),
			availableUnits = WeightUnits.getAllUnits()
		)
	}

	/**
	 * Updates the input value for weight conversion.
	 * 
	 * This method handles user input in the conversion field. It performs
	 * several important tasks:
	 * 1. Validates and sanitizes the input string
	 * 2. Parses the input string to a numeric value
	 * 3. Handles edge cases like empty strings, single dots, and minus signs
	 * 4. Updates both the raw input string (for display) and numeric value (for calculation)
	 * 5. Triggers automatic recalculation of the conversion result
	 * 
	 * Edge cases handled:
	 * - Empty string: Treated as 0.0
	 * - Single dot ("."): Treated as 0.0 (user is typing a decimal number)
	 * - Single minus ("-"): Treated as 0.0 (user is typing a negative number)
	 * - Minus dot ("-."): Treated as 0.0 (user is typing a negative decimal)
	 * - Invalid input: Parsed as 0.0 if [String.toDoubleOrNull] returns null
	 * 
	 * @param value The new input value as a string. Can contain digits, decimal point, and minus sign.
	 */
	fun updateInputValue(value: String) {
		// Validate and sanitize input
		val sanitizedValue = if (InputValidator.isValidNumber(value)) {
			value
		} else {
			InputValidator.sanitizeInput(value)
		}
		
		// Parse the input string to a numeric value, handling edge cases
		val numericValue = when {
			sanitizedValue.isEmpty() || sanitizedValue == "." || sanitizedValue == "-" || sanitizedValue == "-." -> 0.0
			else -> sanitizedValue.toDoubleOrNull() ?: 0.0
		}
		
		// Check if value is within safe bounds
		val errorMessage = when {
			!InputValidator.isValidFiniteNumber(numericValue) -> null // Will be handled in calculateResult
			!InputValidator.isWithinSafeBounds(numericValue) -> "Число слишком большое или слишком маленькое"
			else -> null
		}
		
		// Update state with both raw input (for display) and numeric value (for calculation)
		_uiState.value = _uiState.value.copy(
			inputValue = sanitizedValue,
			numericValue = numericValue,
			errorMessage = errorMessage
		)
		
		// Trigger automatic recalculation
		calculateResult()
	}

	/**
	 * Updates the source unit for weight conversion.
	 * 
	 * This method is called when the user selects a new "from" unit in the
	 * unit selection dropdown. The conversion result is automatically
	 * recalculated after the unit change.
	 * 
	 * @param unit The weight unit to use as the source unit
	 */
	fun updateFromUnit(unit: WeightUnit) {
		_uiState.value = _uiState.value.copy(fromUnit = unit)
		calculateResult()
	}

	/**
	 * Updates the target unit for weight conversion.
	 * 
	 * This method is called when the user selects a new "to" unit in the
	 * unit selection dropdown. The conversion result is automatically
	 * recalculated after the unit change.
	 * 
	 * @param unit The weight unit to use as the target unit
	 */
	fun updateToUnit(unit: WeightUnit) {
		_uiState.value = _uiState.value.copy(toUnit = unit)
		calculateResult()
	}

	/**
	 * Swaps the source and target units for weight conversion.
	 * 
	 * This method provides a convenient way for users to quickly reverse a conversion
	 * by swapping the "from" and "to" units. This is particularly useful when users
	 * want to convert in the opposite direction without manually selecting new units.
	 * 
	 * The swap operation:
	 * 1. Swaps the "from" and "to" units
	 * 2. Automatically recalculates the conversion result with the swapped units
	 * 
	 * The input value remains unchanged, so the user can immediately see the
	 * converted result in the opposite direction.
	 */
	fun swapUnits() {
		val currentState = _uiState.value
		_uiState.value = currentState.copy(
			fromUnit = currentState.toUnit,
			toUnit = currentState.fromUnit
		)
		calculateResult()
	}

	/**
	 * Calculates the weight conversion result based on the current input and selected units.
	 * 
	 * This private method is called automatically whenever:
	 * - The input value changes
	 * - The source or target unit changes
	 * - Units are swapped
	 * 
	 * The calculation process:
	 * 1. Checks if the input is empty or represents an incomplete number (edge cases)
	 * 2. Validates that the numeric value is finite and within safe bounds
	 * 3. If input is valid and non-zero, performs the conversion using the converter
	 * 4. Validates the result (checks for NaN, Infinity, and safe bounds)
	 * 5. Formats the result to the specified number of decimal places
	 * 6. Removes trailing zeros and decimal points for cleaner display
	 * 7. Updates the state with the formatted result or error message
	 * 
	 * The method uses [viewModelScope] to launch the calculation in a coroutine,
	 * ensuring that the calculation doesn't block the UI thread.
	 * 
	 * Error handling:
	 * - NaN values: Shows error message "Некорректный результат"
	 * - Infinity values: Shows error message "Результат слишком большой"
	 * - Out of bounds: Shows error message "Число слишком большое или слишком маленькое"
	 * 
	 * Result formatting:
	 * - Uses [Constants.RESULT_DECIMAL_PLACES] for precision
	 * - Removes trailing zeros (e.g., "10.000000" becomes "10")
	 * - Removes trailing decimal point (e.g., "10." becomes "10")
	 * - Empty input results in empty string (displayed as "—" in UI)
	 * - Zero value results in "0"
	 */
	private fun calculateResult() {
		viewModelScope.launch {
			val state = _uiState.value
			when {
				// Handle edge cases: empty input or incomplete number entry
				state.inputValue.isEmpty() || 
				state.inputValue == "." || 
				state.inputValue == "-" || 
				state.inputValue == "-." -> {
					_uiState.value = state.copy(result = "", errorMessage = null)
				}
				// Check if value is valid and finite
				!InputValidator.isValidFiniteNumber(state.numericValue) -> {
					_uiState.value = state.copy(
						result = "",
						errorMessage = "Некорректное значение"
					)
				}
				// Check if value is within safe bounds
				!InputValidator.isWithinSafeBounds(state.numericValue) -> {
					_uiState.value = state.copy(
						result = "",
						errorMessage = "Число слишком большое или слишком маленькое"
					)
				}
				// Perform conversion if input is valid and non-zero
				state.numericValue != 0.0 -> {
					val result = weightConverter.convert(
						state.numericValue,
						state.fromUnit,
						state.toUnit
					)
					
					// Validate result
					when {
						result.isNaN() -> {
							_uiState.value = state.copy(
								result = "",
								errorMessage = "Некорректный результат"
							)
						}
						!result.isFinite() -> {
							_uiState.value = state.copy(
								result = "",
								errorMessage = "Результат слишком большой"
							)
						}
						!InputValidator.isWithinSafeBounds(result) -> {
							_uiState.value = state.copy(
								result = "",
								errorMessage = "Результат выходит за допустимые пределы"
							)
						}
						else -> {
							// Format result and remove trailing zeros/decimal point
							_uiState.value = state.copy(
								result = String.format("%.${Constants.RESULT_DECIMAL_PLACES}f", result)
									.trimEnd('0')
									.trimEnd('.'),
								errorMessage = null
							)
						}
					}
				}
				// Handle zero value
				else -> {
					_uiState.value = state.copy(result = "0", errorMessage = null)
				}
			}
		}
	}
}

/**
 * UI state for weight conversion screen.
 * 
 * This data class holds all the state necessary for the weight conversion screen.
 * The state is immutable, and updates are made through copy operations to ensure
 * reactive updates in Compose.
 * 
 * All properties have default values to ensure the state is always valid,
 * even when first initialized.
 * 
 * @property inputValue The raw input value as entered by the user (preserves formatting)
 * @property numericValue The parsed numeric value of the input (used for calculations)
 * @property fromUnit The source weight unit (the "from" unit)
 * @property toUnit The target weight unit (the "to" unit)
 * @property result The formatted result of the weight conversion
 * @property availableUnits List of all available weight units for dropdown menus
 */
data class WeightUiState(
	/**
	 * The raw input value as entered by the user.
	 * Stored as a string to preserve user input exactly as typed,
	 * including incomplete entries like "12." or "-".
	 */
	val inputValue: String = "",
	
	/**
	 * The parsed numeric value of the input.
	 * Used for actual calculations. Defaults to 0.0 if input cannot be parsed.
	 */
	val numericValue: Double = 0.0,
	
	/**
	 * The source weight unit (the "from" unit).
	 */
	val fromUnit: WeightUnit = WeightUnit.KILOGRAM,
	
	/**
	 * The target weight unit (the "to" unit).
	 */
	val toUnit: WeightUnit = WeightUnit.GRAM,
	
	/**
	 * The formatted result of the weight conversion.
	 * Formatted to [Constants.RESULT_DECIMAL_PLACES] decimal places with
	 * trailing zeros and decimal points removed.
	 */
	val result: String = "0",
	
	/**
	 * List of all available weight units.
	 * Used to populate the unit selection dropdown menus.
	 */
	val availableUnits: List<WeightUnit> = emptyList(),
	
	/**
	 * Error message to display to the user if validation or calculation fails.
	 * 
	 * Possible error messages:
	 * - "Некорректное значение" - Input is not a valid number
	 * - "Число слишком большое или слишком маленькое" - Input is out of safe bounds
	 * - "Некорректный результат" - Calculation resulted in NaN
	 * - "Результат слишком большой" - Calculation resulted in Infinity
	 * - "Результат выходит за допустимые пределы" - Result is out of safe bounds
	 * 
	 * `null` indicates no error.
	 */
	val errorMessage: String? = null
)

