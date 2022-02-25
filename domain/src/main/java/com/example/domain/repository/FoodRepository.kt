package com.example.domain.repository



import com.example.domain.models.FoodItem
import com.example.domain.models.FoodItemInfo
import retrofit2.Call
import java.io.InputStream

interface FoodRepository {

    suspend fun getFoodItems(string: String): ArrayList<FoodItem>
    suspend fun getFoodItemById(id: String): FoodItemInfo
}
