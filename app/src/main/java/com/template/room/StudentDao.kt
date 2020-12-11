package com.template.room

import androidx.room.*

@Dao
interface StudentDao {

    @Insert
    fun insertStudent(student:Student)

    @Delete
    fun deleteStudent(student:Student)

    @Update
             fun updateStudent(student:Student)

    @Query("SELECT * FROM student")
    fun getStudentList():List<Student>

    @Query("SELECT * FROM student WHERE id = :id ")
    fun getStudentList(id:Int):Student
}