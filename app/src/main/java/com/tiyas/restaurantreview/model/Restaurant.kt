package com.tiyas.restaurantreview.model


import com.google.gson.annotations.SerializedName
import com.tiyas.restaurantreview.model.CustomerReview

data class Restaurant(
    @SerializedName("customerReviews")
    val customerReviews: List<CustomerReview>,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("pictureId")
    val pictureId: String,
    @SerializedName("rating")
    val rating: Double
)