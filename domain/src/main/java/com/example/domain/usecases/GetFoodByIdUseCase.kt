package com.example.domain.usecases

import com.example.domain.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFoodByIdUseCase @Inject constructor(private val foodRepository: FoodRepository) {

    suspend operator fun invoke(id: String) = withContext(Dispatchers.IO){
        foodRepository.getFoodItemById(id)
    }
}