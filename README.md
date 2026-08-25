# Fire Ants Consent

Standalone Android library for Google UMP consent flow and in-app update helpers.

## Install

Add JitPack to your root repository list:

```gradle
maven { url 'https://jitpack.io' }
```

Add the dependency:

```gradle
dependencies {
    implementation 'com.github.<your-org>:fireants-consent:version'
}
```

## Update API

Use `AnrStudioAppUpdateConsentManager` for in-app updates.

```kotlin
if (isShowDialogUpdate) {
    AnrStudioAppUpdateConsentManager(this, REQUEST_CODE, object : AnrStudioAppUpdateConsentCallback {
        override fun updateAvailableListener(updateAvailability: AppUpdateInfo): Int {
            return if (updateAvailability.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE) {
                type
            } else {
                AppUpdateType.FLEXIBLE
            }
        }
    }).checkUpdateAvailable()
}
```

## Consent API

Load consent info and decide later whether to show the dialog:

```kotlin
ANRConsentManager.loadAndShowConsent(false, this)
```

Show consent after a previous load:

```kotlin
ANRConsentManager.showDialogConsent(this)
```

Load and show immediately:

```kotlin
ANRConsentManager.loadAndShowConsent(true, this)
```

Reset consent state:

```kotlin
ANRConsentManager.resetConsentDialog()
```
