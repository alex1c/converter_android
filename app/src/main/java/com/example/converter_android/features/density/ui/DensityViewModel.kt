package com.example.converter_android.features.density.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.converter_android.core.utils.Constants
import com.example.converter_android.features.density.data.DensityConverter
import com.example.converter_android.features.density.data.DensityUnits
import com.example.converter_android.features.density.domain.models.DensityUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for density conversion screen.
 * Manages the state and business logic for density conversion.
 */
class DensityViewModel : ViewModel() {
	private val densityConverter = DensityConverter()

	private val _uiState = MutableStateFlow(DensityUiState())
	val uiState: StateFlow<DensityUiState> = _uiState.asStateFlow()

	init {
		// Initialize with default units
		_uiState.value = DensityUiState(
			fromUnit = DensityUnits.getDefaultFromUnit(),
			toUnit = DensityUnits.getDefaultToUnit(),
			availableUnits = DensityUnits.getAllUnits()
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
	fun updateFromUnit(unit: DensityUnit) {
		_uiState.value = _uiState.value.copy(fromUnit = unit)
		calculateResult()
	}

	/**
	 * Updates the target unit and recalculates the result.
	 */
	fun updateToUnit(unit: DensityUnit) {
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
					val result = densityConverter.convert(
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
 * UI state for density conversion screen.
 */
data class DensityUiState(
	val inputValue: String = "",
	val numericValue: Double = 0.0,
	val fromUnit: DensityUnit = DensityUnit.KILOGRAM_PER_CUBIC_METER,
	val toUnit: DensityUnit = DensityUnit.GRAM_PER_CUBIC_CENTIMETER,
	val result: String = "0",
	val availableUnits: List<DensityUnit> = emptyList()
)

