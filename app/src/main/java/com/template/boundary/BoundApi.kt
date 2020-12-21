package com.template.boundary

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BoundApi {
    /**
     * 获取GitHub用户列表
     *
     * https://api.github.com/users?since=0&page=0&per_page=5
     *
     * @param since 这里的since是上一个列表中最后一个User的id，以这个id作为since的值，请求下一列表的数据
     */
    @GET("users")
    fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int
    ): Call<List<Users?>?>?
}