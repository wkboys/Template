package com.template.mvvm

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AnthorRetrofitClient private constructor() {
    private val retrofit: Retrofit
    val api: AnthorApi
        get() = retrofit.create(AnthorApi::class.java)

    companion object {
        private const val BASE_URL = "https://api.github.com/"
        private var retrofitClient: AnthorRetrofitClient? = null

        @get:Synchronized
        val instance: AnthorRetrofitClient?
            get() {
                if (retrofitClient == null) {
                    retrofitClient = AnthorRetrofitClient()
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