package com.iccangji.understextapp.data

import UserPagingSource
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.iccangji.suitmediatest.data.network.retrofit.ApiService
import com.iccangji.suitmediatest.data.network.model.DataItem

interface UsersRepository{
    fun getUsers(): LiveData<PagingData<DataItem>>
}
class NetworkRepository(
    private val apiService: ApiService
) : UsersRepository {
    override fun getUsers() : LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 12
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }
}