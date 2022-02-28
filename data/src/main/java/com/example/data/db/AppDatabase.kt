package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.models.FoodItemDb

@Database(entities = [FoodItemDb::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun foodItemsDao(): FoodItemsDao
}