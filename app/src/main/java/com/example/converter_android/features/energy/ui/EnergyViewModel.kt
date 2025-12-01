package com.example.converter_android.features.energy.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.converter_android.core.utils.Constants
import com.example.converter_android.features.energy.data.EnergyConverter
import com.example.converter_android.features.energy.data.EnergyUnits
import com.example.converter_android.features.energy.domain.models.EnergyUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for energy conversion screen.
 * Manages the state and business logic for energy conversion.
 */
class EnergyViewModel : ViewModel() {
	private val energyConverter = EnergyConverter()

	private val _uiState = MutableStateFlow(EnergyUiState())
	val uiState: StateFlow<EnergyUiState> = _uiState.asStateFlow()

	init {
		// Initialize with default units
		_uiState.value = EnergyUiState(
			fromUnit = EnergyUnits.getDefaultFromUnit(),
			toUnit = EnergyUnits.getDefaultToUnit(),
			availableUnits = EnergyUnits.getAllUnits()
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
	fun updateFromUnit(unit: EnergyUnit) {
		_uiState.value = _uiState.value.copy(fromUnit = unit)
		calculateResult()
	}

	/**
	 * Updates the target unit and recalculates the result.
	 */
	fun updateToUnit(unit: EnergyUnit) {
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
					val result = energyConverter.convert(
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
 * UI state for energy conversion screen.
 */
data class EnergyUiState(
	val inputValue: String = "",
	val numericValue: Double = 0.0,
	val fromUnit: EnergyUnit = EnergyUnit.JOULE,
	val toUnit: EnergyUnit = EnergyUnit.KILOJOULE,
	val result: String = "0",
	val availableUnits: List<EnergyUnit> = emptyList()
)

