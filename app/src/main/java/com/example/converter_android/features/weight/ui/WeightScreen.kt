package com.example.converter_android.features.weight.ui

import android.content.ClipData
import android.content.ClipboardManager
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch
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
import com.example.converter_android.features.weight.domain.models.WeightUnit

/**
 * Main screen for weight conversion.
 * 
 * This screen provides a user interface for converting weight values between
 * different units. It includes input field, unit selection dropdowns, swap
 * button, and result display with copy functionality.
 */
@Composable
fun WeightScreen(
	viewModel: WeightViewModel,
	onBackClick: () -> Unit = {},
	modifier: Modifier = Modifier
) {
	val uiState by viewModel.uiState.collectAsStateWithLifecycle()
	val context = LocalContext.current
	val snackbarHostState = remember { SnackbarHostState() }
	val coroutineScope = rememberCoroutineScope()

	// Show error message if present
	uiState.errorMessage?.let { error ->
		LaunchedEffect(error) {
			snackbarHostState.showSnackbar(error)
		}
	}

	Box(modifier = modifier.fillMaxSize()) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(Constants.SPACING_MD.dp),
			verticalArrangement = Arrangement.spacedBy(Constants.SPACING_LG.dp)
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
					text = stringResource(com.forestmusic.converter_android.R.string.weight_converter_title),
					style = MaterialTheme.typography.headlineMedium,
					fontWeight = FontWeight.Bold,
					modifier = Modifier
						.weight(1f)
						.padding(vertical = Constants.SPACING_SM.dp),
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
				singleLine = true,
				isError = uiState.errorMessage != null
			)

			// Show error message below input if present
			uiState.errorMessage?.let { error ->
				Text(
					text = error,
					color = MaterialTheme.colorScheme.error,
					style = MaterialTheme.typography.bodySmall,
					modifier = Modifier.padding(start = Constants.SPACING_MD.dp, top = (-Constants.SPACING_SM).dp)
				)
			}

			// Unit selection row
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.spacedBy(Constants.SPACING_MD.dp),
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

				// Swap button with icon
				IconButton(
					onClick = { viewModel.swapUnits() },
					modifier = Modifier.size(48.dp)
				) {
					Icon(
						imageVector = Icons.Default.SwapHoriz,
						contentDescription = stringResource(com.forestmusic.converter_android.R.string.swap_units),
						tint = MaterialTheme.colorScheme.primary
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

			// Result card with improved animations
			AnimatedContent(
				targetState = uiState.result,
				transitionSpec = {
					(
						fadeIn(animationSpec = spring()) + 
						scaleIn(initialScale = 0.9f, animationSpec = spring())
					) togetherWith (
						fadeOut(animationSpec = tween(Constants.ANIMATION_DURATION / 2)) +
						scaleOut(targetScale = 0.9f, animationSpec = tween(Constants.ANIMATION_DURATION / 2))
					)
				},
				label = "result_animation"
			) { result ->
				Card(
					modifier = Modifier.fillMaxWidth(),
					elevation = CardDefaults.cardElevation(
						defaultElevation = 2.dp,
						pressedElevation = 8.dp
					),
					colors = CardDefaults.cardColors(
						containerColor = MaterialTheme.colorScheme.primaryContainer
					)
				) {
					Column(
						modifier = Modifier
							.fillMaxWidth()
							.padding(Constants.SPACING_LG.dp),
						horizontalAlignment = Alignment.CenterHorizontally
					) {
						Row(
							modifier = Modifier.fillMaxWidth(),
							horizontalArrangement = Arrangement.SpaceBetween,
							verticalAlignment = Alignment.CenterVertically
						) {
							Text(
								text = stringResource(com.forestmusic.converter_android.R.string.result),
								style = MaterialTheme.typography.titleMedium,
								color = MaterialTheme.colorScheme.onPrimaryContainer
							)
							// Copy button
							if (result.isNotEmpty() && result != "—") {
								IconButton(
									onClick = {
										val clipboard = context.getSystemService(android.content.Context.CLIPBOARD_SERVICE) as ClipboardManager
										val clip = ClipData.newPlainText("result", result)
										clipboard.setPrimaryClip(clip)
										// Show snackbar
										val message = context.getString(com.forestmusic.converter_android.R.string.copied)
										coroutineScope.launch {
											snackbarHostState.showSnackbar(message)
										}
									}
								) {
									Icon(
										imageVector = Icons.Default.ContentCopy,
										contentDescription = stringResource(com.forestmusic.converter_android.R.string.copy_result),
										tint = MaterialTheme.colorScheme.onPrimaryContainer
									)
								}
							}
						}
						Spacer(modifier = Modifier.height(Constants.SPACING_SM.dp))
						Text(
							text = if (result.isEmpty()) "—" else result,
							style = MaterialTheme.typography.headlineLarge,
							fontWeight = FontWeight.Bold,
							color = MaterialTheme.colorScheme.onPrimaryContainer,
							textAlign = TextAlign.Center
						)
						if (result.isNotEmpty() && result != "—") {
							Spacer(modifier = Modifier.height(Constants.SPACING_XS.dp))
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

		// Snackbar for copy confirmation
		SnackbarHost(
			hostState = snackbarHostState,
			modifier = Modifier.align(Alignment.BottomCenter)
		)
	}
}

/**
 * Dropdown menu for unit selection.
 */
@Composable
private fun UnitDropdown(
	label: String,
	selectedUnit: WeightUnit,
	units: List<WeightUnit>,
	onUnitSelected: (WeightUnit) -> Unit,
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
