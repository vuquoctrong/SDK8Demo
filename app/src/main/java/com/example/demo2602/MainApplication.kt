package com.example.demo2602

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication: Application(){

    override fun onCreate() {
        super.onCreate()
    }
}