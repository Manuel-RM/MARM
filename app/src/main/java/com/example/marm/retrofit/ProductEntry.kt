package com.example.marm.retrofit

import android.media.Rating
import com.google.gson.annotations.SerializedName

data class ProductEntry(
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
    val image:String,
    @SerializedName("rating")
    val rating: ratingEntry
)

data class ratingEntry(
    @SerializedName("rate")
    val rate: Double
)