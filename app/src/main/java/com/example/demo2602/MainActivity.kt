package com.example.demo2602

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.viettel.vht.sdk.funtionsdk.VHomeOpenDetailDeviceListener
import com.viettel.vht.sdk.funtionsdk.VHomeResultListener
import com.viettel.vht.sdk.funtionsdk.VHomeSDKManager
import com.viettel.vht.sdk.funtionsdk.VResult
import com.viettel.vht.sdk.funtionsdk.VStatus
import com.viettel.vht.sdk.jfmanager.JFCameraManager
import com.viettel.vht.sdk.model.DeviceDataResponse
import com.viettel.vht.sdk.model.share.UsersResponse
import com.viettel.vht.sdk.utils.DebugConfig
import dagger.hilt.android.AndroidEntryPoint
import vn.com.rangdong.rallismartv3dev.R
import vn.com.rangdong.rallismartv3dev.databinding.ActivityMainBinding
import java.sql.Time
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
            vHomeSDKManager.openAddDevice(
                this,
                object : VHomeResultListener<DeviceDataResponse, String> {
                    override fun onFailed(error: String?) {
                        Toast.makeText(
                            this@MainActivity,
                            "onDeleteCameraJF statusDelete: $error",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onSuccess(data: DeviceDataResponse?) {
                        val idCamera = data?.id
                        val serial = data?.getSerialNumber()
                        val nameCamera = data?.name
                        val model = data?.getModelCamera()
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
            vHomeSDKManager.openListDeviceVHome()
        }

        binding.detailCamera.setOnClickListener {
            vHomeSDKManager.openDetailDevice(this,
                serialCamera = "df9f0eea22042945",
                listener = object : VHomeOpenDetailDeviceListener {
                    override fun onDeleteDevice(data: VResult<Boolean, String>) {
                        when (data.status) {
                            VStatus.SUCCESS -> {
                                Toast.makeText(
                                    this@MainActivity,
                                    "onDeleteCameraJF statusDelete: ${data.data}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            VStatus.ERROR -> {
                                Toast.makeText(
                                    this@MainActivity,
                                    "onDeleteCameraJF onFailed: ${data.error}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            else -> {}
                        }
                    }

                    override fun onRenameDevice(data: VResult<String, String>) {
                        when (data.status) {
                            VStatus.SUCCESS -> {
                                Toast.makeText(
                                    this@MainActivity,
                                    "renameCameraListener onSuccess: ${data.data}",
                                    Toast.LENGTH_LONG
                                ).show()

                            }

                            VStatus.ERROR -> {
                                Toast.makeText(
                                    this@MainActivity,
                                    "renameCameraListener onFailed: ${data.error}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                            else -> {}
                        }
                    }

                }

            )
        }

        binding.loginVhome.setOnClickListener {

            JFCameraManager.logout()
            vHomeSDKManager.loginAccountVHome(
                "0986784498",
                "12345678aB@",
                listener = object : VHomeResultListener<String, Int> {
                    override fun onFailed(error: Int?) {
                        Toast.makeText(
                            this@MainActivity,
                            "loginAccountVHome: $error",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onSuccess(data: String?) {
                        Toast.makeText(
                            this@MainActivity,
                            "loginAccountVHome: $data",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                })
        }
        binding.refreshTokenVHome.setOnClickListener {

            vHomeSDKManager.setRefreshTokenSDKVHome("S3DNbf0ThJ6jxYMh1rjLY0NL7SIvE6PUaEzekg2S",
                object : VHomeResultListener<String, Int> {
                    override fun onFailed(error: Int?) {
                        Toast.makeText(
                            this@MainActivity,
                            "setRefreshTokenSDKVHome onFailed: $error",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onSuccess(data: String?) {
                        Toast.makeText(
                            this@MainActivity,
                            "setRefreshTokenSDKVHome onSuccess: $data",
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
                val list = vHomeSDKManager.getListEventDeviceByTime(
                    "b37149463e75da3e",
                    startSearchDay,
                    stopSearchDay
                )

                DebugConfig.logd("Trong", "ListData: $list")
            }

        }

        binding.btnDeleteEvent.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                val deleteResult = vHomeSDKManager.deleteListEventDevice(
                    "b37149463e75da3e",
                    listOf<String>("24498465")
                )

                DebugConfig.logd("Trong", "deleteResult: $deleteResult")
            }
        }

        binding.btnReadEvent.setOnClickListener {
//            lifecycleScope.launchWhenStarted {
//                val readResult = vHomeSDKManager.setReadEventDevice(
//                    "b37149463e75da3e",
//                    listOf<String>("244983743")
//                )
//                DebugConfig.logd("Trong", "readResult: $readResult")
//
//            }

            vHomeSDKManager.logOutAccountSDKVHome()
        }


        binding.btnCreateShare.setOnClickListener {
            val list = mutableListOf<String>()
            //349eca2f2bee1bcb
            list.add("DP_AlarmPush")
            list.add(  "DP_LocalStorage,DP_ViewCloudVideo")
            vHomeSDKManager.createShareDevice(
                "349eca2f2bee1bcb",
                "0943456788",
                list,
                object : VHomeResultListener<Boolean, String> {
                    override fun onFailed(error: String?) {
                        Toast.makeText(
                            this@MainActivity,
                            "createShareCamera onFailed: $error",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onSuccess(data: Boolean?) {
                        Toast.makeText(
                            this@MainActivity,
                            "createShareCamera onSuccess: $data",
                            Toast.LENGTH_LONG
                        ).show()
                    }


                }
            )

        }

        binding.btnListDeviceReceivedSharing.setOnClickListener {
            vHomeSDKManager.getListDeviceReceivedSharing(
                "",
                object : VHomeResultListener<List<DeviceDataResponse>, String> {
                    override fun onFailed(error: String?) {
                        Toast.makeText(
                            this@MainActivity,
                            "getListDeviceReceivedSharing onFailed: $error",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    override fun onSuccess(data: List<DeviceDataResponse>?) {
                        Toast.makeText(
                            this@MainActivity,
                            "getListDeviceReceivedSharing onSuccess: $data",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d("Trong", "getListDeviceReceivedSharing onSuccess: $data")
                    }
                })
        }

        binding.btnListShareUserBySerialCamera.setOnClickListener {
            vHomeSDKManager.getListShareUserBySerialCamera("349eca2f2bee1bcb", object : VHomeResultListener<UsersResponse, String>{
                override fun onFailed(error: String?) {
                    Toast.makeText(
                        this@MainActivity,
                        "getListShareUserBySerialCamera onFailed: $error",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onSuccess(data: UsersResponse?) {
                    Toast.makeText(
                        this@MainActivity,
                        "getListShareUserBySerialCamera onSuccess: $data",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("Trong", "getListShareUserBySerialCamera onSuccess: $data")
                }

            })
        }
        vHomeSDKManager.loginJFAccount()
    }
}