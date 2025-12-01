package com.example.converter_android.core.converters

/**
 * Universal interface for unit conversion.
 * 
 * This interface provides a standardized contract for all unit conversion operations
 * across different measurement categories in the application. It follows the
 * Strategy pattern, allowing each category (weight, length, temperature, etc.) to
 * implement its own conversion logic while maintaining a consistent API.
 * 
 * The interface uses Kotlin generics with a type parameter constrained to [Enum],
 * ensuring type safety and allowing each category to define its own unit enum
 * (e.g., [WeightUnit], [LengthUnit], [TemperatureUnit]).
 * 
 * Benefits of this design:
 * - Type safety: Compile-time checking ensures units from different categories
 *   cannot be mixed in conversions
 * - Extensibility: New categories can be added by creating a new enum and
 *   implementing this interface
 * - Consistency: All converters follow the same pattern, making the codebase
 *   easier to understand and maintain
 * - Testability: The interface can be easily mocked for unit testing
 * 
 * Implementation pattern:
 * All implementations should convert through a base unit (e.g., kilogram for weight,
 * meter for length, Celsius for temperature) to ensure accuracy and consistency.
 * 
 * @param T The enum type representing units for a specific category.
 *          Must be an enum class that represents measurement units.
 * 
 * @example
 * // Example implementation for weight conversion
 * class ConvertWeightUseCase : UnitConverter<WeightUnit> {
 *     override fun convert(value: Double, from: WeightUnit, to: WeightUnit): Double {
 *         // Convert to base unit (kilogram), then to target unit
 *         val valueInKg = value * from.conversionFactorToKg
 *         return valueInKg / to.conversionFactorToKg
 *     }
 * }
 * 
 * @see com.example.converter_android.features.weight.domain.ConvertWeightUseCase
 * @see com.example.converter_android.features.length.domain.ConvertLengthUseCase
 * @see com.example.converter_android.features.temperature.domain.ConvertTemperatureUseCase
 */
interface UnitConverter<T : Enum<T>> {
	/**
	 * Converts a value from one unit to another within the same category.
	 * 
	 * This method performs the actual unit conversion calculation. The implementation
	 * should handle the conversion logic specific to the unit category, typically
	 * by converting to a base unit first, then to the target unit.
	 * 
	 * Conversion strategy:
	 * 1. Convert the input value from the source unit to a base unit
	 * 2. Convert from the base unit to the target unit
	 * 
	 * This two-step approach ensures accuracy and consistency, especially for
	 * conversions involving very large or very small numbers.
	 * 
	 * Edge cases to consider:
	 * - Same unit conversion: Should return the value unchanged (optimization)
	 * - Zero values: Should be handled correctly
	 * - Negative values: Should be supported if applicable to the unit type
	 * - Very large/small numbers: Should maintain precision
	 *
	 * @param value The numeric value to convert. Can be positive, negative, or zero.
	 * @param from The source unit from which to convert. Must be of the same enum type as [to].
	 * @param to The target unit to which to convert. Must be of the same enum type as [from].
	 * @return The converted value in the target unit. The precision should match
	 *         the precision of the input value, with appropriate rounding if necessary.
	 * 
	 * @throws IllegalArgumentException If the conversion cannot be performed
	 *                                  (should not occur with proper enum types)
	 * 
	 * @example
	 * val converter: UnitConverter<WeightUnit> = ConvertWeightUseCase()
	 * // Convert 1000 grams to kilograms
	 * val result = converter.convert(1000.0, WeightUnit.GRAM, WeightUnit.KILOGRAM) // 1.0
	 */
	fun convert(value: Double, from: T, to: T): Double
}
