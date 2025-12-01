package com.example.converter_android.features.electricity.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.converter_android.core.utils.Constants
import com.example.converter_android.features.electricity.data.CurrentConverter
import com.example.converter_android.features.electricity.data.CurrentUnits
import com.example.converter_android.features.electricity.data.ResistanceConverter
import com.example.converter_android.features.electricity.data.ResistanceUnits
import com.example.converter_android.features.electricity.data.VoltageConverter
import com.example.converter_android.features.electricity.data.VoltageUnits
import com.example.converter_android.features.electricity.domain.ElectricCalc
import com.example.converter_android.features.electricity.domain.models.CurrentUnit
import com.example.converter_android.features.electricity.domain.models.ResistanceUnit
import com.example.converter_android.features.electricity.domain.models.VoltageUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for electricity screen.
 * 
 * This ViewModel manages the state and business logic for the electricity feature,
 * which includes two main functionalities:
 * 1. Unit conversion: Converting between different units of voltage, current, and resistance
 * 2. Calculators: Calculating electrical parameters using Ohm's law and power formulas
 * 
 * The ViewModel follows the MVVM (Model-View-ViewModel) pattern and uses Kotlin
 * Coroutines and StateFlow for reactive state management. It maintains separate
 * state for conversion and calculator operations, allowing users to switch between
 * tabs without losing their input.
 * 
 * State Management:
 * - Uses [StateFlow] to expose UI state to the composable screen
 * - State is immutable and updated through copy operations
 * - All state changes trigger automatic UI updates through Compose's state observation
 * 
 * Conversion Features:
 * - Supports three quantity types: Voltage, Current, and Resistance
 * - Each quantity type has its own set of units and conversion logic
 * - Automatic recalculation when input value or units change
 * - Unit swapping functionality for quick conversion reversal
 * 
 * Calculator Features:
 * - Six different calculators for electrical calculations:
 *   - Voltage calculation (V = I × R)
 *   - Current calculation (I = V / R)
 *   - Resistance calculation (R = V / I)
 *   - Power calculation (P = V × I)
 *   - Power calculation (P = I² × R)
 *   - Power calculation (P = V² / R)
 * - Two input fields per calculator with appropriate labels
 * - Result display with proper unit indication
 * 
 * @see ElectricityUiState
 * @see QuantityType
 * @see CalculatorType
 */
class ElectricityViewModel : ViewModel() {
	/**
	 * Converter instances for each quantity type.
	 * These are initialized once and reused for all conversion operations.
	 */
	private val voltageConverter = VoltageConverter()
	private val currentConverter = CurrentConverter()
	private val resistanceConverter = ResistanceConverter()

	/**
	 * Internal mutable state flow that holds the current UI state.
	 * This is private to prevent external modification. External access
	 * is provided through the read-only [uiState] property.
	 */
	private val _uiState = MutableStateFlow(ElectricityUiState())
	
	/**
	 * Public read-only state flow that exposes the current UI state.
	 * The UI layer observes this flow to reactively update when state changes.
	 * 
	 * @see ElectricityUiState
	 */
	val uiState: StateFlow<ElectricityUiState> = _uiState.asStateFlow()

	/**
	 * Initialization block that sets up the default state when the ViewModel is created.
	 * 
	 * This initializes:
	 * - Default quantity type (Voltage)
	 * - Default units for each quantity type (from and to units)
	 * - Available units list for each quantity type
	 * 
	 * The default selections are chosen to provide a good initial user experience,
	 * with commonly used units that demonstrate the conversion functionality.
	 */
	init {
		// Initialize with default units
		_uiState.value = ElectricityUiState(
			selectedQuantityType = QuantityType.VOLTAGE,
			voltageFromUnit = VoltageUnits.getDefaultFromUnit(),
			voltageToUnit = VoltageUnits.getDefaultToUnit(),
			voltageAvailableUnits = VoltageUnits.getAllUnits(),
			currentFromUnit = CurrentUnits.getDefaultFromUnit(),
			currentToUnit = CurrentUnits.getDefaultToUnit(),
			currentAvailableUnits = CurrentUnits.getAllUnits(),
			resistanceFromUnit = ResistanceUnits.getDefaultFromUnit(),
			resistanceToUnit = ResistanceUnits.getDefaultToUnit(),
			resistanceAvailableUnits = ResistanceUnits.getAllUnits()
		)
	}

	// ==================== Conversion Methods ====================
	
	/**
	 * Updates the input value for unit conversion.
	 * 
	 * This method handles user input in the conversion field. It performs
	 * several important tasks:
	 * 1. Validates and parses the input string to a numeric value
	 * 2. Handles edge cases like empty strings, single dots, and minus signs
	 * 3. Updates both the raw input string (for display) and numeric value (for calculation)
	 * 4. Triggers automatic recalculation of the conversion result
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
		// Parse the input string to a numeric value, handling edge cases
		val numericValue = when {
			value.isEmpty() || value == "." || value == "-" || value == "-." -> 0.0
			else -> value.toDoubleOrNull() ?: 0.0
		}
		
		// Update state with both raw input (for display) and numeric value (for calculation)
		_uiState.value = _uiState.value.copy(
			inputValue = value,
			numericValue = numericValue
		)
		
		// Trigger automatic recalculation
		calculateConversionResult()
	}

	/**
	 * Updates the selected tab (Conversion or Calculators).
	 * 
	 * The electricity screen has two tabs:
	 * - Tab 0: Conversion tab for unit conversion
	 * - Tab 1: Calculators tab for electrical calculations
	 * 
	 * @param tabIndex The index of the tab to select (0 or 1)
	 */
	fun updateTab(tabIndex: Int) {
		_uiState.value = _uiState.value.copy(selectedTab = tabIndex)
	}

	/**
	 * Updates the selected quantity type for conversion.
	 * 
	 * The conversion tab supports three quantity types:
	 * - VOLTAGE: For converting voltage units (V, mV, kV)
	 * - CURRENT: For converting current units (A, mA, kA)
	 * - RESISTANCE: For converting resistance units (Ω, kΩ, MΩ)
	 * 
	 * When the quantity type changes, the conversion result is automatically
	 * recalculated using the new quantity type's units.
	 * 
	 * @param type The quantity type to select
	 */
	fun updateQuantityType(type: QuantityType) {
		_uiState.value = _uiState.value.copy(selectedQuantityType = type)
		calculateConversionResult()
	}

	/**
	 * Updates the source unit for voltage conversion.
	 * 
	 * This method is called when the user selects a new "from" unit in the
	 * voltage conversion dropdown. The conversion result is automatically
	 * recalculated after the unit change.
	 * 
	 * @param unit The voltage unit to use as the source unit
	 */
	fun updateVoltageFromUnit(unit: VoltageUnit) {
		_uiState.value = _uiState.value.copy(voltageFromUnit = unit)
		calculateConversionResult()
	}

	/**
	 * Updates the target unit for voltage conversion.
	 * 
	 * This method is called when the user selects a new "to" unit in the
	 * voltage conversion dropdown. The conversion result is automatically
	 * recalculated after the unit change.
	 * 
	 * @param unit The voltage unit to use as the target unit
	 */
	fun updateVoltageToUnit(unit: VoltageUnit) {
		_uiState.value = _uiState.value.copy(voltageToUnit = unit)
		calculateConversionResult()
	}

	/**
	 * Updates the source unit for current conversion.
	 * 
	 * This method is called when the user selects a new "from" unit in the
	 * current conversion dropdown. The conversion result is automatically
	 * recalculated after the unit change.
	 * 
	 * @param unit The current unit to use as the source unit
	 */
	fun updateCurrentFromUnit(unit: CurrentUnit) {
		_uiState.value = _uiState.value.copy(currentFromUnit = unit)
		calculateConversionResult()
	}

	/**
	 * Updates the target unit for current conversion.
	 * 
	 * This method is called when the user selects a new "to" unit in the
	 * current conversion dropdown. The conversion result is automatically
	 * recalculated after the unit change.
	 * 
	 * @param unit The current unit to use as the target unit
	 */
	fun updateCurrentToUnit(unit: CurrentUnit) {
		_uiState.value = _uiState.value.copy(currentToUnit = unit)
		calculateConversionResult()
	}

	/**
	 * Updates the source unit for resistance conversion.
	 * 
	 * This method is called when the user selects a new "from" unit in the
	 * resistance conversion dropdown. The conversion result is automatically
	 * recalculated after the unit change.
	 * 
	 * @param unit The resistance unit to use as the source unit
	 */
	fun updateResistanceFromUnit(unit: ResistanceUnit) {
		_uiState.value = _uiState.value.copy(resistanceFromUnit = unit)
		calculateConversionResult()
	}

	/**
	 * Updates the target unit for resistance conversion.
	 * 
	 * This method is called when the user selects a new "to" unit in the
	 * resistance conversion dropdown. The conversion result is automatically
	 * recalculated after the unit change.
	 * 
	 * @param unit The resistance unit to use as the target unit
	 */
	fun updateResistanceToUnit(unit: ResistanceUnit) {
		_uiState.value = _uiState.value.copy(resistanceToUnit = unit)
		calculateConversionResult()
	}

	/**
	 * Swaps the source and target units for the currently selected quantity type.
	 * 
	 * This method provides a convenient way for users to quickly reverse a conversion
	 * by swapping the "from" and "to" units. This is particularly useful when users
	 * want to convert in the opposite direction without manually selecting new units.
	 * 
	 * The swap operation:
	 * 1. Determines the current quantity type (Voltage, Current, or Resistance)
	 * 2. Swaps the "from" and "to" units for that quantity type
	 * 3. Automatically recalculates the conversion result with the swapped units
	 * 
	 * The input value remains unchanged, so the user can immediately see the
	 * converted result in the opposite direction.
	 */
	fun swapUnits() {
		val state = _uiState.value
		when (state.selectedQuantityType) {
			QuantityType.VOLTAGE -> {
				_uiState.value = state.copy(
					voltageFromUnit = state.voltageToUnit,
					voltageToUnit = state.voltageFromUnit
				)
			}
			QuantityType.CURRENT -> {
				_uiState.value = state.copy(
					currentFromUnit = state.currentToUnit,
					currentToUnit = state.currentFromUnit
				)
			}
			QuantityType.RESISTANCE -> {
				_uiState.value = state.copy(
					resistanceFromUnit = state.resistanceToUnit,
					resistanceToUnit = state.resistanceFromUnit
				)
			}
		}
		calculateConversionResult()
	}

	/**
	 * Calculates the conversion result based on the current input and selected units.
	 * 
	 * This private method is called automatically whenever:
	 * - The input value changes
	 * - The quantity type changes
	 * - The source or target unit changes
	 * - Units are swapped
	 * 
	 * The calculation process:
	 * 1. Checks if the input is empty or represents an incomplete number (edge cases)
	 * 2. If input is valid and non-zero, performs the conversion using the appropriate converter
	 * 3. Formats the result to the specified number of decimal places
	 * 4. Removes trailing zeros and decimal points for cleaner display
	 * 5. Updates the state with the formatted result
	 * 
	 * The method uses [viewModelScope] to launch the calculation in a coroutine,
	 * ensuring that the calculation doesn't block the UI thread.
	 * 
	 * Result formatting:
	 * - Uses [Constants.RESULT_DECIMAL_PLACES] for precision
	 * - Removes trailing zeros (e.g., "10.000000" becomes "10")
	 * - Removes trailing decimal point (e.g., "10." becomes "10")
	 * - Empty input results in empty string (displayed as "—" in UI)
	 * - Zero value results in "0"
	 */
	private fun calculateConversionResult() {
		viewModelScope.launch {
			val state = _uiState.value
			when {
				// Handle edge cases: empty input or incomplete number entry
				state.inputValue.isEmpty() ||
				state.inputValue == "." ||
				state.inputValue == "-" ||
				state.inputValue == "-." -> {
					_uiState.value = state.copy(conversionResult = "")
				}
				// Perform conversion if input is valid and non-zero
				state.numericValue != 0.0 -> {
					// Select the appropriate converter based on quantity type
					val result = when (state.selectedQuantityType) {
						QuantityType.VOLTAGE -> voltageConverter.convert(
							state.numericValue,
							state.voltageFromUnit,
							state.voltageToUnit
						)
						QuantityType.CURRENT -> currentConverter.convert(
							state.numericValue,
							state.currentFromUnit,
							state.currentToUnit
						)
						QuantityType.RESISTANCE -> resistanceConverter.convert(
							state.numericValue,
							state.resistanceFromUnit,
							state.resistanceToUnit
						)
					}
					// Format result and remove trailing zeros/decimal point
					_uiState.value = state.copy(
						conversionResult = String.format("%.${Constants.RESULT_DECIMAL_PLACES}f", result)
							.trimEnd('0')
							.trimEnd('.')
					)
				}
				// Handle zero value
				else -> {
					_uiState.value = state.copy(conversionResult = "0")
				}
			}
		}
	}

	// ==================== Calculator Methods ====================
	
	/**
	 * Updates the selected calculator type.
	 * 
	 * This method is called when the user selects a different calculator
	 * from the list. When the calculator type changes, the input fields
	 * and result are cleared to provide a clean slate for the new calculation.
	 * 
	 * Available calculators:
	 * - VOLTAGE: Calculate voltage using V = I × R
	 * - CURRENT: Calculate current using I = V / R
	 * - RESISTANCE: Calculate resistance using R = V / I
	 * - POWER_VI: Calculate power using P = V × I
	 * - POWER_IR: Calculate power using P = I² × R
	 * - POWER_VR: Calculate power using P = V² / R
	 * 
	 * @param type The calculator type to select
	 */
	fun updateCalculatorType(type: CalculatorType) {
		_uiState.value = _uiState.value.copy(
			selectedCalculator = type,
			calculatorInput1 = "",
			calculatorInput2 = "",
			calculatorResult = ""
		)
	}

	/**
	 * Updates the first input field value for the calculator.
	 * 
	 * This method is called when the user types in the first input field.
	 * The value is stored as a string to preserve user input exactly as typed,
	 * including incomplete entries (e.g., "12." while typing "12.5").
	 * 
	 * The calculation is not performed automatically; the user must click
	 * the "Calculate" button to trigger the calculation.
	 * 
	 * @param value The new input value as a string
	 */
	fun updateCalculatorInput1(value: String) {
		_uiState.value = _uiState.value.copy(calculatorInput1 = value)
	}

	/**
	 * Updates the second input field value for the calculator.
	 * 
	 * This method is called when the user types in the second input field.
	 * The value is stored as a string to preserve user input exactly as typed,
	 * including incomplete entries (e.g., "12." while typing "12.5").
	 * 
	 * The calculation is not performed automatically; the user must click
	 * the "Calculate" button to trigger the calculation.
	 * 
	 * @param value The new input value as a string
	 */
	fun updateCalculatorInput2(value: String) {
		_uiState.value = _uiState.value.copy(calculatorInput2 = value)
	}

	/**
	 * Performs the calculation based on the selected calculator type and input values.
	 * 
	 * This method:
	 * 1. Parses both input strings to numeric values
	 * 2. Validates that both inputs are non-zero (required for meaningful calculations)
	 * 3. Calls the appropriate calculation method from [ElectricCalc] based on calculator type
	 * 4. Formats the result and updates the state
	 * 
	 * Input validation:
	 * - If either input is 0.0 or cannot be parsed, the result is cleared
	 * - This prevents division by zero errors and invalid calculations
	 * 
	 * Result formatting:
	 * - Uses [Constants.RESULT_DECIMAL_PLACES] for precision
	 * - Removes trailing zeros and decimal points for cleaner display
	 * 
	 * Calculator parameter mapping:
	 * - VOLTAGE: input1 = Current (I), input2 = Resistance (R)
	 * - CURRENT: input1 = Voltage (V), input2 = Resistance (R)
	 * - RESISTANCE: input1 = Voltage (V), input2 = Current (I)
	 * - POWER_VI: input1 = Voltage (V), input2 = Current (I)
	 * - POWER_IR: input1 = Current (I), input2 = Resistance (R)
	 * - POWER_VR: input1 = Voltage (V), input2 = Resistance (R)
	 */
	fun calculate() {
		val state = _uiState.value
		
		// Parse input strings to numeric values, defaulting to 0.0 if parsing fails
		val input1 = state.calculatorInput1.toDoubleOrNull() ?: 0.0
		val input2 = state.calculatorInput2.toDoubleOrNull() ?: 0.0

		// Validate inputs: both must be non-zero for meaningful calculations
		if (input1 == 0.0 || input2 == 0.0) {
			_uiState.value = state.copy(calculatorResult = "")
			return
		}

		// Perform calculation based on selected calculator type
		val result = when (state.selectedCalculator) {
			CalculatorType.VOLTAGE -> ElectricCalc.voltage(input1, input2) // I, R
			CalculatorType.CURRENT -> ElectricCalc.current(input1, input2) // V, R
			CalculatorType.RESISTANCE -> ElectricCalc.resistance(input1, input2) // V, I
			CalculatorType.POWER_VI -> ElectricCalc.powerVI(input1, input2) // V, I
			CalculatorType.POWER_IR -> ElectricCalc.powerIR(input1, input2) // I, R
			CalculatorType.POWER_VR -> ElectricCalc.powerVR(input1, input2) // V, R
		}

		// Format result and remove trailing zeros/decimal point
		_uiState.value = state.copy(
			calculatorResult = String.format("%.${Constants.RESULT_DECIMAL_PLACES}f", result)
				.trimEnd('0')
				.trimEnd('.')
		)
	}
}

/**
 * UI state for electricity screen.
 * 
 * This data class holds all the state necessary for the electricity screen,
 * including both conversion and calculator functionality. The state is immutable,
 * and updates are made through copy operations to ensure reactive updates in Compose.
 * 
 * The state is divided into several sections:
 * - Tab selection: Which tab is currently active
 * - Conversion state: Input, output, and unit selections for conversion
 * - Unit selections: Separate state for each quantity type (voltage, current, resistance)
 * - Calculator state: Input fields, calculator type, and result
 * 
 * All properties have default values to ensure the state is always valid,
 * even when first initialized.
 * 
 * @property selectedTab The currently selected tab index (0 = Conversion, 1 = Calculators)
 * @property selectedQuantityType The quantity type selected for conversion (Voltage, Current, or Resistance)
 * @property inputValue The raw input string as entered by the user (preserves formatting)
 * @property numericValue The parsed numeric value of the input (used for calculations)
 * @property conversionResult The formatted result of the unit conversion
 * @property voltageFromUnit The source unit for voltage conversion
 * @property voltageToUnit The target unit for voltage conversion
 * @property voltageAvailableUnits List of all available voltage units for dropdown
 * @property currentFromUnit The source unit for current conversion
 * @property currentToUnit The target unit for current conversion
 * @property currentAvailableUnits List of all available current units for dropdown
 * @property resistanceFromUnit The source unit for resistance conversion
 * @property resistanceToUnit The target unit for resistance conversion
 * @property resistanceAvailableUnits List of all available resistance units for dropdown
 * @property selectedCalculator The currently selected calculator type
 * @property calculatorInput1 The first input field value for the calculator
 * @property calculatorInput2 The second input field value for the calculator
 * @property calculatorResult The formatted result of the calculator operation
 */
data class ElectricityUiState(
	/**
	 * The currently selected tab index.
	 * - 0: Conversion tab for unit conversion
	 * - 1: Calculators tab for electrical calculations
	 */
	val selectedTab: Int = 0, // 0 = Conversion, 1 = Calculators

	// ==================== Conversion State ====================
	
	/**
	 * The quantity type selected for conversion.
	 * Determines which set of units (voltage, current, or resistance) is used.
	 */
	val selectedQuantityType: QuantityType = QuantityType.VOLTAGE,
	
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
	 * The formatted result of the unit conversion.
	 * Formatted to [Constants.RESULT_DECIMAL_PLACES] decimal places with
	 * trailing zeros and decimal points removed.
	 */
	val conversionResult: String = "0",

	// ==================== Voltage Units ====================
	
	/**
	 * The source unit for voltage conversion (the "from" unit).
	 */
	val voltageFromUnit: VoltageUnit = VoltageUnit.VOLT,
	
	/**
	 * The target unit for voltage conversion (the "to" unit).
	 */
	val voltageToUnit: VoltageUnit = VoltageUnit.MILLIVOLT,
	
	/**
	 * List of all available voltage units.
	 * Used to populate the unit selection dropdown menus.
	 */
	val voltageAvailableUnits: List<VoltageUnit> = emptyList(),

	// ==================== Current Units ====================
	
	/**
	 * The source unit for current conversion (the "from" unit).
	 */
	val currentFromUnit: CurrentUnit = CurrentUnit.AMPERE,
	
	/**
	 * The target unit for current conversion (the "to" unit).
	 */
	val currentToUnit: CurrentUnit = CurrentUnit.MILLIAMPERE,
	
	/**
	 * List of all available current units.
	 * Used to populate the unit selection dropdown menus.
	 */
	val currentAvailableUnits: List<CurrentUnit> = emptyList(),

	// ==================== Resistance Units ====================
	
	/**
	 * The source unit for resistance conversion (the "from" unit).
	 */
	val resistanceFromUnit: ResistanceUnit = ResistanceUnit.OHM,
	
	/**
	 * The target unit for resistance conversion (the "to" unit).
	 */
	val resistanceToUnit: ResistanceUnit = ResistanceUnit.KILOOHM,
	
	/**
	 * List of all available resistance units.
	 * Used to populate the unit selection dropdown menus.
	 */
	val resistanceAvailableUnits: List<ResistanceUnit> = emptyList(),

	// ==================== Calculator State ====================
	
	/**
	 * The currently selected calculator type.
	 * Determines which calculation formula is used and what the input fields represent.
	 */
	val selectedCalculator: CalculatorType = CalculatorType.VOLTAGE,
	
	/**
	 * The first input field value for the calculator.
	 * Stored as a string to preserve user input exactly as typed.
	 */
	val calculatorInput1: String = "",
	
	/**
	 * The second input field value for the calculator.
	 * Stored as a string to preserve user input exactly as typed.
	 */
	val calculatorInput2: String = "",
	
	/**
	 * The formatted result of the calculator operation.
	 * Formatted to [Constants.RESULT_DECIMAL_PLACES] decimal places with
	 * trailing zeros and decimal points removed.
	 */
	val calculatorResult: String = ""
)

/**
 * Type of electrical quantity for conversion.
 * 
 * This enum represents the three types of electrical quantities that can be
 * converted in the conversion tab. Each quantity type has its own set of units
 * and conversion logic.
 * 
 * - VOLTAGE: Electric potential difference, measured in volts (V)
 * - CURRENT: Electric current flow, measured in amperes (A)
 * - RESISTANCE: Opposition to current flow, measured in ohms (Ω)
 */
enum class QuantityType {
	/**
	 * Voltage (electric potential difference).
	 * Units: Volt (V), Millivolt (mV), Kilovolt (kV)
	 */
	VOLTAGE,
	
	/**
	 * Current (electric current flow).
	 * Units: Ampere (A), Milliampere (mA), Kiloampere (kA)
	 */
	CURRENT,
	
	/**
	 * Resistance (opposition to current flow).
	 * Units: Ohm (Ω), Kiloohm (kΩ), Megaohm (MΩ)
	 */
	RESISTANCE
}

/**
 * Type of calculator for electrical calculations.
 * 
 * This enum represents the six different calculators available in the
 * calculators tab. Each calculator uses a specific formula from Ohm's law
 * or power calculations.
 * 
 * The calculators are organized into two groups:
 * 1. Ohm's Law calculators: Calculate voltage, current, or resistance
 * 2. Power calculators: Calculate power using different combinations of parameters
 */
enum class CalculatorType {
	/**
	 * Voltage calculator using Ohm's law: V = I × R
	 * Inputs: Current (I) and Resistance (R)
	 * Output: Voltage in volts (V)
	 */
	VOLTAGE, // V = I × R
	
	/**
	 * Current calculator using Ohm's law: I = V / R
	 * Inputs: Voltage (V) and Resistance (R)
	 * Output: Current in amperes (A)
	 */
	CURRENT, // I = V / R
	
	/**
	 * Resistance calculator using Ohm's law: R = V / I
	 * Inputs: Voltage (V) and Current (I)
	 * Output: Resistance in ohms (Ω)
	 */
	RESISTANCE, // R = V / I
	
	/**
	 * Power calculator using voltage and current: P = V × I
	 * Inputs: Voltage (V) and Current (I)
	 * Output: Power in watts (W)
	 */
	POWER_VI, // P = V × I
	
	/**
	 * Power calculator using current and resistance: P = I² × R
	 * Inputs: Current (I) and Resistance (R)
	 * Output: Power in watts (W)
	 */
	POWER_IR, // P = I² × R
	
	/**
	 * Power calculator using voltage and resistance: P = V² / R
	 * Inputs: Voltage (V) and Resistance (R)
	 * Output: Power in watts (W)
	 */
	POWER_VR // P = V² / R
}

