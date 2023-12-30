package com.iccangji.suitmediatest.data.network.retrofit

import com.iccangji.suitmediatest.data.network.model.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsersData(
        @Query("page") page: Int
    ): UsersResponse
}