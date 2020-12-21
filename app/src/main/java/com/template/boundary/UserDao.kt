package com.template.boundary

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun insertUsers(users: List<Users>)

    @Query("DELETE FROM user")
    fun clear()

    @Query("SELECT * FROM user")
   fun getUsersList(): DataSource.Factory<Int, Users>


}