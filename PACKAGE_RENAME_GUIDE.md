# Руководство по переименованию пакетов

## Текущая ситуация

- **applicationId**: `com.forestmusic.converter_android` ✅ (изменено)
- **namespace**: `com.forestmusic.converter_android` ✅ (изменено)
- **Package name в коде**: `com.example.converter_android` ⚠️ (нужно изменить)

## Важно

**ApplicationId и namespace уже изменены на `com.forestmusic.converter_android`.**

Package name в исходном коде (`com.example.converter_android`) можно оставить как есть для внутренней структуры, так как это не влияет на публикацию в RuStore. Однако для полной консистентности рекомендуется переименовать.

## Автоматическое переименование (Android Studio)

### Способ 1: Refactor → Rename Package

1. Откройте Android Studio
2. В Project view найдите папку `com.example.converter_android`
3. Правый клик → Refactor → Rename Package
4. Введите: `com.forestmusic.converter_android`
5. Выберите опции:
   - ✅ Search in comments and strings
   - ✅ Search for text occurrences
6. Нажмите Refactor

### Способ 2: Постепенное переименование

1. Переименуйте корневую папку пакета:
   - `com/example/converter_android` → `com/forestmusic/converter_android`
2. Android Studio автоматически обновит все импорты

## Ручное переименование (если нужно)

Если автоматическое переименование не работает, выполните:

1. **Переместите файлы:**
   ```bash
   # Создайте новую структуру
   mkdir -p app/src/main/java/com/forestmusic/converter_android
   
   # Переместите все файлы
   mv app/src/main/java/com/example/converter_android/* app/src/main/java/com/forestmusic/converter_android/
   ```

2. **Обновите все package declarations:**
   ```bash
   # Найдите все файлы с package declaration
   find app/src -name "*.kt" -exec sed -i 's/package com\.example\.converter_android/package com.forestmusic.converter_android/g' {} \;
   ```

3. **Обновите все импорты:**
   ```bash
   # Обновите импорты
   find app/src -name "*.kt" -exec sed -i 's/import com\.example\.converter_android/import com.forestmusic.converter_android/g' {} \;
   ```

4. **Обновите R классы:**
   - Все ссылки на `com.example.converter_android.R` → `com.forestmusic.converter_android.R`

## Проверка

После переименования проверьте:

1. ✅ Проект компилируется без ошибок
2. ✅ Все импорты корректны
3. ✅ Все ссылки на R класс обновлены
4. ✅ Тесты проходят

## Примечание

Если вы не хотите переименовывать package name в коде, это не критично для публикации в RuStore. Главное - applicationId должен быть правильным (что уже сделано).

