package com.template.boundary

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "user")
class Users(
    @field:ColumnInfo(
        name = "id",
        typeAffinity = ColumnInfo.INTEGER
    ) @field:PrimaryKey var id: Int, @field:SerializedName("login") @field:ColumnInfo(
        name = "name",
        typeAffinity = ColumnInfo.TEXT
    ) var name: String, @field:SerializedName("avatar_url") @field:ColumnInfo(
        name = "avatar",
        typeAffinity = ColumnInfo.TEXT
    ) var avatar: String
) {
    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is Users) return false
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