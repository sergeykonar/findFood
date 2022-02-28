package com.example.domain.usecases

import com.example.domain.models.FoodItem
import com.example.domain.models.FoodItemCache
import com.example.domain.repository.FoodRepository
import javax.inject.Inject

class SaveFoodToDbUseCase @Inject constructor(private val foodRepository: FoodRepository) {

    suspend operator fun invoke(foodItem: FoodItemCache){
        foodRepository.saveToDatabase(foodItem)
    }
}