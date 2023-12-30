package com.iccangji.suitmediatest.data

import android.content.Context
import com.iccangji.suitmediatest.data.network.retrofit.ApiConfig
import com.iccangji.understextapp.data.NetworkRepository

object Injection {
    fun provideRepository(context: Context): NetworkRepository {
        val apiService = ApiConfig.getApiService()
        return NetworkRepository(apiService)
    }
}