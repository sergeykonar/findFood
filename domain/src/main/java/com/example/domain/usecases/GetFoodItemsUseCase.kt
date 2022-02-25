package com.example.domain.usecases

import com.example.domain.models.FoodItem
import com.example.domain.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class GetFoodItemsUseCase @Inject constructor(private val foodRepository: FoodRepository) {

    suspend operator fun invoke(string: String) = withContext(Dispatchers.IO){
        foodRepository.getFoodItems(string)
    }
}