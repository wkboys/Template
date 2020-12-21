package com.template.paging

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
     var retrofit:Retrofit
    companion object{
        var BASE_URL:String="http://www.baidu.com"
        var API_KEY:String="api_key"
         var retrofitClient:RetrofitClient?=null
        fun getInstance(): RetrofitClient {
            if (retrofitClient==null){
                retrofitClient= RetrofitClient()
            }
            return retrofitClient as RetrofitClient
        }
    }

    init {
        retrofit=Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient()).build()
    }

    fun getApi(): Api {
        return retrofit.create(Api::class.java)
    }

    private fun getClient(): OkHttpClient {
        var httpClient=OkHttpClient.Builder()
        httpClient.addInterceptor{ chain ->
            var original:Request=chain.request()
            val originalHttpUrl = original.url()
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", API_KEY)
                .build()
            val requestBuilder = original.newBuilder().url(url)
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return httpClient.build()
    }
}