package com.example.domain.models

sealed class Resource<out T> {
    object LoadingState : Resource<Nothing>()
    data class ErrorState(var exception: Throwable) : Resource<Nothing>()
    data class DataState<T>(var data: T) : Resource<T>()
}