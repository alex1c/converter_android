package com.example.converter_android.features.electricity.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.converter_android.core.utils.Constants
import com.example.converter_android.features.electricity.domain.models.CurrentUnit
import com.example.converter_android.features.electricity.domain.models.ResistanceUnit
import com.example.converter_android.features.electricity.domain.models.VoltageUnit

/**
 * Main screen for electricity conversion and calculations.
 * 
 * This composable function creates the main electricity screen with two tabs:
 * 1. Conversion Tab: Allows users to convert between different units of voltage,
 *    current, and resistance
 * 2. Calculators Tab: Provides six calculators for electrical calculations using
 *    Ohm's law and power formulas
 * 
 * The screen uses Material3's Scaffold for consistent app structure and TabRow
 * for tab navigation. The screen observes the ViewModel's state using
 * [collectAsStateWithLifecycle] to reactively update when state changes.
 * 
 * Architecture:
 * - Uses MVVM pattern with [ElectricityViewModel] managing all business logic
 * - UI is purely declarative and reactive to state changes
 * - Follows Material Design 3 guidelines for consistent look and feel
 * - Supports both light and dark themes automatically
 * 
 * @param viewModel The ViewModel that manages the screen's state and business logic
 * @param onBackClick Callback function called when the back button is clicked
 * @param modifier Modifier to be applied to the main Column container
 * 
 * @see ElectricityViewModel
 * @see ConversionTab
 * @see CalculatorsTab
 */
@Composable
fun ElectricityScreen(
	viewModel: ElectricityViewModel,
	onBackClick: () -> Unit = {},
	modifier: Modifier = Modifier
) {
	// Observe the ViewModel's state using lifecycle-aware state collection
	// This ensures the UI updates reactively when state changes
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()
	
	// Get Android context for accessing resources (used in child composables)
	val context = LocalContext.current
	
	// Get status bar insets for proper top padding
	val statusBarPadding = WindowInsets.statusBars.asPaddingValues()
	// Calculate top padding: status bar + header font size (headlineMedium fontSize)
	val density = LocalDensity.current
	val headerFontSize = with(density) { MaterialTheme.typography.headlineMedium.fontSize.toDp() }
	val topBarPadding = statusBarPadding.calculateTopPadding() + headerFontSize

	// Use Scaffold for consistent app structure with top bar
	Scaffold(
		topBar = {
			// Top bar with back button and title
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(
						start = Constants.SPACING_SM.dp,
						end = Constants.SPACING_SM.dp,
						top = topBarPadding, // Top padding: status bar + header height
						bottom = Constants.SPACING_SM.dp
					),
				verticalAlignment = Alignment.CenterVertically
			) {
				// Back button to navigate to previous screen
				IconButton(onClick = onBackClick) {
					Icon(
						imageVector = Icons.Default.ArrowBack,
						contentDescription = "Назад"
					)
				}
				// Centered title text
				Text(
					text = stringResource(com.forestmusic.converter_android.R.string.electricity_converter_title),
					style = MaterialTheme.typography.headlineMedium,
					fontWeight = FontWeight.Bold,
					modifier = Modifier
						.weight(1f)
						.padding(top = 8.dp, bottom = 8.dp),
					textAlign = TextAlign.Center
				)
				// Spacer to balance the layout (same width as back button)
				Spacer(modifier = Modifier.size(48.dp))
			}
		}
	) { paddingValues ->
		// Main content column with padding from Scaffold
		Column(
			modifier = modifier
				.fillMaxSize()
				.padding(paddingValues)
		) {
			// Tab row for switching between Conversion and Calculators tabs
			TabRow(selectedTabIndex = uiState.selectedTab) {
				// Conversion tab
				Tab(
					selected = uiState.selectedTab == 0,
					onClick = { viewModel.updateTab(0) },
					text = { Text(stringResource(com.forestmusic.converter_android.R.string.tab_conversion)) }
				)
				// Calculators tab
				Tab(
					selected = uiState.selectedTab == 1,
					onClick = { viewModel.updateTab(1) },
					text = { Text(stringResource(com.forestmusic.converter_android.R.string.tab_calculators)) }
				)
			}

			// Display the appropriate tab content based on selected tab
			when (uiState.selectedTab) {
				0 -> ConversionTab(uiState, viewModel, context)
				1 -> CalculatorsTab(uiState, viewModel, context)
			}
		}
	}
}

/**
 * Conversion tab composable.
 * 
 * This composable displays the unit conversion interface, allowing users to:
 * - Select a quantity type (Voltage, Current, or Resistance)
 * - Enter a value to convert
 * - Select source and target units
 * - View the converted result
 * 
 * The tab uses a scrollable column layout to accommodate all UI elements
 * and ensure usability on smaller screens. The conversion result is
 * automatically recalculated whenever the input or units change.
 * 
 * @param uiState The current UI state containing all conversion-related data
 * @param viewModel The ViewModel for handling user interactions and state updates
 * @param context Android context for accessing localized strings
 */
@Composable
private fun ConversionTab(
	uiState: ElectricityUiState,
	viewModel: ElectricityViewModel,
	context: android.content.Context
) {
	// Scrollable column layout with consistent spacing
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(
				start = Constants.SPACING_MD.dp,
				end = Constants.SPACING_MD.dp,
				bottom = Constants.SPACING_MD.dp,
				top = Constants.SPACING_MD.dp // Top padding (Scaffold already has topBar padding)
			)
			.verticalScroll(rememberScrollState()), // Enable vertical scrolling for smaller screens
		verticalArrangement = Arrangement.spacedBy(Constants.SPACING_LG.dp) // Consistent spacing between elements
	) {
		// Quantity type selector: Allows user to choose between Voltage, Current, or Resistance
		QuantityTypeSelector(
			selectedType = uiState.selectedQuantityType,
			onTypeSelected = { viewModel.updateQuantityType(it) }
		)

		// Input field for entering the value to convert
		// Uses OutlinedTextField for Material Design 3 styling
		OutlinedTextField(
			value = uiState.inputValue, // Display the current input value
			onValueChange = { newValue ->
				// Limit input length to prevent extremely long numbers
				if (newValue.length <= Constants.MAX_INPUT_LENGTH) {
					viewModel.updateInputValue(newValue)
				}
			},
			label = { Text(stringResource(com.forestmusic.converter_android.R.string.enter_value)) },
			// Use decimal keyboard for numeric input (includes digits and decimal point)
			keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
				keyboardType = KeyboardType.Decimal
			),
			modifier = Modifier.fillMaxWidth(),
			singleLine = true // Restrict to single line input
		)

		// Unit selection row: Contains "from" unit, swap button, and "to" unit
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.spacedBy(16.dp), // Space between elements
			verticalAlignment = Alignment.CenterVertically // Vertically center all elements
		) {
			// "From" unit dropdown - dynamically shows units based on selected quantity type
			when (uiState.selectedQuantityType) {
				QuantityType.VOLTAGE -> UnitDropdown(
					label = stringResource(com.forestmusic.converter_android.R.string.from_unit),
					selectedUnit = uiState.voltageFromUnit,
					units = uiState.voltageAvailableUnits,
					onUnitSelected = { viewModel.updateVoltageFromUnit(it) },
					getDisplayName = { context.getString(it.displayNameRes) },
					modifier = Modifier.weight(1f) // Take equal space with "to" unit dropdown
				)
				QuantityType.CURRENT -> UnitDropdown(
					label = stringResource(com.forestmusic.converter_android.R.string.from_unit),
					selectedUnit = uiState.currentFromUnit,
					units = uiState.currentAvailableUnits,
					onUnitSelected = { viewModel.updateCurrentFromUnit(it) },
					getDisplayName = { context.getString(it.displayNameRes) },
					modifier = Modifier.weight(1f)
				)
				QuantityType.RESISTANCE -> UnitDropdown(
					label = stringResource(com.forestmusic.converter_android.R.string.from_unit),
					selectedUnit = uiState.resistanceFromUnit,
					units = uiState.resistanceAvailableUnits,
					onUnitSelected = { viewModel.updateResistanceFromUnit(it) },
					getDisplayName = { context.getString(it.displayNameRes) },
					modifier = Modifier.weight(1f)
				)
			}

			// Swap button: Quickly swap "from" and "to" units
			// Uses a text symbol (⇄) instead of an icon for simplicity
			IconButton(
				onClick = { viewModel.swapUnits() },
				modifier = Modifier.size(48.dp) // Standard touch target size
			) {
				Text(
					text = "⇄",
					style = MaterialTheme.typography.titleLarge,
					fontWeight = FontWeight.Bold
				)
			}

			// "To" unit dropdown - dynamically shows units based on selected quantity type
			when (uiState.selectedQuantityType) {
				QuantityType.VOLTAGE -> UnitDropdown(
					label = stringResource(com.forestmusic.converter_android.R.string.to_unit),
					selectedUnit = uiState.voltageToUnit,
					units = uiState.voltageAvailableUnits,
					onUnitSelected = { viewModel.updateVoltageToUnit(it) },
					getDisplayName = { context.getString(it.displayNameRes) },
					modifier = Modifier.weight(1f)
				)
				QuantityType.CURRENT -> UnitDropdown(
					label = stringResource(com.forestmusic.converter_android.R.string.to_unit),
					selectedUnit = uiState.currentToUnit,
					units = uiState.currentAvailableUnits,
					onUnitSelected = { viewModel.updateCurrentToUnit(it) },
					getDisplayName = { context.getString(it.displayNameRes) },
					modifier = Modifier.weight(1f)
				)
				QuantityType.RESISTANCE -> UnitDropdown(
					label = stringResource(com.forestmusic.converter_android.R.string.to_unit),
					selectedUnit = uiState.resistanceToUnit,
					units = uiState.resistanceAvailableUnits,
					onUnitSelected = { viewModel.updateResistanceToUnit(it) },
					getDisplayName = { context.getString(it.displayNameRes) },
					modifier = Modifier.weight(1f)
				)
			}
		}

		// Result card: Displays the conversion result in a prominent, styled card
		Card(
			modifier = Modifier.fillMaxWidth(),
			elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), // Subtle shadow for depth
			colors = CardDefaults.cardColors(
				containerColor = MaterialTheme.colorScheme.primaryContainer // Use primary container color for emphasis
			)
		) {
			Column(
				modifier = Modifier
					.fillMaxWidth()
					.padding(24.dp),
				horizontalAlignment = Alignment.CenterHorizontally // Center all content
			) {
				// "Result" label
				Text(
					text = stringResource(com.forestmusic.converter_android.R.string.result),
					style = MaterialTheme.typography.titleMedium,
					color = MaterialTheme.colorScheme.onPrimaryContainer
				)
				Spacer(modifier = Modifier.height(8.dp))
				// Result value: Display "—" if empty, otherwise show the formatted result
				Text(
					text = if (uiState.conversionResult.isEmpty()) "—" else uiState.conversionResult,
					style = MaterialTheme.typography.headlineLarge, // Large, prominent text
					fontWeight = FontWeight.Bold,
					color = MaterialTheme.colorScheme.onPrimaryContainer,
					textAlign = TextAlign.Center
				)
				// Unit label: Show the target unit name below the result
				if (uiState.conversionResult.isNotEmpty()) {
					Spacer(modifier = Modifier.height(4.dp))
					Text(
						text = when (uiState.selectedQuantityType) {
							QuantityType.VOLTAGE -> context.getString(uiState.voltageToUnit.displayNameRes)
							QuantityType.CURRENT -> context.getString(uiState.currentToUnit.displayNameRes)
							QuantityType.RESISTANCE -> context.getString(uiState.resistanceToUnit.displayNameRes)
						},
						style = MaterialTheme.typography.bodyMedium,
						color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f) // Slightly transparent
					)
				}
			}
		}
	}
}

/**
 * Calculators tab composable.
 * 
 * This composable displays the electrical calculators interface, allowing users to:
 * - Select a calculator type from six available calculators
 * - Enter two input values (specific to each calculator type)
 * - Calculate the result using the appropriate formula
 * - View the calculated result with proper unit indication
 * 
 * Available calculators:
 * - Voltage: V = I × R
 * - Current: I = V / R
 * - Resistance: R = V / I
 * - Power (V×I): P = V × I
 * - Power (I²×R): P = I² × R
 * - Power (V²/R): P = V² / R
 * 
 * The tab uses a scrollable column layout to accommodate all UI elements
 * and ensure usability on smaller screens. Input validation ensures that
 * calculations are only performed with valid, non-zero inputs.
 * 
 * @param uiState The current UI state containing all calculator-related data
 * @param viewModel The ViewModel for handling user interactions and calculations
 * @param context Android context for accessing localized strings
 */
@Composable
private fun CalculatorsTab(
	uiState: ElectricityUiState,
	viewModel: ElectricityViewModel,
	context: android.content.Context
) {
	// Scrollable column layout with consistent spacing
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(
				start = Constants.SPACING_MD.dp,
				end = Constants.SPACING_MD.dp,
				bottom = Constants.SPACING_MD.dp,
				top = Constants.SPACING_MD.dp // Top padding (Scaffold already has topBar padding)
			)
			.verticalScroll(rememberScrollState()), // Enable vertical scrolling
		verticalArrangement = Arrangement.spacedBy(Constants.SPACING_MD.dp) // Consistent spacing
	) {
		// Calculator type selector: List of all available calculators
		CalculatorTypeSelector(
			selectedType = uiState.selectedCalculator,
			onTypeSelected = { viewModel.updateCalculatorType(it) }
		)

		// Determine input field labels based on selected calculator type
		// Each calculator requires different inputs (V, I, or R)
		val (label1, label2) = when (uiState.selectedCalculator) {
			CalculatorType.VOLTAGE -> stringResource(com.forestmusic.converter_android.R.string.calc_input_current) to stringResource(com.forestmusic.converter_android.R.string.calc_input_resistance)
			CalculatorType.CURRENT -> stringResource(com.forestmusic.converter_android.R.string.calc_input_voltage) to stringResource(com.forestmusic.converter_android.R.string.calc_input_resistance)
			CalculatorType.RESISTANCE -> stringResource(com.forestmusic.converter_android.R.string.calc_input_voltage) to stringResource(com.forestmusic.converter_android.R.string.calc_input_current)
			CalculatorType.POWER_VI -> stringResource(com.forestmusic.converter_android.R.string.calc_input_voltage) to stringResource(com.forestmusic.converter_android.R.string.calc_input_current)
			CalculatorType.POWER_IR -> stringResource(com.forestmusic.converter_android.R.string.calc_input_current) to stringResource(com.forestmusic.converter_android.R.string.calc_input_resistance)
			CalculatorType.POWER_VR -> stringResource(com.forestmusic.converter_android.R.string.calc_input_voltage) to stringResource(com.forestmusic.converter_android.R.string.calc_input_resistance)
		}

		// First input field: Label changes based on calculator type
		OutlinedTextField(
			value = uiState.calculatorInput1,
			onValueChange = { viewModel.updateCalculatorInput1(it) },
			label = { Text(label1) },
			keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
				keyboardType = KeyboardType.Decimal
			),
			modifier = Modifier.fillMaxWidth(),
			singleLine = true
		)

		// Second input field: Label changes based on calculator type
		OutlinedTextField(
			value = uiState.calculatorInput2,
			onValueChange = { viewModel.updateCalculatorInput2(it) },
			label = { Text(label2) },
			keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
				keyboardType = KeyboardType.Decimal
			),
			modifier = Modifier.fillMaxWidth(),
			singleLine = true
		)

		// Calculate button: Triggers the calculation when clicked
		Button(
			onClick = { viewModel.calculate() },
			modifier = Modifier.fillMaxWidth()
		) {
			Text(stringResource(com.forestmusic.converter_android.R.string.calculate))
		}

		// Result card: Only displayed when calculation result is available
		if (uiState.calculatorResult.isNotEmpty()) {
			Card(
				modifier = Modifier.fillMaxWidth(),
				elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
				colors = CardDefaults.cardColors(
					containerColor = MaterialTheme.colorScheme.primaryContainer
				)
			) {
				Column(
					modifier = Modifier
						.fillMaxWidth()
						.padding(24.dp),
					horizontalAlignment = Alignment.CenterHorizontally
				) {
					// "Result" label
					Text(
						text = stringResource(com.forestmusic.converter_android.R.string.result),
						style = MaterialTheme.typography.titleMedium,
						color = MaterialTheme.colorScheme.onPrimaryContainer
					)
					Spacer(modifier = Modifier.height(8.dp))
					// Calculated result value
					Text(
						text = uiState.calculatorResult,
						style = MaterialTheme.typography.headlineLarge,
						fontWeight = FontWeight.Bold,
						color = MaterialTheme.colorScheme.onPrimaryContainer,
						textAlign = TextAlign.Center
					)
					Spacer(modifier = Modifier.height(4.dp))
					// Unit label: Shows appropriate unit based on calculator type
					Text(
						text = when (uiState.selectedCalculator) {
							CalculatorType.VOLTAGE -> "В" // Volts
							CalculatorType.CURRENT -> "А" // Amperes
							CalculatorType.RESISTANCE -> "Ом" // Ohms
							CalculatorType.POWER_VI,
							CalculatorType.POWER_IR,
							CalculatorType.POWER_VR -> "Вт" // Watts
						},
						style = MaterialTheme.typography.bodyMedium,
						color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
					)
				}
			}
		}
	}
}

/**
 * Quantity type selector composable.
 * 
 * This composable displays three buttons for selecting the quantity type
 * (Voltage, Current, or Resistance) in the conversion tab. The selected
 * type is highlighted with the primary color, while unselected types use
 * the surface variant color.
 * 
 * The buttons are arranged horizontally with equal weight, ensuring they
 * take up equal space and are evenly distributed across the width.
 * 
 * @param selectedType The currently selected quantity type
 * @param onTypeSelected Callback function called when a quantity type is selected
 */
@Composable
private fun QuantityTypeSelector(
	selectedType: QuantityType,
	onTypeSelected: (QuantityType) -> Unit
) {
	val context = LocalContext.current
	
	// Horizontal row of buttons
	Row(
		modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between buttons
	) {
		// Create a button for each quantity type
		listOf(QuantityType.VOLTAGE, QuantityType.CURRENT, QuantityType.RESISTANCE).forEach { type ->
			Button(
				onClick = { onTypeSelected(type) },
				modifier = Modifier.weight(1f), // Equal width for all buttons
				// Highlight selected button with primary color, others with surface variant
				colors = androidx.compose.material3.ButtonDefaults.buttonColors(
					containerColor = if (selectedType == type)
						MaterialTheme.colorScheme.primary
					else
						MaterialTheme.colorScheme.surfaceVariant
				)
			) {
				Text(
					text = when (type) {
						QuantityType.VOLTAGE -> context.getString(com.forestmusic.converter_android.R.string.voltage)
						QuantityType.CURRENT -> context.getString(com.forestmusic.converter_android.R.string.current)
						QuantityType.RESISTANCE -> context.getString(com.forestmusic.converter_android.R.string.resistance)
					}
				)
			}
		}
	}
}

/**
 * Calculator type selector composable.
 * 
 * This composable displays a vertical list of cards, each representing a
 * different calculator type. Users can tap on a card to select that calculator.
 * The selected calculator is highlighted with the primary container color,
 * while unselected calculators use the surface variant color.
 * 
 * The list includes six calculators:
 * - Voltage calculator (V = I × R)
 * - Current calculator (I = V / R)
 * - Resistance calculator (R = V / I)
 * - Power calculator (P = V × I)
 * - Power calculator (P = I² × R)
 * - Power calculator (P = V² / R)
 * 
 * Each card is clickable and displays the calculator's formula in its title.
 * 
 * @param selectedType The currently selected calculator type
 * @param onTypeSelected Callback function called when a calculator type is selected
 */
@Composable
private fun CalculatorTypeSelector(
	selectedType: CalculatorType,
	onTypeSelected: (CalculatorType) -> Unit
) {
	val context = LocalContext.current
	
	// Vertical column of calculator cards
	Column(
		modifier = Modifier.fillMaxWidth(),
		verticalArrangement = Arrangement.spacedBy(8.dp) // Space between cards
	) {
		// Create a card for each calculator type
		listOf(
			CalculatorType.VOLTAGE,
			CalculatorType.CURRENT,
			CalculatorType.RESISTANCE,
			CalculatorType.POWER_VI,
			CalculatorType.POWER_IR,
			CalculatorType.POWER_VR
		).forEach { type ->
			Card(
				modifier = Modifier
					.fillMaxWidth()
					.clickable { onTypeSelected(type) }, // Make entire card clickable
				// Highlight selected calculator with primary container color
				colors = CardDefaults.cardColors(
					containerColor = if (selectedType == type)
						MaterialTheme.colorScheme.primaryContainer
					else
						MaterialTheme.colorScheme.surfaceVariant
				)
			) {
				// Display calculator name with formula
				Text(
					text = when (type) {
						CalculatorType.VOLTAGE -> context.getString(com.forestmusic.converter_android.R.string.calc_voltage)
						CalculatorType.CURRENT -> context.getString(com.forestmusic.converter_android.R.string.calc_current)
						CalculatorType.RESISTANCE -> context.getString(com.forestmusic.converter_android.R.string.calc_resistance)
						CalculatorType.POWER_VI -> context.getString(com.forestmusic.converter_android.R.string.calc_power_vi)
						CalculatorType.POWER_IR -> context.getString(com.forestmusic.converter_android.R.string.calc_power_ir)
						CalculatorType.POWER_VR -> context.getString(com.forestmusic.converter_android.R.string.calc_power_vr)
					},
					modifier = Modifier.padding(16.dp),
					style = MaterialTheme.typography.bodyLarge
				)
			}
		}
	}
}

/**
 * Generic unit dropdown composable.
 * 
 * This composable creates a dropdown menu for selecting units. It uses a
 * read-only text field that displays the currently selected unit, and when
 * clicked, shows a dropdown menu with all available units.
 * 
 * The composable is generic, allowing it to work with any unit type (VoltageUnit,
 * CurrentUnit, or ResistanceUnit) through type parameters and function parameters.
 * 
 * Features:
 * - Read-only text field showing the selected unit
 * - Clickable field and trailing icon to open/close dropdown
 * - Dropdown menu with all available units
 * - Visual indicator (▲/▼) showing dropdown state
 * - Automatic closure when a unit is selected
 * 
 * @param T The type of unit (e.g., VoltageUnit, CurrentUnit, ResistanceUnit)
 * @param label The label text to display above the dropdown
 * @param selectedUnit The currently selected unit
 * @param units List of all available units to display in the dropdown
 * @param onUnitSelected Callback function called when a unit is selected
 * @param getDisplayName Function to get the display name for a unit
 * @param modifier Modifier to be applied to the container Box
 */
@Composable
private fun <T> UnitDropdown(
	label: String,
	selectedUnit: T,
	units: List<T>,
	onUnitSelected: (T) -> Unit,
	getDisplayName: (T) -> String,
	modifier: Modifier = Modifier
) {
	// State to track whether the dropdown menu is expanded
	var expanded by remember { mutableStateOf(false) }

	// Box container to position the dropdown menu relative to the text field
	Box(modifier = modifier) {
		// Read-only text field that displays the selected unit
		OutlinedTextField(
			value = getDisplayName(selectedUnit), // Display name of selected unit
			onValueChange = {}, // No-op: field is read-only
			readOnly = true, // Prevent direct text input
			enabled = true, // Field is enabled and clickable
			label = { Text(label) },
			trailingIcon = {
				// Visual indicator showing dropdown state (▲ when open, ▼ when closed)
				Text(
					text = if (expanded) "▲" else "▼",
					style = MaterialTheme.typography.bodySmall,
					modifier = Modifier.clickable { expanded = !expanded }
				)
			},
			// Make entire field clickable to open dropdown
			modifier = Modifier
				.fillMaxWidth()
				.clickable { expanded = !expanded }
		)

		// Dropdown menu that appears when expanded
		DropdownMenu(
			expanded = expanded,
			onDismissRequest = { expanded = false } // Close when user taps outside
		) {
			// Create a menu item for each available unit
			units.forEach { unit ->
				DropdownMenuItem(
					text = { Text(getDisplayName(unit)) },
					onClick = {
						onUnitSelected(unit) // Notify parent of selection
						expanded = false // Close dropdown after selection
					}
				)
			}
		}
	}
}

