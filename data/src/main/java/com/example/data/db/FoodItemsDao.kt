package com.example.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.models.FoodItemDb

@Dao
interface FoodItemsDao {

    @Insert
    suspend fun insertFood(foodItemDb: FoodItemDb)

    @Query("SELECT * FROM fooditemdb")
    suspend fun getAll(): List<FoodItemDb>
}
