package com.forestmusic.converter_android.presentation.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.forestmusic.converter_android.core.utils.Constants
import kotlinx.coroutines.delay

/**
 * Splash screen displayed when the application starts.
 * 
 * This composable creates an animated splash screen that appears when the app
 * is first launched. It features:
 * - Animated logo/icon
 * - App name
 * - Smooth fade-in and scale animations
 * - Gradient background matching the app theme
 * 
 * The splash screen automatically transitions to the main content after
 * a brief delay, providing a polished first impression.
 * 
 * @param onSplashComplete Callback function called when the splash animation
 *                        is complete and the app is ready to show the main content.
 *                        Typically used to navigate to the main screen.
 */
@Composable
fun SplashScreen(
	onSplashComplete: () -> Unit,
	modifier: Modifier = Modifier
) {
	var startAnimation by remember { mutableStateOf(false) }
	
	// Animate alpha and scale
	val alphaAnim = animateFloatAsState(
		targetValue = if (startAnimation) 1f else 0f,
		animationSpec = tween(
			durationMillis = Constants.ANIMATION_DURATION * 2,
			delayMillis = 0
		),
		label = "splash_alpha"
	)
	
	val scaleAnim = animateFloatAsState(
		targetValue = if (startAnimation) 1f else 0.5f,
		animationSpec = tween(
			durationMillis = Constants.ANIMATION_DURATION * 2,
			delayMillis = 0
		),
		label = "splash_scale"
	)
	
	// Start animation and navigate after delay
	LaunchedEffect(key1 = true) {
		startAnimation = true
		delay((Constants.ANIMATION_DURATION * 3).toLong()) // Show for 900ms
		onSplashComplete()
	}
	
	// Gradient background colors
	val gradientColors = listOf(
		MaterialTheme.colorScheme.primaryContainer,
		MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
		MaterialTheme.colorScheme.background
	)
	
	Box(
		modifier = modifier
			.fillMaxSize()
			.background(
				brush = Brush.verticalGradient(
					colors = gradientColors
				)
			),
		contentAlignment = Alignment.Center
	) {
		Column(
			horizontalAlignment = Alignment.CenterHorizontally,
			modifier = Modifier
		) {
			// App icon/logo with animation
			Icon(
				imageVector = Icons.Default.SwapHoriz,
				contentDescription = "App Logo",
				modifier = Modifier
					.size((120 * scaleAnim.value).dp)
					.then(
						Modifier.alpha(alphaAnim.value)
					),
				tint = MaterialTheme.colorScheme.onPrimaryContainer
			)
			
			Spacer(modifier = Modifier.height(Constants.SPACING_LG.dp))
			
			// App name with animation
			Text(
				text = "Универсальный\nконвертер",
				style = MaterialTheme.typography.headlineLarge.copy(
					fontSize = 28.sp
				),
				fontWeight = FontWeight.Bold,
				color = MaterialTheme.colorScheme.onPrimaryContainer,
				textAlign = TextAlign.Center,
				modifier = Modifier.alpha(alphaAnim.value)
			)
		}
	}
}

