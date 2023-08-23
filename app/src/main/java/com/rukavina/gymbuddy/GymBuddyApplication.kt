package com.rukavina.gymbuddy

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GymBuddyApplication : Application() {
    // You can perform any global initialization or setup here
    override fun onCreate() {
        super.onCreate()
        Log.d("AppInfo", "GymBuddyApplication init")
    }
}
