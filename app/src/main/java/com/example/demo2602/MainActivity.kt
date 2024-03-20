package com.example.demo2602

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.demo2602.databinding.ActivityMainBinding
import com.vht.sdkcore.pref.RxPreferences
import com.viettel.vht.sdk.funtionsdk.VHomeDetailCameraJFSDKListener
import com.viettel.vht.sdk.funtionsdk.VHomeSDKAddCameraJFListener
import com.viettel.vht.sdk.funtionsdk.VHomeSDKLoginListener
import com.viettel.vht.sdk.funtionsdk.VHomeSDKManager
import com.viettel.vht.sdk.jfmanager.JFCameraManager
import com.viettel.vht.sdk.model.DeviceDataResponse
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var vHomeSDKManager: VHomeSDKManager

    @Inject
    lateinit var rxPreferences: RxPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.addCamera.setOnClickListener {
            vHomeSDKManager.openAddCameraJF(this, object : VHomeSDKAddCameraJFListener {
                override fun onFailed(messageError: String) {
                    Toast.makeText(
                        this@MainActivity,
                        "onDeleteCameraJF statusDelete: $messageError",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onSuccess(data: DeviceDataResponse) {
                    val idCamera =  data.id
                    val serial = data.getSerialNumber()
                    val nameCamera = data.name
                    val model = data.getModelCamera()
                    Toast.makeText(
                        this@MainActivity,
                        "openAddCameraJF onSuccess",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("Trong","openAddCameraJF onSuccess idCamera:$idCamera, serial: $serial, nameCamera:$nameCamera, model:$model")
                }

            })
        }
        binding.detailCamera.setOnClickListener {
            vHomeSDKManager.openDetailCameraJF(this, idCamera = "577df1d0-25e7-47d7-986e-464f488e9439"
                , serialCamera = "b37149463e75da3e", nameCamera = "b37149463e75da3e", modelCamera = "HC23", object : VHomeDetailCameraJFSDKListener {
                override fun onDeleteCameraJF(statusDelete: Boolean) {
                    Toast.makeText(
                        this@MainActivity,
                        "onDeleteCameraJF statusDelete: $statusDelete",
                        Toast.LENGTH_LONG
                    ).show()

                }

            })
        }

        binding.loginVhome.setOnClickListener {

            JFCameraManager.logout()
            vHomeSDKManager.loginAccountVHome(
                "0986784498",
                "12345678aA@",
                object : VHomeSDKLoginListener {
                    override fun onFailed(var1: Int) {
                        Toast.makeText(
                            this@MainActivity,
                            "sdkOpenAddCameraJF: $var1",
                            Toast.LENGTH_LONG
                        ).show()

                    }

                    override fun onSuccess(token: String) {
                        Toast.makeText(
                            this@MainActivity,
                            "sdkOpenAddCameraJF: $token",
                            Toast.LENGTH_LONG
                        ).show()

                        Toast.makeText(
                            this@MainActivity,
                            "  rxPreferences.getUserPhoneNumber(): ${ rxPreferences.getUserPhoneNumber()}",
                            Toast.LENGTH_LONG
                        ).show()
                        rxPreferences.getUserPhoneNumber()
                    }

                })
        }

    }
}