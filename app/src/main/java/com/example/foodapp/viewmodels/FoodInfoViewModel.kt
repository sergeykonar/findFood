package com.example.foodapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.models.Resource
import com.example.domain.usecases.GetFoodByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FoodInfoViewModel @Inject constructor(private val useCase: GetFoodByIdUseCase): ViewModel(){

    fun getFoodInfoResponse(id: String) = flow{
        emit(Resource.LoadingState)
        try {
            delay(100)
            emit(Resource.DataState(useCase.invoke(id)))
        }catch (e: Exception){
            emit(Resource.ErrorState(e))
        }
    }
}