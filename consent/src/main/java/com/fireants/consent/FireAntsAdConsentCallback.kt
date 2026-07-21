package com.fireants.consent

import android.app.Activity
import com.google.android.ump.FormError

interface FireAntsAdConsentCallback {
    fun getCurrentActivity(): Activity

    fun isDebug(): Boolean

    fun isUnderAgeAd(): Boolean

    fun onNotUsingAdConsent()

    fun onConsentSuccess(canPersonalized: Boolean)

    fun onConsentError(formError: FormError)

    fun onConsentStatus(consentStatus: Int)

    fun testDeviceID(): String {
        return ""
    }
    fun onRequestShowDialog()
}