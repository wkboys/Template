package com.template.mvvm

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * DAO
 *
 * 对数据库表的增删改查
 *
 */

@Dao
interface AnthorUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: AuthorUser)

    @Delete
    fun deleteStudent(user: AuthorUser)

    @Query("SELECT * FROM anthoruser WHERE name = :name")
    fun getUserByName(name: String?): LiveData<AuthorUser>
}