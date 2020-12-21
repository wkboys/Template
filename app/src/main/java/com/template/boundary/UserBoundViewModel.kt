package com.template.boundary

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class UserBoundViewModel(application: Application?) : AndroidViewModel(
    application!!
) {
    /**
     * 通过LivePagedListBuilder创建PagedList，并通过LiveData，将PagedList暴露给页面
     */
    var userPagedList: LiveData<PagedList<Users>>

    /**
     * 刷新数据
     */
    fun refresh() {
        AsyncTask.execute { UserDatabase.getInstance(getApplication())!!.userDao()!!.clear() }
    }

    companion object {
        const val PER_PAGE = 8
    }

    init {
        val database = UserDatabase.getInstance(application!!)
        val usersList:DataSource.Factory<Int, Users> = database!!.userDao()!!.getUsersList()
        userPagedList = LivePagedListBuilder(
            usersList,
            UserBoundViewModel.Companion.PER_PAGE
        )
            .setBoundaryCallback(UserBoundaryCallback())
            .build()


    }

}
