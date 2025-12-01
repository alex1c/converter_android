# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Keep line number information for debugging stack traces
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

# Keep all ViewModels and their state classes
-keep class * extends androidx.lifecycle.ViewModel { *; }
-keep class * extends androidx.lifecycle.ViewModel$* { *; }

# Keep data classes used in StateFlow
-keep class com.forestmusic.converter_android.features.**.*UiState { *; }

# Keep enum classes for units
-keep class com.forestmusic.converter_android.features.**.*Unit { *; }
-keepclassmembers enum com.forestmusic.converter_android.features.**.*Unit {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep interfaces
-keep interface com.forestmusic.converter_android.core.converters.UnitConverter { *; }

# Keep use cases
-keep class com.forestmusic.converter_android.features.**.*UseCase { *; }

# Keep converters
-keep class com.forestmusic.converter_android.features.**.*Converter { *; }

# Keep utility classes
-keep class com.forestmusic.converter_android.core.utils.** { *; }

# Keep data layer objects
-keep class com.forestmusic.converter_android.features.**.*Units { *; }

# Keep Compose-related classes
-keep class androidx.compose.** { *; }
-keep class kotlin.coroutines.** { *; }

# Keep Kotlin metadata
-keepattributes *Annotation*
-keepattributes Signature
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes EnclosingMethod

# Keep Parcelable implementations
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}

# Remove logging in release builds
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}