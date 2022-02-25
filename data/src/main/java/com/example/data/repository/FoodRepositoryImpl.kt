package com.example.data.repository


import android.util.Log
import com.example.data.api.ApiService
import com.example.domain.models.FoodItem
import com.example.domain.models.FoodItemInfo
import com.example.domain.repository.FoodRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStream
import java.util.*
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(val apiService: ApiService): FoodRepository {

    override suspend fun getFoodItems(string: String): ArrayList<FoodItem> {
        val i= apiService.getFoodList(string)
        val mapper = FoodMapper()
        mapper.toDomainList(i.food)
        return mapper.toDomainList(i.food)
    }

    override suspend fun getFoodItemById(id: String): FoodItemInfo {
        val i = apiService.getFoodItemById(id)
        val mapper = FoodMapper()
        return mapper.toFoodItemInfo(i)
    }
}