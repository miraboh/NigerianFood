package com.example.nigeriancuisine

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "foods")
class FoodCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "pics")
    var pics: String? = null,
    @ColumnInfo(name = "likes")
    var likes: String? = null,
    @ColumnInfo(name = "about")
    var about: String? = null,
    @ColumnInfo(name = "origin")
    var origin: String? = null,
    @ColumnInfo(name = "foodClass")
    var foodClass: String? = null,
    @ColumnInfo(name = "procedure")
    var procedure: String? = null,
    @ColumnInfo(name = "ingredients")
    var ingredients: String? = null
)

