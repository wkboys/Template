package com.template.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.template.module_common.BaseApp
import com.template.wk.myApplication
import io.reactivex.Single

@Database(entities = [ Student::class ]  , version =1)
abstract class MyDatabase : RoomDatabase() {


    companion object {

        @Volatile
        private var instance: MyDatabase? = null

        fun getDBInstace(): MyDatabase {

            if (instance == null) {

                synchronized(MyDatabase::class) {

                    if (instance == null) {

                        instance = Room.databaseBuilder(
                            BaseApp.baseApplication,
                            MyDatabase::class.java,
                            "User.db"
                        )
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }
            return instance!!
        }

    }

    abstract fun getStudentDao(): StudentDao
}