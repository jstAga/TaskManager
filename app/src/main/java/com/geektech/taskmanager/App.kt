package com.geektech.taskmanager

import android.app.Application
import androidx.room.Room
import com.geektech.taskmanager.data.local.database.AppDataBase

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        dataBase = Room.databaseBuilder(applicationContext, AppDataBase::class.java, "task-database").
        allowMainThreadQueries().build()
    }

    companion object {
        lateinit var dataBase: AppDataBase
    }
}