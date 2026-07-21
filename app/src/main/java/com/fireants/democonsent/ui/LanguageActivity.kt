package com.fireants.democonsent.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.ump.FormError
import com.fireants.democonsent.databinding.ActivityLanguageBinding
import com.fireants.consent.FireAntsAdConsentCallback
import com.fireants.consent.FireAntsAdConsentManager


class LanguageActivity : AppCompatActivity(), FireAntsAdConsentCallback {

    private lateinit var binding: ActivityLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonShowDialog.setOnClickListener {
            FireAntsAdConsentManager.showDialogConsent(this)
        }
        binding.buttonRequestDialog.setOnClickListener {
            FireAntsAdConsentManager.showDialogConsent(this)
        }


    }

    override fun getCurrentActivity(): Activity {
        return this@LanguageActivity
    }

    override fun isDebug(): Boolean {
        return true
    }
    /**
     * https://developers.google.com/admob/android/targeting
     */

    override fun isUnderAgeAd(): Boolean {
        return false
    }

    override fun onNotUsingAdConsent() {
        Log.v("FireAntsAdConsentManager", "onNotUsingAdConsent")
    }

    override fun onConsentSuccess(canPersonalized: Boolean) {
        Log.v("FireAntsAdConsentManager", "onConsentSuccess")

    }

    override fun onConsentError(formError: FormError) {
        Log.v("FireAntsAdConsentManager", "formError  ${formError.message}")
    }

    override fun onConsentStatus(consentStatus: Int) {

    }

    override fun onRequestShowDialog() {

    }
}