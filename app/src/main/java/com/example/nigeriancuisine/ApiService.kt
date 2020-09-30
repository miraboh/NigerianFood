package com.example.nigeriancuisine

import retrofit2.http.GET

interface ApiService {

    @GET("/recipe.json")
    suspend fun getApi(): List<Food>

}