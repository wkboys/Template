package com.template.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "student")
data class Student(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id",typeAffinity = ColumnInfo.INTEGER)
    val id:Int,

    @ColumnInfo(name = "name",typeAffinity = ColumnInfo.TEXT)
    val name:String

)