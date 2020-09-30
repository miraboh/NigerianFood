package com.example.nigeriancuisine

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nigeriancuisine.Food

@Dao
interface FoodDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(food: List<Food>)

    @Query("SELECT * FROM foods")
    fun getfood() : LiveData<List<Food>>
}