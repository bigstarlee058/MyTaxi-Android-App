package com.piyush.mytaxi

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.osmdroid.config.Configuration

@HiltAndroidApp
class MyTaxiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Configuration.getInstance().load(
            applicationContext,
            getSharedPreferences(packageName, MODE_PRIVATE)
        )
    }
}