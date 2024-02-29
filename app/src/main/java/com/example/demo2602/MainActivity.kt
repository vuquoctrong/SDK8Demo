package com.example.demo2602

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.demo2602.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.addCamera.setOnClickListener {
            SDKVHome.getInstantSDK()?.sdkOpenAddCameraJF(onFailed = {
                Toast.makeText(this,"sdkOpenAddCameraJF: $it",Toast.LENGTH_LONG).show()
            }, onSuccess = {

            })
        }
        binding.detailCamera.setOnClickListener {
            SDKVHome.getInstantSDK()?.sdkOpenDetailCamera()
        }

        binding.loginVhome.setOnClickListener {
            SDKVHome.getInstantSDK()?.sdkLoginVHome("0986784498","12345678aA@", onFailed = {
                Toast.makeText(this,"Login Vhome: $it",Toast.LENGTH_LONG).show()
            },onSuccess = {
                Toast.makeText(this,"Login Vhome: $it",Toast.LENGTH_LONG).show()
            })
        }

    }
}