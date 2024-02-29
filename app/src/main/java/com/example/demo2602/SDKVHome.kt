package com.example.demo2602


object SDKVHome {
    private var sdkManager: SDKManager? = null

    fun setInstantSDK(sdkManager: SDKManager) {
        this.sdkManager = sdkManager
    }

    fun getInstantSDK(): SDKManager? {
        return this.sdkManager
    }
}