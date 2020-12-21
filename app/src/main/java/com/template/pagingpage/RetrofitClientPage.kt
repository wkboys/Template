package com.template.pagingpage

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientPage private constructor() {
    private val retrofit: Retrofit
    val api: PageApi
        get() = retrofit.create(PageApi::class.java)

    companion object {
        private const val BASE_URL = "https://api.stackexchange.com/2.2/"
        private var retrofitClient: RetrofitClientPage? = null

        @get:Synchronized
        val instance: RetrofitClientPage?
            get() {
                if (retrofitClient == null) {
                    retrofitClient = RetrofitClientPage()
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
