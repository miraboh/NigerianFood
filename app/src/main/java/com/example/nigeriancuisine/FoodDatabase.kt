package com.example.nigeriancuisine

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nigeriancuisine.Food
import com.example.nigeriancuisine.FoodDao

@Database(
    entities = [Food::class],
    version = 1, exportSchema = false
)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun getFoodDao(): FoodDao

    companion object{
        val DATABASE_NAME: String = "food_db"
    }

}