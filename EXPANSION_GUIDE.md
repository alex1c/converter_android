# Руководство по расширению приложения

Этот документ содержит пошаговую инструкцию по добавлению нового типа конвертации (например, длины) в приложение.

## Пример: Добавление конвертации длины

### Шаг 1: Создать enum для единиц длины

Создайте файл: `app/src/main/java/com/example/converter_android/features/length/domain/models/LengthUnit.kt`

```kotlin
package com.example.converter_android.features.length.domain.models

enum class LengthUnit(val displayNameRes: Int, val conversionFactorToMeter: Double) {
    METER(com.example.converter_android.R.string.unit_meter, 1.0),
    KILOMETER(com.example.converter_android.R.string.unit_kilometer, 1000.0),
    CENTIMETER(com.example.converter_android.R.string.unit_centimeter, 0.01),
    MILLIMETER(com.example.converter_android.R.string.unit_millimeter, 0.001),
    MILE(com.example.converter_android.R.string.unit_mile, 1609.344),
    FOOT(com.example.converter_android.R.string.unit_foot, 0.3048),
    INCH(com.example.converter_android.R.string.unit_inch, 0.0254),
    YARD(com.example.converter_android.R.string.unit_yard, 0.9144)
}
```

### Шаг 2: Создать Use Case

Создайте файл: `app/src/main/java/com/example/converter_android/features/length/domain/convert_length_usecase.kt`

```kotlin
package com.example.converter_android.features.length.domain

import com.example.converter_android.core.converters.UnitConverter
import com.example.converter_android.features.length.domain.models.LengthUnit

class ConvertLengthUseCase : UnitConverter<LengthUnit> {
    override fun convert(value: Double, from: LengthUnit, to: LengthUnit): Double {
        if (from == to) {
            return value
        }

        // Convert to base unit (meters) first
        val valueInMeters = value * from.conversionFactorToMeter

        // Convert from base unit to target unit
        return valueInMeters / to.conversionFactorToMeter
    }
}
```

### Шаг 3: Создать Data слой

**LengthUnits.kt:**
```kotlin
package com.example.converter_android.features.length.data

import com.example.converter_android.features.length.domain.models.LengthUnit

object LengthUnits {
    fun getAllUnits(): List<LengthUnit> {
        return LengthUnit.values().toList()
    }

    fun getDefaultFromUnit(): LengthUnit = LengthUnit.METER

    fun getDefaultToUnit(): LengthUnit = LengthUnit.KILOMETER
}
```

**LengthConverter.kt:**
```kotlin
package com.example.converter_android.features.length.data

import com.example.converter_android.features.length.domain.ConvertLengthUseCase
import com.example.converter_android.features.length.domain.models.LengthUnit

class LengthConverter {
    private val convertLengthUseCase = ConvertLengthUseCase()

    fun convert(value: Double, from: LengthUnit, to: LengthUnit): Double {
        return convertLengthUseCase.convert(value, from, to)
    }
}
```

### Шаг 4: Создать UI слой

Скопируйте `WeightViewModel.kt` и `WeightScreen.kt`, замените все упоминания:
- `Weight` → `Length`
- `weight` → `length`
- `WeightUnit` → `LengthUnit`
- `WeightConverter` → `LengthConverter`
- `WeightUnits` → `LengthUnits`

### Шаг 5: Обновить навигацию

В `NavGraph.kt` добавьте:

```kotlin
sealed class Screen(val route: String) {
    object Weight : Screen("weight")
    object Length : Screen("length")  // Добавить
}

// В функции NavGraph:
composable(route = Screen.Length.route) {
    LengthScreen(viewModel = lengthViewModel)
}
```

В `MainActivity.kt` добавьте ViewModel:

```kotlin
val lengthViewModel: LengthViewModel = viewModel()
```

### Шаг 6: Добавить строки локализации

В `values/strings.xml`:
```xml
<string name="length_converter_title">Length Converter</string>
<string name="unit_meter">Meter</string>
<string name="unit_kilometer">Kilometer</string>
<string name="unit_centimeter">Centimeter</string>
<string name="unit_millimeter">Millimeter</string>
<string name="unit_mile">Mile</string>
<string name="unit_foot">Foot</string>
<string name="unit_inch">Inch</string>
<string name="unit_yard">Yard</string>
```

В `values-ru/strings.xml`:
```xml
<string name="length_converter_title">Конвертер длины</string>
<string name="unit_meter">Метр</string>
<string name="unit_kilometer">Километр</string>
<string name="unit_centimeter">Сантиметр</string>
<string name="unit_millimeter">Миллиметр</string>
<string name="unit_mile">Миля</string>
<string name="unit_foot">Фут</string>
<string name="unit_inch">Дюйм</string>
<string name="unit_yard">Ярд</string>
```

## Общие принципы

1. **Базовая единица**: Всегда выбирайте метрическую единицу как базовую (например, метр для длины, килограмм для веса)
2. **Коэффициенты**: Все коэффициенты конвертации должны быть относительно базовой единицы
3. **Структура**: Следуйте той же структуре папок, что и для weight feature
4. **Именование**: Используйте единообразное именование (CategoryUnit, ConvertCategoryUseCase, CategoryConverter, CategoryViewModel, CategoryScreen)

## Проверка

После добавления нового типа конвертации:

1. ✅ Проверьте компиляцию проекта
2. ✅ Убедитесь, что все строки локализованы
3. ✅ Протестируйте конвертацию между всеми единицами
4. ✅ Проверьте UI на разных размерах экрана
5. ✅ Убедитесь, что анимации работают корректно

