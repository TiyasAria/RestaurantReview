package com.tiyas.restaurantreview.model

import com.google.gson.annotations.SerializedName

data  class PostReviewResponse(

    @field:SerializedName("customerReviews")
    val customerReviews: List<CustomerReview>,

    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)