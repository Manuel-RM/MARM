package com.example.marm.retrofit

import com.google.gson.annotations.SerializedName

data class ProductsEntry(
    @SerializedName("0")
    val jsonEntry : jsonEntry
    )

data class jsonEntry(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("description")
    val description: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("rating")
    val rating: rateEntry
)

data class rateEntry(
    @SerializedName("rate")
    val rate: Double,
)