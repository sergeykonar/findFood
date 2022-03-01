package com.example.domain.usecases

import com.example.domain.repository.FoodRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSavedFoodUseCase @Inject constructor(private val foodRepository: FoodRepository) {

    suspend operator fun invoke() = withContext(Dispatchers.IO){
        foodRepository.getFoodDataDb()
    }
}