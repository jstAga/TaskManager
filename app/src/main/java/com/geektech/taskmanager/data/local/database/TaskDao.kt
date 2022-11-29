package com.geektech.taskmanager.data.local.database

import androidx.room.*
import com.geektech.taskmanager.data.model.Task

@Dao
interface  TaskDao {

    @Query("SELECT * FROM task ORDER BY id DESC")
    fun getAllTask(): List<Task>

    @Insert
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task: Task)
}