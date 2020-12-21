package com.template.pagingpage

import com.google.gson.annotations.SerializedName
import java.util.*

class User(
    @field:SerializedName("account_id") var id: Int, @field:SerializedName(
        "display_name"
    ) var name: String, @field:SerializedName("profile_image") var avatar: String
) {
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is User) return false
        val user = o
        return id == user.id && name == user.name && avatar == user.avatar
    }

    override fun hashCode(): Int {
        return Objects.hash(id, name, avatar)
    }

    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                '}'
    }
}