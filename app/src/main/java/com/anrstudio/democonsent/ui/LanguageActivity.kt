package com.anrstudio.democonsent.ui

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.ump.FormError
import com.anrstudio.democonsent.databinding.ActivityLanguageBinding
import com.anrstudio.consent.ANRConsentCallback
import com.anrstudio.consent.ANRConsentManager


class LanguageActivity : AppCompatActivity(), ANRConsentCallback {

    private lateinit var binding: ActivityLanguageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLanguageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonShowDialog.setOnClickListener {
            ANRConsentManager.showDialogConsent(this)
        }
        binding.buttonRequestDialog.setOnClickListener {
            ANRConsentManager.showDialogConsent(this)
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
        Log.v("ANRConsentManager", "onNotUsingAdConsent")
    }

    override fun onConsentSuccess(canPersonalized: Boolean) {
        Log.v("ANRConsentManager", "onConsentSuccess")

    }

    override fun onConsentError(formError: FormError) {
        Log.v("ANRConsentManager", "formError  ${formError.message}")
    }

    override fun onConsentStatus(consentStatus: Int) {

    }

    override fun onRequestShowDialog() {

    }
}