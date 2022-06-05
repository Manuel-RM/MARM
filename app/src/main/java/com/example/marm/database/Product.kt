package com.example.marm.database

import android.text.LoginFilter

class Product(
    id: String,
    title: String,
    price: Double,
    description: String,
    image: String,
    rate: Double,
    username: String
    ) {

        val id: String = id
        val title: String  = title
        val price: Double = price
        val description: String = description
        val image: String = image
        val rate: Double = rate
        val username: String = username
    }



    fun Product.toEntity() = ProductEntity(
        id,
        title,
        price,
        description,
        image,
        rate,
        username
    )