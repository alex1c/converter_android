package com.example.converter_android.features.speed.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.converter_android.core.utils.Constants
import com.example.converter_android.features.speed.domain.models.SpeedUnit

/**
 * Main screen for speed conversion.
 */
@Composable
fun SpeedScreen(
	viewModel: SpeedViewModel,
	onBackClick: () -> Unit = {},
	modifier: Modifier = Modifier
) {
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()
	val context = LocalContext.current

	Column(
		modifier = modifier
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.spacedBy(24.dp)
	) {
		// Back button and Title row
		Row(
			modifier = Modifier.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically
		) {
			IconButton(onClick = onBackClick) {
				Icon(
					imageVector = Icons.Default.ArrowBack,
					contentDescription = "Назад"
				)
			}
			Text(
				text = stringResource(com.forestmusic.converter_android.R.string.speed_converter_title),
				style = MaterialTheme.typography.headlineMedium,
				fontWeight = FontWeight.Bold,
				modifier = Modifier
					.weight(1f)
					.padding(top = 8.dp, bottom = 8.dp),
				textAlign = TextAlign.Center
			)
			Spacer(modifier = Modifier.size(48.dp)) // Balance for back button
		}

		// Input field
		OutlinedTextField(
			value = uiState.inputValue,
			onValueChange = { newValue ->
				if (newValue.length <= Constants.MAX_INPUT_LENGTH) {
					viewModel.updateInputValue(newValue)
				}
			},
			label = { Text(stringResource(com.forestmusic.converter_android.R.string.enter_value)) },
			keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
				keyboardType = KeyboardType.Decimal
			),
			modifier = Modifier.fillMaxWidth(),
			singleLine = true
		)

		// Unit selection row
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.spacedBy(16.dp),
			verticalAlignment = Alignment.CenterVertically
		) {
			// From unit dropdown
			UnitDropdown(
				label = stringResource(com.forestmusic.converter_android.R.string.from_unit),
				selectedUnit = uiState.fromUnit,
				units = uiState.availableUnits,
				onUnitSelected = { viewModel.updateFromUnit(it) },
				modifier = Modifier.weight(1f)
			)

			// Swap button
			IconButton(
				onClick = { viewModel.swapUnits() },
				modifier = Modifier.size(48.dp)
			) {
				Text(
					text = "⇄",
					style = MaterialTheme.typography.titleLarge,
					fontWeight = FontWeight.Bold
				)
			}

			// To unit dropdown
			UnitDropdown(
				label = stringResource(com.forestmusic.converter_android.R.string.to_unit),
				selectedUnit = uiState.toUnit,
				units = uiState.availableUnits,
				onUnitSelected = { viewModel.updateToUnit(it) },
				modifier = Modifier.weight(1f)
			)
		}

		// Result card
		AnimatedContent(
			targetState = uiState.result,
			transitionSpec = {
				fadeIn(animationSpec = tween(Constants.ANIMATION_DURATION)) togetherWith
					fadeOut(animationSpec = tween(Constants.ANIMATION_DURATION))
			},
			label = "result_animation"
		) { result ->
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
					Text(
						text = stringResource(com.forestmusic.converter_android.R.string.result),
						style = MaterialTheme.typography.titleMedium,
						color = MaterialTheme.colorScheme.onPrimaryContainer
					)
					Spacer(modifier = Modifier.height(8.dp))
					Text(
						text = if (result.isEmpty()) "—" else result,
						style = MaterialTheme.typography.headlineLarge,
						fontWeight = FontWeight.Bold,
						color = MaterialTheme.colorScheme.onPrimaryContainer,
						textAlign = TextAlign.Center
					)
					if (result.isNotEmpty()) {
						Spacer(modifier = Modifier.height(4.dp))
						Text(
							text = context.getString(uiState.toUnit.displayNameRes),
							style = MaterialTheme.typography.bodyMedium,
							color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f)
						)
					}
				}
			}
		}
	}
}

/**
 * Dropdown menu for unit selection.
 */
@Composable
private fun UnitDropdown(
	label: String,
	selectedUnit: SpeedUnit,
	units: List<SpeedUnit>,
	onUnitSelected: (SpeedUnit) -> Unit,
	modifier: Modifier = Modifier
) {
	var expanded by remember { mutableStateOf(false) }
	val context = LocalContext.current

	Box(modifier = modifier) {
		OutlinedTextField(
			value = context.getString(selectedUnit.displayNameRes),
			onValueChange = {},
			readOnly = true,
			enabled = true,
			label = { Text(label) },
			trailingIcon = {
				Text(
					text = if (expanded) "▲" else "▼",
					style = MaterialTheme.typography.bodySmall,
					modifier = Modifier.clickable { expanded = !expanded }
				)
			},
			modifier = Modifier
				.fillMaxWidth()
				.clickable { expanded = !expanded }
		)

		DropdownMenu(
			expanded = expanded,
			onDismissRequest = { expanded = false }
		) {
			units.forEach { unit ->
				DropdownMenuItem(
					text = { 
						Text(context.getString(unit.displayNameRes))
					},
					onClick = {
						onUnitSelected(unit)
						expanded = false
					}
				)
			}
		}
	}
}

