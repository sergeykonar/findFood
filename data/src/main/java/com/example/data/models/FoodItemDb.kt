package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FoodItemDb(
    val name: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}
