package com.example.demo2602

import android.content.Context
import com.viettel.vht.sdk.funtionsdk.VHomeDetailCameraJFSDKListener
import com.viettel.vht.sdk.funtionsdk.VHomeSDKAddCameraJFListener
import com.viettel.vht.sdk.funtionsdk.VHomeSDKLoginListener
import com.viettel.vht.sdk.funtionsdk.VHomeSDKManager
import com.viettel.vht.sdk.model.DeviceDataResponse
import javax.inject.Singleton


@Singleton
class SDKManager(
    private val vHomeSDKManager: VHomeSDKManager,
    private val context: Context
) {
    fun sdkLoginVHome(
        phone: String,
        password: String,
        onFailed: ((String) -> Unit)? = null,
        onSuccess: ((String) -> Unit)? = null
    ) {
        vHomeSDKManager.loginAccountVHome(phone, password, object : VHomeSDKLoginListener {
            override fun onFailed(var1: Int) {
                onFailed?.invoke(" loginAccountVHome onFailed: onFailed $var1 ")
            }

            override fun onSuccess(token: String) {
                onSuccess?.invoke(" loginAccountVHome onSuccess: token $token ")
            }

        })
    }

    fun sdkOpenAddCameraJF(
        onFailed: ((String) -> Unit)? = null,
        onSuccess: ((String) -> Unit)? = null
    ) {
        vHomeSDKManager.openAddCameraJF(context, object : VHomeSDKAddCameraJFListener {
            override fun onFailed(messageError: String) {
                onFailed?.invoke(messageError)
            }

            override fun onSuccess(data: DeviceDataResponse) {
                val idCamera =  data.id
                val serial = data.getSerialNumber()
                val nameCamera = data.name
                val model = data.getModelCamera()
                onSuccess?.invoke(data.id ?: "")
            }

        })
    }


}