package com.example.marm.database

import android.app.Application

class Application : Application() {

    override fun onCreate(){
        DatabaseManager.instance.initializeDatabase(applicationContext)
        super.onCreate()
    }
}