package com.playground.movie.utils

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.get
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.playground.movie.BuildConfig
import com.playground.movie.MovieApp
import com.playground.movie.R
import com.playground.movie.data.dto.ConfigDataModel
import kotlinx.serialization.json.Json

/**
 * @Details :FirebaseConfigData
 * @Author Roshan Bhagat
 */
class FirebaseConfig {

    companion object {
        // Remote Config keys
        private const val PCS_CONFIG_KEY = "QA"
        private const val TAG = "FirebaseConfigData"
        private const val CACHE_DURATION = 1800L
    }


    private fun initRemoteConfig() {
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) 0L else CACHE_DURATION
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val updated = task.result
                Log.d(TAG, "Firebase Config Fetched Successfully.$updated")

            } else {
                Log.d(TAG, "Firebase Config Fetched Failed.")

            }
        }
    }

    fun fetchFirebaseConfig() {
        initRemoteConfig()
        setupData()
    }

    private fun setupData() {
        val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
        val configResponse = remoteConfig[PCS_CONFIG_KEY].asString()
        //val format = Json { encodeDefaults = true } In case we want to use default values
        MovieApp.instance.sessionData.configData =
            Json.decodeFromString<ConfigDataModel>(configResponse)

    }
}