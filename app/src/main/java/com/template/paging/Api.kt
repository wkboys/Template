package com.template.paging

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("move/in_theaters")
    fun getMovies(
        @Query("start") since: Int,
        @Query("count") perPage: Int,
    ): Call<Movies>
}