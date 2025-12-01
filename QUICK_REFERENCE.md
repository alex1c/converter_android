# Быстрый справочник проекта

## 📋 Текущее состояние

### Реализованные категории

| Категория | Единиц | Статус |
|-----------|--------|--------|
| Вес | 6 | ✅ Готово |
| Длина | 9 | ✅ Готово |
| Объём | 10 | ✅ Готово |

**Всего единиц измерения:** 25

### Структура файлов

```
features/[category]/
├── data/
│   ├── [Category]Units.kt
│   └── [Category]Converter.kt
├── domain/
│   ├── models/[Category]Unit.kt
│   └── convert_[category]_usecase.kt
└── ui/
    ├── [Category]Screen.kt
    └── [Category]ViewModel.kt
```

## 🔧 Быстрые команды

### Сборка
```bash
./gradlew assembleDebug
```

### Тестирование
```bash
./gradlew test
```

### Очистка
```bash
./gradlew clean
```

## 📝 Шаблон для новой категории

### 1. Domain слой

**Enum единиц:**
```kotlin
enum class [Category]Unit(
    val displayNameRes: Int,
    val conversionFactorToBase: Double
) {
    BASE_UNIT(R.string.unit_base, 1.0),
    // ... другие единицы
}
```

**Use Case:**
```kotlin
class Convert[Category]UseCase : UnitConverter<[Category]Unit> {
    override fun convert(value: Double, from: [Category]Unit, to: [Category]Unit): Double {
        if (from == to) return value
        val valueInBase = value * from.conversionFactorToBase
        return valueInBase / to.conversionFactorToBase
    }
}
```

### 2. Data слой

**Units:**
```kotlin
object [Category]Units {
    fun getAllUnits(): List<[Category]Unit> = [Category]Unit.values().toList()
    fun getDefaultFromUnit(): [Category]Unit = [Category]Unit.BASE_UNIT
    fun getDefaultToUnit(): [Category]Unit = [Category]Unit.OTHER_UNIT
}
```

**Converter:**
```kotlin
class [Category]Converter {
    private val useCase = Convert[Category]UseCase()
    fun convert(value: Double, from: [Category]Unit, to: [Category]Unit): Double {
        return useCase.convert(value, from, to)
    }
}
```

### 3. UI слой

**ViewModel:** Скопировать из `LengthViewModel.kt` и заменить `Length` → `[Category]`

**Screen:** Скопировать из `LengthScreen.kt` и заменить `Length` → `[Category]`

### 4. Интеграция

**CategoriesData.kt:**
```kotlin
ConverterCategory(
    id = "[category]",
    title = "Название",
    icon = Icons.Default.IconName
)
```

**NavGraph.kt:**
```kotlin
// В when добавить:
"[category]" -> navController.navigate(Screen.[Category].route)

// Добавить composable:
composable(route = Screen.[Category].route) {
    val viewModel: [Category]ViewModel = viewModel()
    [Category]Screen(viewModel = viewModel, onBackClick = { navController.popBackStack() })
}

// В Screen добавить:
object [Category] : Screen("[category]")
```

**strings.xml:**
```xml
<string name="[category]_converter_title">Название конвертера</string>
<string name="unit_[unit]">Название единицы</string>
```

## 🎯 Приоритеты разработки

1. **Высокий:** Температура, Темная тема
2. **Средний:** Площадь, Скорость, Настройки
3. **Низкий:** Время, Энергия, Давление

## 📚 Документация

- **[DOCUMENTATION.md](DOCUMENTATION.md)** — Полная документация
- **[ROADMAP.md](ROADMAP.md)** — План развития
- **[EXPANSION_GUIDE.md](EXPANSION_GUIDE.md)** — Руководство по расширению

## 🔗 Полезные ссылки

- Core интерфейс: `core/converters/UnitConverter.kt`
- Константы: `core/utils/Constants.kt`
- Навигация: `presentation/navigation/NavGraph.kt`
- Категории: `presentation/categories/CategoriesData.kt`

---

**Версия:** 1.0

