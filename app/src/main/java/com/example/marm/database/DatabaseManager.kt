package com.example.marm.database

import androidx.room.Room
import android.content.Context

class DatabaseManager {
    lateinit var database: Database

    fun initializeDatabase(context: Context){
        createDatabase(context)
    }

    private fun createDatabase(context: Context){
        database = Room.databaseBuilder(context, Database::class.java, DATABASE_NAME).build()
    }

    companion object{
        val instance = DatabaseManager()
    }
}