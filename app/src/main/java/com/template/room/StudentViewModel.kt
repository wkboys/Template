package com.template.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class StudentViewModel(application: Application) : AndroidViewModel(application) {
    var myDatabase: MyDatabase = MyDatabase.getDBInstace()
    private var liveDataStudent: LiveData<List<Student>>? = null
    init {
        liveDataStudent= myDatabase.getStudentDao().getStudentList()
    }
    fun getLiveDataStudent(): LiveData<List<Student>>? {
        return liveDataStudent
    }
}