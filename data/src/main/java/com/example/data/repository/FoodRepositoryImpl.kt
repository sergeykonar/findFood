package com.example.data.repository


import com.example.data.api.ApiService
import com.example.data.db.FoodItemsDao
import com.example.domain.models.FoodItem
import com.example.domain.models.FoodItemCache
import com.example.domain.models.FoodItemInfo
import com.example.domain.repository.FoodRepository
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class FoodRepositoryImpl @Inject constructor(val apiService: ApiService, val dao: FoodItemsDao): FoodRepository {

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

    override suspend fun saveToDatabase(foodItem: FoodItemCache) {
        val mapper = FoodMapper()
        val obj = mapper.toFoodItemDb(foodItem)
        dao.insertFood(obj)
    }

    override suspend fun getFoodDataDb(): ArrayList<FoodItemCache> {
        val list = dao.getAll()
        val mapper = FoodMapper()
        val data = ArrayList<FoodItemCache>()
        list.forEach {
            data.add(mapper.toFoodItemCache(it))
        }
        return data
    }
}