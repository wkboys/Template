package com.template.boundary

import com.template.paging.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientBound private constructor() {
    private val retrofit: Retrofit
    val api: BoundApi
        get() = retrofit.create(BoundApi::class.java)

    companion object {
        private const val BASE_URL = "https://api.github.com/"
        private var retrofitClient: RetrofitClientBound? = null

        @get:Synchronized
        val instance: RetrofitClientBound?
            get() {
                if (retrofitClient == null) {
                    retrofitClient = RetrofitClientBound()
                }
                return retrofitClient
            }
    }

    init {
        retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}