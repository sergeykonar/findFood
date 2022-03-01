package com.example.foodapp.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.domain.models.FoodItem
import com.example.domain.models.FoodItemCache
import com.example.domain.models.Resource
import com.example.domain.usecases.GetFoodByIdUseCase
import com.example.domain.usecases.SaveFoodToDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FoodInfoViewModel @Inject constructor(private val useCase: GetFoodByIdUseCase, private val insertUseCase: SaveFoodToDbUseCase): ViewModel(){

    private val mTag = FoodInfoViewModel::class.java.canonicalName

    fun getFoodInfoResponse(id: String) = flow{
        emit(Resource.LoadingState)
        try {
            delay(100)
            emit(Resource.DataState(useCase.invoke(id)))
        }catch (e: Exception){
            emit(Resource.ErrorState(e))
        }
    }

    suspend fun insertFood(foodItem: FoodItemCache) = withContext(Dispatchers.IO) {
        try{
            insertUseCase(foodItem)
        }catch (e: Exception){
            Log.e(mTag, e.message.toString())
        }
    }

}