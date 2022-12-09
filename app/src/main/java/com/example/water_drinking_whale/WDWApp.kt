package com.example.water_drinking_whale

import android.app.Application
import com.example.water_drinking_whale.utils.SharedPreferencesUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WDWApp : Application() {

    companion object {
        lateinit var sharedPreferencesUtil: SharedPreferencesUtil
    }

    override fun onCreate() {
        super.onCreate()
        sharedPreferencesUtil = SharedPreferencesUtil(applicationContext)
    }
}
