package com.template.pagingpage

import com.google.gson.annotations.SerializedName

class UserResponse {
    @SerializedName("items")
    var users: List<User>? = null

    @SerializedName("has_more")
    var hasMore = false
    override fun toString(): String {
        return "UserResponse{" +
                "users=" + users +
                ", hasMore=" + hasMore +
                '}'
    }
}