package com.template.mvvm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "anthoruser")
class AuthorUser(
    @field:ColumnInfo(
        name = "id",
        typeAffinity = ColumnInfo.INTEGER
    ) @field:PrimaryKey var id: Int, @field:ColumnInfo(
        name = "name",
        typeAffinity = ColumnInfo.TEXT
    ) var name: String, @field:SerializedName("avatar_url") @field:ColumnInfo(
        name = "avatar",
        typeAffinity = ColumnInfo.TEXT
    ) var avatar: String, @field:ColumnInfo(
        name = "followers",
        typeAffinity = ColumnInfo.INTEGER
    ) var followers: Int, @field:ColumnInfo(
        name = "following",
        typeAffinity = ColumnInfo.INTEGER
    ) var following: Int, @field:ColumnInfo(
        name = "blog",
        typeAffinity = ColumnInfo.TEXT
    ) var blog: String, @field:ColumnInfo(
        name = "company",
        typeAffinity = ColumnInfo.TEXT
    ) var company: String, @field:ColumnInfo(
        name = "bio",
        typeAffinity = ColumnInfo.TEXT
    ) var bio: String, @field:ColumnInfo(
        name = "location",
        typeAffinity = ColumnInfo.TEXT
    ) var location: String, @field:SerializedName("html_url") @field:ColumnInfo(
        name = "htmlUrl",
        typeAffinity = ColumnInfo.TEXT
    ) var htmlUrl: String
) {
    override fun toString(): String {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", followers=" + followers +
                ", following=" + following +
                ", blog='" + blog + '\'' +
                ", company='" + company + '\'' +
                ", bio='" + bio + '\'' +
                ", location='" + location + '\'' +
                ", htmlUrl='" + htmlUrl + '\'' +
                '}'
    }
}