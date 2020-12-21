package com.template.pagingpage

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PageApi {
    /**
     * 获取GitHub用户列表
     *
     * https://api.stackexchange.com/2.2/users?page=1&pagesize=6&site=stackoverflow
     */
    @GET("users")
    fun getUsers(
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int,
        @Query("site") site: String?
    ): Call<UserResponse?>?
}