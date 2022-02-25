package com.example.data.api

import com.example.domain.models.FoodItem
import com.example.domain.models.FoodItemInfo
import com.example.domain.models.Resource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.InputStream

interface ApiService {

    @GET("getSearchPotraviny.php?pid=s3Rg3i11")
    suspend fun getFoodList(@Query("Q") string: String): com.example.data.models.FoodItem

    @GET("getPotravina.php?pid=s3Rg3i11")
    suspend fun getFoodItemById(@Query("GUID_Potravina") string: String): com.example.data.models.FoodItemInfo
}