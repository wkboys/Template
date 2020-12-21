package com.template.boundary

import android.app.Application
import android.os.AsyncTask
import android.util.Log
import androidx.paging.PagedList
import com.template.module_common.BaseApp
import com.template.room.MyDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserBoundaryCallback : PagedList.BoundaryCallback<Users>() {

    private val TAG = this.javaClass.name

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        Log.e(TAG, "onZeroItemsLoaded()")
        getTopData()
    }

    override fun onItemAtFrontLoaded(itemAtFront: Users) {
        super.onItemAtFrontLoaded(itemAtFront)
        Log.e(TAG, "onItemAtFrontLoaded()")
    }

    override fun onItemAtEndLoaded(itemAtEnd: Users) {
        super.onItemAtEndLoaded(itemAtEnd)
        Log.e(TAG, "onItemAtEndLoaded()")
        getTopAfterData(itemAtEnd)
    }

    /**
     * 没有数据的时候，加载第一页数据
     */
    private fun getTopData() {
        val since = 0
        RetrofitClientBound.instance?.api
            ?.getUsers(since, UserBoundViewModel.PER_PAGE)
            ?.enqueue(object : Callback<List<Users?>?> {
                override fun onResponse(call: Call<List<Users?>?>, response: Response<List<Users?>?>) {
                    if (response.body() != null) {
                        Log.e("getTopData()", " response:" + response.body())
                        insertUsers(response.body())
                    }
                }

                override fun onFailure(call: Call<List<Users?>?>, t: Throwable) {}
            })
    }

    /**
     * 获取下一页数据
     */
    private fun getTopAfterData(user: Users) {
        RetrofitClientBound.instance?.api
            ?.getUsers(user.id, UserBoundViewModel.PER_PAGE)
            ?.enqueue(object : Callback<List<Users?>?> {
                override fun onResponse(call: Call<List<Users?>?>, response: Response<List<Users?>?>) {
                    if (response.body() != null) {
                        Log.e("getTopAfterData()", " response:" + response.body())
                        insertUsers(response.body())
                    }
                }

                override fun onFailure(call: Call<List<Users?>?>, t: Throwable) {}
            })
    }

    /**
     * 插入数据
     */
    private fun insertUsers(users: List<Users?>?) {
        AsyncTask.execute {
            UserDatabase.getInstance(BaseApp.baseApplication)?.userDao()?.insertUsers(users as List<Users>)
        }
    }
}