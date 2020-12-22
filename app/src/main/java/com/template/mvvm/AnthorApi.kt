package com.template.mvvm

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AnthorApi {
    /**
     * 获取GitHub用户信息
     *
     * https://api.github.com/users/michaelye
     *
     */
    @GET("users/{userId}")
    fun getUser(
        @Path("userId") userId: String?
    ): Call<AuthorUser?>?
}