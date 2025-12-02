package com.forestmusic.converter_android.features.pressure.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.forestmusic.converter_android.core.utils.Constants
import com.forestmusic.converter_android.features.pressure.data.PressureConverter
import com.forestmusic.converter_android.features.pressure.data.PressureUnits
import com.forestmusic.converter_android.features.pressure.domain.models.PressureUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for pressure conversion screen.
 * Manages the state and business logic for pressure conversion.
 */
class PressureViewModel : ViewModel() {
	private val pressureConverter = PressureConverter()

	private val _uiState = MutableStateFlow(PressureUiState())
	val uiState: StateFlow<PressureUiState> = _uiState.asStateFlow()

	init {
		// Initialize with default units
		_uiState.value = PressureUiState(
			fromUnit = PressureUnits.getDefaultFromUnit(),
			toUnit = PressureUnits.getDefaultToUnit(),
			availableUnits = PressureUnits.getAllUnits()
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
	fun updateFromUnit(unit: PressureUnit) {
		_uiState.value = _uiState.value.copy(fromUnit = unit)
		calculateResult()
	}

	/**
	 * Updates the target unit and recalculates the result.
	 */
	fun updateToUnit(unit: PressureUnit) {
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
					val result = pressureConverter.convert(
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
 * UI state for pressure conversion screen.
 */
data class PressureUiState(
	val inputValue: String = "",
	val numericValue: Double = 0.0,
	val fromUnit: PressureUnit = PressureUnit.PASCAL,
	val toUnit: PressureUnit = PressureUnit.KILOPASCAL,
	val result: String = "0",
	val availableUnits: List<PressureUnit> = emptyList()
)

