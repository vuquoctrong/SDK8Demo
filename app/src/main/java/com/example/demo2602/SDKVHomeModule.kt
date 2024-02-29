package com.example.demo2602

import android.content.Context
import com.viettel.vht.sdk.funtionsdk.VHomeSDKManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SDKVHomeModule {
    @Provides
    @Singleton
    fun provideSDKManager(
        vHomeSDKManager: VHomeSDKManager,
        @ApplicationContext context: Context
    ): SDKManager = SDKManager(vHomeSDKManager, context)
}