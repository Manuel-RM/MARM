package com.example.marm.database

import androidx.room.Database
import androidx.room.RoomDatabase

const val DATABASE_VER = 1
const val TABLE_PRODUCTS = "products"
const val DATABASE_NAME = "app"+"base.sqlite"

@Database(entities = [ProductEntity::class],
version = DATABASE_VER
)

abstract class Database : RoomDatabase(){
    abstract fun productDao() : ProductDao
}