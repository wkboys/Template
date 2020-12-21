package com.template.paging

import com.google.gson.annotations.SerializedName

class Movies {
    /**
     * 当前返回的数量
     */
    var count = 0

    /**
     * 起始位置
     */
    @JvmField
    var start = 0

    /**
     * 一共多少数据
     */
    @JvmField
    var total = 0

    /**
     * 返回的电影列表
     */
    @JvmField
    @SerializedName("subjects")
    var movieList: List<Movie>? = null
    override fun toString(): String {
        return "Movies{" +
                "count=" + count +
                ", start=" + start +
                ", total=" + total +
                ", movieList=" + movieList +
                '}'
    }
}