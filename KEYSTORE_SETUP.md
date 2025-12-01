# Настройка Keystore для подписи приложения

## Важно!

**Текущая конфигурация использует debug keystore для тестирования.**
**Перед публикацией в RuStore необходимо создать production keystore!**

## Создание Production Keystore

### Шаг 1: Создайте директорию для keystore

```bash
mkdir -p keystore
```

### Шаг 2: Создайте production keystore

```bash
keytool -genkeypair -v -storetype PKCS12 -keystore keystore/release.keystore -alias release -keyalg RSA -keysize 2048 -validity 10000
```

Вам будет предложено ввести:
- **Пароль хранилища** (store password) - сохраните его в безопасном месте!
- **Пароль ключа** (key password) - может быть таким же, как пароль хранилища
- **Имя и фамилию** (CN)
- **Название организации** (OU)
- **Организацию** (O)
- **Город** (L)
- **Регион/Область** (ST)
- **Код страны** (C) - например, RU

### Шаг 3: Обновите build.gradle.kts

Замените конфигурацию signing в `app/build.gradle.kts`:

```kotlin
signingConfigs {
    create("release") {
        storeFile = file("${project.rootDir}/keystore/release.keystore")
        storePassword = System.getenv("KEYSTORE_PASSWORD") ?: ""
        keyAlias = "release"
        keyPassword = System.getenv("KEY_PASSWORD") ?: ""
    }
}
```

### Шаг 4: Настройте переменные окружения

**Вариант 1: Через gradle.properties (НЕ рекомендуется для production)**

Создайте файл `keystore.properties` в корне проекта:

```properties
KEYSTORE_PASSWORD=ваш_пароль_хранилища
KEY_PASSWORD=ваш_пароль_ключа
```

Добавьте в `.gitignore`:
```
keystore.properties
keystore/*.keystore
```

**Вариант 2: Через переменные окружения (Рекомендуется)**

Установите переменные окружения перед сборкой:

**Windows (PowerShell):**
```powershell
$env:KEYSTORE_PASSWORD="ваш_пароль"
$env:KEY_PASSWORD="ваш_пароль"
```

**Windows (CMD):**
```cmd
set KEYSTORE_PASSWORD=ваш_пароль
set KEY_PASSWORD=ваш_пароль
```

**Linux/Mac:**
```bash
export KEYSTORE_PASSWORD="ваш_пароль"
export KEY_PASSWORD="ваш_пароль"
```

### Шаг 5: Обновите build.gradle.kts для чтения из файла (опционально)

Если хотите использовать `keystore.properties`:

```kotlin
val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
if (keystorePropertiesFile.exists()) {
    keystoreProperties.load(FileInputStream(keystorePropertiesFile))
}

signingConfigs {
    create("release") {
        storeFile = file("${project.rootDir}/keystore/release.keystore")
        storePassword = keystoreProperties["KEYSTORE_PASSWORD"] as String? ?: ""
        keyAlias = "release"
        keyPassword = keystoreProperties["KEY_PASSWORD"] as String? ?: ""
    }
}
```

## Безопасность

⚠️ **ВАЖНО:**
- **НИКОГДА** не коммитьте keystore файлы в Git
- **НИКОГДА** не коммитьте пароли в Git
- Сохраните keystore и пароли в безопасном месте
- Создайте резервную копию keystore
- Если потеряете keystore, вы не сможете обновить приложение в RuStore!

## Проверка подписи

После сборки release APK проверьте подпись:

```bash
jarsigner -verify -verbose -certs app/build/outputs/apk/release/app-release.apk
```

## Обновление .gitignore

Убедитесь, что в `.gitignore` добавлено:

```
keystore/
keystore.properties
*.keystore
*.jks
```

