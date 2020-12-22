package com.template.mvvm

import android.os.AsyncTask
import android.util.Log
import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 数据层，只对ViewModel负责，ViewModel不再需要关心数据从哪里来。
 *
 *
 * Room数据库是ViewModel的单一数据来源，刷新后数据也是直接写入Room数据库，通过LiveData暴露给ViewModel
 */
class UserRepository(private val userDao: AnthorUserDao, private val api: AnthorApi) {
    private val TAG = this.javaClass.name
    fun getUser(name: String?): LiveData<AuthorUser> {
        refresh(name)
        return userDao.getUserByName(name)
    }

    fun refresh(userName: String?) {
        api.getUser(userName)!!.enqueue(object : Callback<AuthorUser?> {
            override fun onResponse(call: Call<AuthorUser?>, response: Response<AuthorUser?>) {
                if (response.body() != null) {
                    Log.e(TAG, "refresh()->response:" + response.body())
                    insertUser(response.body()!!)
                }
            }

            override fun onFailure(call: Call<AuthorUser?>, t: Throwable) {
                Log.e(TAG, "refresh()->onFailure()")
            }
        })
    }

    private fun insertUser(user: AuthorUser) {
        AsyncTask.execute {
            userDao.insertUser(user)
            Log.e(TAG, "insertUser()->$user")
        }
    }
}