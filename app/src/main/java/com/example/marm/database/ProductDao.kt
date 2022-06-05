package com.example.marm.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy

@Dao
interface ProductDao {
    @Query("SELECT * FROM $TABLE_PRODUCTS")
    fun getProductsFromDatabase(): List<ProductEntity>

    @Query("SELECT * FROM $TABLE_PRODUCTS WHERE id = :pid")
    fun getProductById(pid: String): ProductEntity

    @Delete()
    fun delete(product: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(product: ProductEntity)
}