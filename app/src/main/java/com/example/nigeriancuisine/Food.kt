package com.example.nigeriancuisine

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "foods")
data class Food(
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null,
    var name: String? = null,
    var pics: String? = null,
    var likes: String? = null,
    var about: String? = null,
    var origin: String? = null,
    var foodClass: String? = null,
    var procedure: String? = null,
    var ingredients: String? = null
):Parcelable