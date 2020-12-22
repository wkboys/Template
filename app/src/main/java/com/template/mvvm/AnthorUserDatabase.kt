package com.template.mvvm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//import com.template.boundary.UserDao;
//import com.template.boundary.UserDatabase;
@Database(entities = [AuthorUser::class], version = 1)
abstract class AnthorUserDatabase : RoomDatabase() {
    abstract fun userDao(): AnthorUserDao

    companion object {
        private const val DATABASE_NAME = "anthoruser_db"
        private var databaseInstance: AnthorUserDatabase? = null
        @Synchronized
        fun getInstance(context: Context): AnthorUserDatabase? {
            if (databaseInstance == null) {
                databaseInstance = Room
                    .databaseBuilder(
                        context.applicationContext,
                        AnthorUserDatabase::class.java,
                        DATABASE_NAME
                    )
                    .build()
            }
            return databaseInstance
        }
    }
}