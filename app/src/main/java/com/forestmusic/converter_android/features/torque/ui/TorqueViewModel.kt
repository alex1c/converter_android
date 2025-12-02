package com.forestmusic.converter_android.features.torque.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.forestmusic.converter_android.core.utils.Constants
import com.forestmusic.converter_android.features.torque.data.TorqueConverter
import com.forestmusic.converter_android.features.torque.data.TorqueUnits
import com.forestmusic.converter_android.features.torque.domain.models.TorqueUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for torque conversion screen.
 * Manages the state and business logic for torque conversion.
 */
class TorqueViewModel : ViewModel() {
	private val torqueConverter = TorqueConverter()

	private val _uiState = MutableStateFlow(TorqueUiState())
	val uiState: StateFlow<TorqueUiState> = _uiState.asStateFlow()

	init {
		// Initialize with default units
		_uiState.value = TorqueUiState(
			fromUnit = TorqueUnits.getDefaultFromUnit(),
			toUnit = TorqueUnits.getDefaultToUnit(),
			availableUnits = TorqueUnits.getAllUnits()
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
	fun updateFromUnit(unit: TorqueUnit) {
		_uiState.value = _uiState.value.copy(fromUnit = unit)
		calculateResult()
	}

	/**
	 * Updates the target unit and recalculates the result.
	 */
	fun updateToUnit(unit: TorqueUnit) {
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
					val result = torqueConverter.convert(
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
 * UI state for torque conversion screen.
 */
data class TorqueUiState(
	val inputValue: String = "",
	val numericValue: Double = 0.0,
	val fromUnit: TorqueUnit = TorqueUnit.NEWTON_METER,
	val toUnit: TorqueUnit = TorqueUnit.POUND_FOOT,
	val result: String = "0",
	val availableUnits: List<TorqueUnit> = emptyList()
)

