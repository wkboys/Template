package com.template.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource


class MovieDataSourceFactory: DataSource.Factory<Int, Movie>() {

    var liveDataSource= MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        var dataSource=MovieDataSource()
        liveDataSource.postValue(dataSource)
        return dataSource
    }

}