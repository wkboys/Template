package com.template.pagingpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

class UserViewModel : ViewModel() {
    /**
     * 通过LivePagedListBuilder创建PagedList，并通过LiveData，将PagedList暴露给页面
     */
    var userPagedList: LiveData<PagedList<User>>

    init {
        val config =
            PagedList.Config.Builder() // 是否显示占位。默认为true。当被设置为true时，要求在DataSource中提供数据源的总量，否则会报错。
                // 这是因为RecyclerView需要知道数据总量，为这些数据预留位置
                .setEnablePlaceholders(true) //设置每页加载的数量
                .setPageSize(UserDataSource.PER_PAGE) //设置距离底部还有多少条数据时加载下一页数据
                .setPrefetchDistance(3) //重点，这里需要设置预获取的值>0，否则getKey()和loadBefore()以及loadAfter()不会被调用
                //首次初始化加载的数量，需是分页加载数量的倍数。若没有设置，则默认是PER_PAGE的三倍
                .setInitialLoadSizeHint(UserDataSource.PER_PAGE * 3) //好像没有效果
                //设置PagedList所能承受的最大数量，一般来说是PER_PAGE的许多许多倍，超过可能会出现异常。
                .setMaxSize(65536 * UserDataSource.PER_PAGE)
                .build()

        // 通过LivePagedListBuilder创建PagedList
        userPagedList = LivePagedListBuilder(UserDataSourceFactory(), config)
            .build()
    }
}