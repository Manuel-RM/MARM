package com.example.marm.database

import androidx.room.Entity

@Entity(tableName = TABLE_PRODUCTS)
data class ProductEntity(
    val id: String,
    val title: String,
    val price: Double,
    val descrption: String,
    val image: String,
    val rate: Double,
    val username: String,
)

fun ProductEntity.toProduct() = Product(id, title, price, descrption, image, rate, username)