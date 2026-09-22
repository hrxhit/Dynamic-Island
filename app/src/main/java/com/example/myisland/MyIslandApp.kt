package com.example.myisland

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyIslandApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
