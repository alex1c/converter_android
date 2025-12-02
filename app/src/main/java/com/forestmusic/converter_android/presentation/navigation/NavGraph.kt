package com.forestmusic.converter_android.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.forestmusic.converter_android.features.angle.ui.AngleScreen
import com.forestmusic.converter_android.features.angle.ui.AngleViewModel
import com.forestmusic.converter_android.features.area.ui.AreaScreen
import com.forestmusic.converter_android.features.area.ui.AreaViewModel
import com.forestmusic.converter_android.features.density.ui.DensityScreen
import com.forestmusic.converter_android.features.density.ui.DensityViewModel
import com.forestmusic.converter_android.features.energy.ui.EnergyScreen
import com.forestmusic.converter_android.features.energy.ui.EnergyViewModel
import com.forestmusic.converter_android.features.power.ui.PowerScreen
import com.forestmusic.converter_android.features.power.ui.PowerViewModel
import com.forestmusic.converter_android.features.torque.ui.TorqueScreen
import com.forestmusic.converter_android.features.torque.ui.TorqueViewModel
import com.forestmusic.converter_android.features.electricity.ui.ElectricityScreen
import com.forestmusic.converter_android.features.electricity.ui.ElectricityViewModel
import com.forestmusic.converter_android.features.length.ui.LengthScreen
import com.forestmusic.converter_android.features.length.ui.LengthViewModel
import com.forestmusic.converter_android.features.pressure.ui.PressureScreen
import com.forestmusic.converter_android.features.pressure.ui.PressureViewModel
import com.forestmusic.converter_android.features.speed.ui.SpeedScreen
import com.forestmusic.converter_android.features.speed.ui.SpeedViewModel
import com.forestmusic.converter_android.features.temperature.ui.TemperatureScreen
import com.forestmusic.converter_android.features.temperature.ui.TemperatureViewModel
import com.forestmusic.converter_android.features.time.ui.TimeScreen
import com.forestmusic.converter_android.features.time.ui.TimeViewModel
import com.forestmusic.converter_android.features.volume.ui.VolumeScreen
import com.forestmusic.converter_android.features.volume.ui.VolumeViewModel
import com.forestmusic.converter_android.features.weight.ui.WeightScreen
import com.forestmusic.converter_android.features.weight.ui.WeightViewModel
import com.forestmusic.converter_android.presentation.categories.CategoriesScreen

/**
 * Navigation graph for the application.
 * 
 * This composable function sets up the complete navigation structure for the
 * application using Jetpack Compose Navigation. It defines all possible routes
 * and their corresponding screens, handling navigation between the categories
 * screen and individual converter screens.
 * 
 * Architecture:
 * - Uses [NavHost] as the root navigation container
 * - Each screen is defined as a [composable] route
 * - ViewModels are created using [viewModel] factory for each screen
 * - Back navigation is handled through [NavHostController.popBackStack]
 * 
 * Navigation flow:
 * 1. App starts at [Screen.Categories] (home screen)
 * 2. User selects a category, triggering navigation to the converter screen
 * 3. User can navigate back using the back button
 * 4. Each converter screen is independent and can be navigated to directly
 * 
 * Screen management:
 * - Each screen has its own ViewModel instance (created via [viewModel])
 * - ViewModels are scoped to the navigation lifecycle
 * - State is preserved when navigating between screens
 * 
 * Adding new screens:
 * 1. Add a new [Screen] object in the sealed class
 * 2. Add a [composable] block for the new route
 * 3. Create the ViewModel instance
 * 4. Add navigation case in the [when] statement
 * 5. Add the category to [CategoriesData]
 * 
 * @param navController The [NavHostController] that manages navigation between screens.
 *                     Created in [MainActivity] and passed down to this composable.
 * 
 * @see Screen
 * @see CategoriesScreen
 * @see NavHostController
 */
@Composable
fun NavGraph(
	navController: NavHostController
) {
	// Set up the navigation host with categories as the starting screen
	NavHost(
		navController = navController,
		startDestination = Screen.Categories.route
	) {
		// Home screen: Categories grid
		composable(route = Screen.Categories.route) {
			CategoriesScreen(
				onCategorySelected = { categoryId ->
					// Navigate to the selected category's converter screen
					when (categoryId) {
						"weight" -> navController.navigate(Screen.Weight.route)
						"length" -> navController.navigate(Screen.Length.route)
						"volume" -> navController.navigate(Screen.Volume.route)
						"temperature" -> navController.navigate(Screen.Temperature.route)
						"speed" -> navController.navigate(Screen.Speed.route)
						"area" -> navController.navigate(Screen.Area.route)
						"pressure" -> navController.navigate(Screen.Pressure.route)
						"energy" -> navController.navigate(Screen.Energy.route)
						"time" -> navController.navigate(Screen.Time.route)
						"angle" -> navController.navigate(Screen.Angle.route)
						"density" -> navController.navigate(Screen.Density.route)
						"power" -> navController.navigate(Screen.Power.route)
						"torque" -> navController.navigate(Screen.Torque.route)
						"electricity" -> navController.navigate(Screen.Electricity.route)
					}
				}
			)
		}

		// Weight converter screen
		composable(route = Screen.Weight.route) {
			// Create ViewModel instance scoped to this navigation route
			val weightViewModel: WeightViewModel = viewModel()
			WeightScreen(
				viewModel = weightViewModel,
				onBackClick = { navController.popBackStack() } // Navigate back to categories
			)
		}

		composable(route = Screen.Length.route) {
			val lengthViewModel: LengthViewModel = viewModel()
			LengthScreen(
				viewModel = lengthViewModel,
				onBackClick = { navController.popBackStack() }
			)
		}

		composable(route = Screen.Volume.route) {
			val volumeViewModel: VolumeViewModel = viewModel()
			VolumeScreen(
				viewModel = volumeViewModel,
				onBackClick = { navController.popBackStack() }
			)
		}

		composable(route = Screen.Temperature.route) {
			val temperatureViewModel: TemperatureViewModel = viewModel()
			TemperatureScreen(
				viewModel = temperatureViewModel,
				onBackClick = { navController.popBackStack() }
			)
		}

		composable(route = Screen.Speed.route) {
			val speedViewModel: SpeedViewModel = viewModel()
			SpeedScreen(
				viewModel = speedViewModel,
				onBackClick = { navController.popBackStack() }
			)
		}

		composable(route = Screen.Area.route) {
			val areaViewModel: AreaViewModel = viewModel()
			AreaScreen(
				viewModel = areaViewModel,
				onBackClick = { navController.popBackStack() }
			)
		}

		composable(route = Screen.Pressure.route) {
			val pressureViewModel: PressureViewModel = viewModel()
			PressureScreen(
				viewModel = pressureViewModel,
				onBackClick = { navController.popBackStack() }
			)
		}

		composable(route = Screen.Energy.route) {
			val energyViewModel: EnergyViewModel = viewModel()
			EnergyScreen(
				viewModel = energyViewModel,
				onBackClick = { navController.popBackStack() }
			)
		}

		composable(route = Screen.Time.route) {
			val timeViewModel: TimeViewModel = viewModel()
			TimeScreen(
				viewModel = timeViewModel,
				onBackClick = { navController.popBackStack() }
			)
		}

		composable(route = Screen.Angle.route) {
			val angleViewModel: AngleViewModel = viewModel()
			AngleScreen(
				viewModel = angleViewModel,
				onBackClick = { navController.popBackStack() }
			)
		}

		composable(route = Screen.Density.route) {
			val densityViewModel: DensityViewModel = viewModel()
			DensityScreen(
				viewModel = densityViewModel,
				onBackClick = { navController.popBackStack() }
			)
		}

		composable(route = Screen.Power.route) {
			val powerViewModel: PowerViewModel = viewModel()
			PowerScreen(
				viewModel = powerViewModel,
				onBackClick = { navController.popBackStack() }
			)
		}

		composable(route = Screen.Torque.route) {
			val torqueViewModel: TorqueViewModel = viewModel()
			TorqueScreen(
				viewModel = torqueViewModel,
				onBackClick = { navController.popBackStack() }
			)
		}

		composable(route = Screen.Electricity.route) {
			val electricityViewModel: ElectricityViewModel = viewModel()
			ElectricityScreen(
				viewModel = electricityViewModel,
				onBackClick = { navController.popBackStack() }
			)
		}
	}
}

/**
 * Sealed class representing all screens in the application.
 * 
 * This sealed class provides type-safe navigation routes for the entire application.
 * Each screen is represented as an object with a unique route string. Using a sealed
 * class ensures that:
 * - All routes are defined in one place
 * - Route strings are type-safe and cannot be misspelled
 * - The compiler can check exhaustiveness in when expressions
 * - Adding new screens requires updating this class
 * 
 * Design pattern:
 * - Sealed class prevents inheritance outside this file
 * - Each screen is an object (singleton) with a route property
 * - Route strings are lowercase and match category IDs in [CategoriesData]
 * 
 * Usage:
 * - Routes are used in [NavGraph] to define navigation destinations
 * - Category IDs in [CategoriesData] must match route strings
 * - Navigation is performed using [NavHostController.navigate] with [route]
 * 
 * Route naming convention:
 * - Use lowercase letters
 * - Use singular form (e.g., "weight" not "weights")
 * - Match the feature module name
 * - Be descriptive and clear
 * 
 * @param route The unique string identifier for this screen's route.
 *             Used in navigation and must match the category ID in [CategoriesData].
 */
sealed class Screen(val route: String) {
	/**
	 * Categories/home screen route.
	 * This is the starting screen of the application.
	 */
	object Categories : Screen("categories")
	
	/**
	 * Weight converter screen route.
	 */
	object Weight : Screen("weight")
	
	/**
	 * Length converter screen route.
	 */
	object Length : Screen("length")
	
	/**
	 * Volume converter screen route.
	 */
	object Volume : Screen("volume")
	
	/**
	 * Temperature converter screen route.
	 */
	object Temperature : Screen("temperature")
	
	/**
	 * Speed converter screen route.
	 */
	object Speed : Screen("speed")
	
	/**
	 * Area converter screen route.
	 */
	object Area : Screen("area")
	
	/**
	 * Pressure converter screen route.
	 */
	object Pressure : Screen("pressure")
	
	/**
	 * Energy converter screen route.
	 */
	object Energy : Screen("energy")
	
	/**
	 * Time converter screen route.
	 */
	object Time : Screen("time")
	
	/**
	 * Angle converter screen route.
	 */
	object Angle : Screen("angle")
	
	/**
	 * Density converter screen route.
	 */
	object Density : Screen("density")
	
	/**
	 * Power converter screen route.
	 */
	object Power : Screen("power")
	
	/**
	 * Torque converter screen route.
	 */
	object Torque : Screen("torque")
	
	/**
	 * Electricity converter and calculator screen route.
	 * This screen includes both unit conversion and electrical calculators.
	 */
	object Electricity : Screen("electricity")
	
	// Future screens can be added here:
	// Example:
	// object Frequency : Screen("frequency")
}

