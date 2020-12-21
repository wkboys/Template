package com.template.pagingpage

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

class UserDataSourceFactory : DataSource.Factory<Int, User>() {
    private val liveDataSource = MutableLiveData<UserDataSource>()
    override fun create(): UserDataSource {
        val dataSource = UserDataSource()
        liveDataSource.postValue(dataSource)
        return dataSource
    }
}