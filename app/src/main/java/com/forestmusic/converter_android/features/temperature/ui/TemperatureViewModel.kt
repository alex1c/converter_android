package com.forestmusic.converter_android.features.temperature.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.forestmusic.converter_android.core.utils.Constants
import com.forestmusic.converter_android.features.temperature.data.TemperatureConverter
import com.forestmusic.converter_android.features.temperature.data.TemperatureUnits
import com.forestmusic.converter_android.features.temperature.domain.models.TemperatureUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for temperature conversion screen.
 * Manages the state and business logic for temperature conversion.
 */
class TemperatureViewModel : ViewModel() {
	private val temperatureConverter = TemperatureConverter()

	private val _uiState = MutableStateFlow(TemperatureUiState())
	val uiState: StateFlow<TemperatureUiState> = _uiState.asStateFlow()

	init {
		// Initialize with default units
		_uiState.value = TemperatureUiState(
			fromUnit = TemperatureUnits.getDefaultFromUnit(),
			toUnit = TemperatureUnits.getDefaultToUnit(),
			availableUnits = TemperatureUnits.getAllUnits()
		)
	}

	/**
	 * Updates the input value and recalculates the result.
	 */
	fun updateInputValue(value: String) {
		// Allow empty string, single dot, or minus for better UX
		val numericValue = when {
			value.isEmpty() || value == "." || value == "-" || value == "-." -> 0.0
			else -> value.toDoubleOrNull() ?: 0.0
		}
		_uiState.value = _uiState.value.copy(
			inputValue = value,
			numericValue = numericValue
		)
		calculateResult()
	}

	/**
	 * Updates the source unit and recalculates the result.
	 */
	fun updateFromUnit(unit: TemperatureUnit) {
		_uiState.value = _uiState.value.copy(fromUnit = unit)
		calculateResult()
	}

	/**
	 * Updates the target unit and recalculates the result.
	 */
	fun updateToUnit(unit: TemperatureUnit) {
		_uiState.value = _uiState.value.copy(toUnit = unit)
		calculateResult()
	}

	/**
	 * Swaps the source and target units.
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
	 * Calculates the conversion result.
	 */
	private fun calculateResult() {
		viewModelScope.launch {
			val state = _uiState.value
			when {
				state.inputValue.isEmpty() || 
				state.inputValue == "." || 
				state.inputValue == "-" || 
				state.inputValue == "-." -> {
					_uiState.value = state.copy(result = "")
				}
				state.numericValue != 0.0 -> {
					val result = temperatureConverter.convert(
						state.numericValue,
						state.fromUnit,
						state.toUnit
					)
					_uiState.value = state.copy(
						result = String.format("%.${Constants.RESULT_DECIMAL_PLACES}f", result)
							.trimEnd('0')
							.trimEnd('.')
					)
				}
				else -> {
					_uiState.value = state.copy(result = "0")
				}
			}
		}
	}
}

/**
 * UI state for temperature conversion screen.
 */
data class TemperatureUiState(
	val inputValue: String = "",
	val numericValue: Double = 0.0,
	val fromUnit: TemperatureUnit = TemperatureUnit.CELSIUS,
	val toUnit: TemperatureUnit = TemperatureUnit.FAHRENHEIT,
	val result: String = "0",
	val availableUnits: List<TemperatureUnit> = emptyList()
)

