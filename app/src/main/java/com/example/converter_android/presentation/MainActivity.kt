package com.example.converter_android.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.converter_android.presentation.navigation.NavGraph
import com.example.converter_android.presentation.splash.SplashScreen
import com.example.converter_android.presentation.theme.ConverterTheme

/**
 * Main activity of the application.
 * 
 * This is the entry point of the Android application. It extends [ComponentActivity],
 * which is the base class for activities that use Jetpack Compose for UI rendering.
 * 
 * Responsibilities:
 * - Initialize the Compose UI when the activity is created
 * - Set up the application theme
 * - Initialize navigation controller for screen navigation
 * - Provide the root composable structure for the entire application
 * 
 * Architecture:
 * The activity follows a minimal setup pattern where:
 * 1. The activity only handles lifecycle and Compose setup
 * 2. All UI logic is delegated to composable functions
 * 3. Navigation is handled by [NavGraph]
 * 4. Theme is applied through [ConverterTheme]
 * 
 * Lifecycle:
 * - [onCreate] is called when the activity is first created
 * - The Compose UI is set up using [setContent]
 * - The navigation controller is created and passed to [NavGraph]
 * 
 * @see ComponentActivity
 * @see NavGraph
 * @see ConverterTheme
 */
class MainActivity : ComponentActivity() {
	/**
	 * Called when the activity is first created.
	 * 
	 * This method sets up the Compose UI and initializes the navigation system.
	 * It is called once during the activity's lifecycle, when the activity is
	 * first created (not when it's resumed from a saved state).
	 * 
	 * Setup process:
	 * 1. Call super.onCreate to ensure proper activity initialization
	 * 2. Set the Compose content using [setContent]
	 * 3. Apply the application theme using [ConverterTheme]
	 * 4. Create a Surface composable as the root container
	 * 5. Initialize the navigation controller
	 * 6. Set up the navigation graph
	 * 
	 * @param savedInstanceState Bundle containing the activity's previously saved state.
	 *                          This is null if the activity is being created for the first time.
	 */
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		// Set the Compose content as the activity's UI
		setContent {
			// Apply the application theme (supports light/dark mode)
			ConverterTheme {
				MainContent()
			}
		}
	}
}

/**
 * Main content composable that handles splash screen and navigation.
 * 
 * This composable manages the initial splash screen display and transitions
 * to the main navigation graph once the splash animation is complete.
 */
@Composable
private fun MainContent() {
	var showSplash by remember { mutableStateOf(true) }
	
	if (showSplash) {
		// Show splash screen
		SplashScreen(
			onSplashComplete = { showSplash = false }
		)
	} else {
		// Root surface container that fills the entire screen
		Surface(
			modifier = Modifier.fillMaxSize(),
			color = MaterialTheme.colorScheme.background
		) {
			// Create and remember the navigation controller
			// This controller manages navigation between screens
			val navController = rememberNavController()

			// Set up the navigation graph with all app screens
			NavGraph(navController = navController)
		}
	}
}

