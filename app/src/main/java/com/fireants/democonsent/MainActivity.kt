package com.fireants.democonsent

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.android.ump.FormError
import com.fireants.democonsent.databinding.ActivityMainBinding
import com.fireants.consent.FireAntsAdConsentCallback
import com.fireants.consent.FireAntsAdConsentManager
import com.fireants.consent.FireAntsAppUpdateConsentCallback
import com.fireants.consent.FireAntsAppUpdateConsentManager


class MainActivity : AppCompatActivity(), FireAntsAdConsentCallback {

    private val isShowDialogUpdate: Boolean = true
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonCheckGgFlex.setOnClickListener {
            updateWithType(AppUpdateType.FLEXIBLE, isShowDialogUpdate)
        }
        binding.buttonCheckUpdateGgImmediate.setOnClickListener {
            updateWithType(AppUpdateType.IMMEDIATE, isShowDialogUpdate)
        }
        binding.buttonLoadConsent.setOnClickListener {
            FireAntsAdConsentManager.loadAndShowConsent(false,this)
            binding.buttonShowConsent.isEnabled = false
            binding.progressLoading.visibility = View.VISIBLE
        }
        binding.buttonShowConsent.setOnClickListener {
            FireAntsAdConsentManager.showDialogConsent(this)
//            startActivity(Intent(this, LanguageActivity::class.java))
        }
        binding.buttonLoadAndShowConsent.setOnClickListener {
            binding.progressLoading.visibility = View.VISIBLE
            FireAntsAdConsentManager.loadAndShowConsent(true,this)
        }
        binding.buttonRestartConsent.setOnClickListener {
            FireAntsAdConsentManager.resetConsentDialog()
        }
    }

    private fun updateWithType(type: Int, isShowDialogUpdate: Boolean) {
        if (isShowDialogUpdate) FireAntsAppUpdateConsentManager(this, 100, object : FireAntsAppUpdateConsentCallback {
            override fun updateAvailableListener(updateAvailability: AppUpdateInfo): Int {
                when (updateAvailability.updateAvailability()) {
                    UpdateAvailability.UPDATE_AVAILABLE -> {
                        // Need to show Dialog check option then return type
                        return type
                    }

                }
                return AppUpdateType.FLEXIBLE
            }

        }).checkUpdateAvailable()
    }

    override fun getCurrentActivity(): Activity {
        return this@MainActivity
    }

    override fun isDebug(): Boolean {
        return true
    }

    override fun isUnderAgeAd(): Boolean {
        return false
    }

    override fun onNotUsingAdConsent() {
        Log.v("FireAntsAdConsentManager", "onNotUsingAdConsent")
        binding.progressLoading.visibility = View.GONE
        binding.buttonShowConsent.isEnabled = false
    }


    override fun onConsentSuccess(canPersonalized: Boolean) {
        if (canPersonalized){
            Log.v("FireAntsAdConsentManager", "onConsentSuccess true")
        }else{
            Log.v("FireAntsAdConsentManager", "onConsentSuccess false")
        }
        binding.progressLoading.visibility = View.GONE
        binding.buttonShowConsent.isEnabled = false

    }

    override fun onConsentError(formError: FormError) {
        binding.progressLoading.visibility = View.GONE
        Log.v("FireAntsAdConsentManager", "formError  ${formError.message}")
    }

    override fun onConsentStatus(consentStatus: Int) {
        Log.v("FireAntsAdConsentManager", "consentStatus  ${consentStatus}")

        binding.buttonShowConsent.isEnabled = true
        binding.progressLoading.visibility = View.GONE
    }

    override fun onRequestShowDialog() {
        Log.v("FireAntsAdConsentManager", "onRequestShowDialog  ")
    }
}