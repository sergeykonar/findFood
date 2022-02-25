package com.example.foodapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.domain.models.Resource
import com.example.domain.usecases.GetFoodItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: GetFoodItemsUseCase) :
    ViewModel() {

    var searchResult: String = ""

    fun getFoodResponse(string: String) = flow {
        emit(Resource.LoadingState)
        try {
            emit(Resource.DataState(useCase(string)))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(e.localizedMessage)
        }
    }

}