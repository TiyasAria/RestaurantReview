package com.tiyas.restaurantreview.model


import com.google.gson.annotations.SerializedName
import com.tiyas.restaurantreview.model.Restaurant

data class RestaurantResponse(
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("restaurant")
    val restaurant: Restaurant
)