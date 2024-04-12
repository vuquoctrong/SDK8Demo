package com.example.demo2602

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.demo2602.databinding.ActivityMainBinding
import com.viettel.vht.sdk.funtionsdk.VHomeDetailCameraJFSDKListener
import com.viettel.vht.sdk.funtionsdk.VHomeSDKAddCameraJFListener
import com.viettel.vht.sdk.funtionsdk.VHomeSDKLoginListener
import com.viettel.vht.sdk.funtionsdk.VHomeSDKManager
import com.viettel.vht.sdk.funtionsdk.VHomeSDKRefreshTokenListener
import com.viettel.vht.sdk.jfmanager.JFCameraManager
import com.viettel.vht.sdk.model.DeviceDataResponse
import com.viettel.vht.sdk.utils.DebugConfig
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var vHomeSDKManager: VHomeSDKManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        vHomeSDKManager.setLogcat(true)
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
                    val idCamera = data.id
                    val serial = data.getSerialNumber()
                    val nameCamera = data.name
                    val model = data.getModelCamera()
                    Toast.makeText(
                        this@MainActivity,
                        "openAddCameraJF onSuccess",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d(
                        "Trong",
                        "openAddCameraJF onSuccess idCamera:$idCamera, serial: $serial, nameCamera:$nameCamera, model:$model"
                    )
                }

            })

        }
        binding.tvListCamera.setOnClickListener {
            vHomeSDKManager.openListCameraJFVHome()
        }

        binding.detailCamera.setOnClickListener {
            vHomeSDKManager.openDetailCameraJF(this,
                idCamera = "ce031a7f-8887-47a9-aed3-738cf0147c92",
                serialCamera = "b37149463e75da3e",
                nameCamera = "b37149463e75da3e",
                modelCamera = "HC23",
                object : VHomeDetailCameraJFSDKListener {
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
                    }

                })
        }
        binding.refreshTokenVHome.setOnClickListener {
            vHomeSDKManager.setRefreshTokenSDKVHome("Ugz5NRjyG5z2nxrKKS248vrUrfaog2yPOpIgvohP",
                object : VHomeSDKRefreshTokenListener {
                    override fun onFailed(var1: Int) {
                        Toast.makeText(
                            this@MainActivity,
                            "setRefreshTokenSDKVHome onFailed: $var1",
                            Toast.LENGTH_LONG
                        ).show()

                    }

                    override fun onSuccess(token: String) {
                        Toast.makeText(
                            this@MainActivity,
                            "setRefreshTokenSDKVHome onSuccess: $token",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                })
        }
        binding.btnGetListEvent.setOnClickListener {
            val startSearchDay = Calendar.getInstance()
            startSearchDay.time = Date(Calendar.getInstance().timeInMillis)
            startSearchDay[Calendar.HOUR_OF_DAY] = 0
            startSearchDay[Calendar.MINUTE] = 0
            startSearchDay[Calendar.SECOND] = 0
            val stopSearchDay = Calendar.getInstance()
            stopSearchDay.time = Date(Calendar.getInstance().timeInMillis)
            stopSearchDay[Calendar.HOUR_OF_DAY] = 23
            stopSearchDay[Calendar.MINUTE] = 59
            stopSearchDay[Calendar.SECOND] = 59
            lifecycleScope.launchWhenStarted {
                val list = vHomeSDKManager.getListEventCameraJFByTime(
                    "b37149463e75da3e",
                    startSearchDay,
                    stopSearchDay
                )

                DebugConfig.logd("Trong","ListData: $list")
            }

        }

        binding.btnDeleteEvent.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                val deleteResult = vHomeSDKManager.deleteListEventCameraJF(
                    "b37149463e75da3e",
                  listOf<String>("24498465")
                )

                DebugConfig.logd("Trong","deleteResult: $deleteResult")
            }
        }

        binding.btnReadEvent.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                val readResult = vHomeSDKManager.setReadEventCameraJF(
                    "b37149463e75da3e",
                    listOf<String>("244983743")
                )
                DebugConfig.logd("Trong","readResult: $readResult")

            }
        }
         vHomeSDKManager.loginJFAccount()

    }
}