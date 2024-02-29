package com.example.demo2602

import android.app.Application
import com.example.mylibrary.SDKManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication: Application(){
    @Inject
    lateinit var sdkManager: SDKManager

    override fun onCreate() {
        super.onCreate()
        SDKVHome.setInstantSDK(sdkManager)
    }
}