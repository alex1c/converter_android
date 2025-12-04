import java.util.Properties
import java.io.FileInputStream

plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
}

// Load keystore properties
val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
if (keystorePropertiesFile.exists()) {
	FileInputStream(keystorePropertiesFile).use { input ->
		keystoreProperties.load(input)
	}
}

android {
	namespace = "com.forestmusic.converter_android"
	compileSdk = 36

	defaultConfig {
		applicationId = "com.forestmusic.converter_android"
		minSdk = 24
		targetSdk = 36
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	signingConfigs {
		val keystoreFile = file("${project.rootDir}/keystore/release.keystore")
		if (keystoreFile.exists()) {
			create("release") {
				// Keystore configuration for release builds
				storeFile = keystoreFile
				storePassword = keystoreProperties["KEYSTORE_PASSWORD"] as String? ?: ""
				keyAlias = "release"
				keyPassword = keystoreProperties["KEY_PASSWORD"] as String? ?: ""
			}
		}
	}

	buildTypes {
		release {
			isMinifyEnabled = true
			isShrinkResources = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
			// Only apply signing config if keystore exists
			val keystoreFile = file("${project.rootDir}/keystore/release.keystore")
			if (keystoreFile.exists()) {
				signingConfig = signingConfigs.getByName("release")
			}
		}
		debug {
			applicationIdSuffix = ".debug"
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_17
		targetCompatibility = JavaVersion.VERSION_17
	}
	kotlinOptions {
		jvmTarget = "17"
	}
	buildFeatures {
		compose = true
	}
	composeOptions {
		kotlinCompilerExtensionVersion = "1.5.14"
	}
}

dependencies {
	// Core
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.material)

	// Compose
	implementation(platform(libs.compose.bom))
	implementation(libs.compose.ui)
	implementation(libs.compose.ui.graphics)
	implementation(libs.compose.ui.tooling.preview)
	implementation(libs.compose.material3)
	implementation(libs.compose.activity)
	implementation("androidx.compose.material:material-icons-extended")
	debugImplementation(libs.compose.ui.tooling)
	debugImplementation(libs.compose.ui.test.manifest)

	// Lifecycle
	implementation(libs.lifecycle.runtime.ktx)
	implementation(libs.lifecycle.viewmodel.compose)

	// Navigation
	implementation(libs.navigation.compose)

	// Testing
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
}