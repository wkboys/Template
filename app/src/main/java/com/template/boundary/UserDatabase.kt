package com.template.boundary

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Users::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    companion object {
        private val DATABASE_NAME = "user_db"

        private var databaseInstance: UserDatabase? = null

        @Synchronized
        open fun getInstance(context: Context): UserDatabase? {
            if (databaseInstance == null) {
                databaseInstance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        DATABASE_NAME
                    )
                    .build()
            }
            return databaseInstance
        }
    }


    abstract fun userDao(): UserDao?

}