package com.template.paging

import android.util.Log
import androidx.paging.PositionalDataSource
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
class MovieDataSource : PositionalDataSource<Movie>() {

    companion object{
        var PER_PAGE=8
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Movie>) {
       var startPosition=0
        RetrofitClient.getInstance().getApi().getMovies(startPosition,PER_PAGE)
            .enqueue(object: Callback<Movies?> {
                override fun onResponse(call: Call<Movies?>, response: Response<Movies?>) {
                    if (response.body() != null) {
                        Log.e("loadInitial()", "startPosition:" + params.requestedStartPosition + " response:" + response.body())
                        callback.onResult(response.body()!!.movieList!!, response.body()!!.start, response.body()!!.total)
                    }
                }

                override fun onFailure(call: Call<Movies?>, t: Throwable) {
                    Log.e("loadInitial()", "onFailure")
                }
            })
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Movie>) {
        RetrofitClient.getInstance().getApi().getMovies(params.startPosition,PER_PAGE)
            .enqueue(object: Callback<Movies?> {
                override fun onResponse(call: Call<Movies?>, response: Response<Movies?>) {
                    if (response.body() != null) {
                        Log.e("loadInitial()", "onResponse")
                        callback.onResult(response.body()!!.movieList!!)
                    }
                }

                override fun onFailure(call: Call<Movies?>, t: Throwable) {
                    Log.e("loadInitial()", "onFailure")
                }
            })
    }
}