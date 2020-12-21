package com.template.pagingpage

import android.util.Log
import androidx.paging.PageKeyedDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserDataSource : PageKeyedDataSource<Int, User?>() {
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User?>
    ) {
        Log.e("loadInitial()", "called")
        RetrofitClientPage.instance?.api
            ?.getUsers(FIRST_PAGE, PER_PAGE, SITE)
            ?.enqueue(object : Callback<UserResponse?> {
                override fun onResponse(
                    call: Call<UserResponse?>,
                    response: Response<UserResponse?>
                ) {
                    if (response.body() != null) {
                        Log.e("loadInitial()", " response:" + response.body())
                        callback.onResult(response.body()!!.users!!, null, FIRST_PAGE + 1)
                    }
                }

                override fun onFailure(call: Call<UserResponse?>, t: Throwable) {}
            })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User?>) {
        Log.e("loadAfter()", "called")
        RetrofitClientPage.instance?.api
            ?.getUsers(params.key, PER_PAGE, SITE)
            ?.enqueue(object : Callback<UserResponse?> {
                override fun onResponse(
                    call: Call<UserResponse?>,
                    response: Response<UserResponse?>
                ) {
                    if (response.body() != null) {
                        Log.e("loadAfter()", " response:" + response.body())
                        val nextKey = if (response.body()!!.hasMore) params.key + 1 else null
                        callback.onResult(response.body()!!.users!!, nextKey)
                    }
                }

                override fun onFailure(call: Call<UserResponse?>, t: Throwable) {}
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User?>) {
        Log.e("loadBefore()", "called")
    }

    companion object {
        const val FIRST_PAGE = 1
        const val PER_PAGE = 8
        const val SITE = "stackoverflow"
    }
}