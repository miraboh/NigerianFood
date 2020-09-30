package com.example.nigeriancuisine

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FoodNetworkEntity(
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("pics")
    @Expose
    var pics: String? = null,
    @SerializedName("likes")
    @Expose
    var likes: String? = null,
    @SerializedName("about")
    @Expose
    var about: String? = null,
    @SerializedName("origin")
    @Expose
    var origin: String? = null,
    @SerializedName("foodClass")
    @Expose
    var foodClass: String? = null,
    @SerializedName("procedure")
    @Expose
    var procedure: String? = null,
    @SerializedName("ingredients")
    @Expose
    var ingredients: String? = null
)