package com.iccangji.suitmediatest.ui.third_screen

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.iccangji.suitmediatest.data.Injection
import com.iccangji.suitmediatest.data.network.model.DataItem
import com.iccangji.understextapp.data.NetworkRepository

class ThirdScreenViewModel(private val repository: NetworkRepository) : ViewModel() {

    val listUser: LiveData<PagingData<DataItem>> = repository.getUsers().cachedIn(viewModelScope)
}

class ViewModelFactory private constructor(
    private val repository: NetworkRepository,
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdScreenViewModel::class.java)) {
            return ThirdScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("No ModelClass: " + modelClass.name)
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory {
            return ViewModelFactory(Injection.provideRepository(context))
        }
    }
}