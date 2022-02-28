package com.example.data.repository

import com.example.data.models.FoodItem
import com.example.data.models.FoodItemDb
import com.example.data.models.FoodItemInfo
import com.example.domain.models.Hodnota

class FoodMapper {

    fun toDomainList(list: ArrayList<FoodItem.Potravina>): ArrayList<com.example.domain.models.FoodItem>{
        val data = ArrayList<com.example.domain.models.FoodItem>()
        list.forEach {
            data.add(com.example.domain.models.FoodItem(it.guid_potravina, it.nazev, it.energy, it.photo.toString()))
        }
        return data
    }

    fun toFoodItemInfo(foodItem: FoodItemInfo): com.example.domain.models.FoodItemInfo {
        val list = toHodnotaList(foodItem.hodnoty)

        return com.example.domain.models.FoodItemInfo(foodItem.guid_potravina, foodItem.nazev, foodItem.kategorie, list)
    }

    fun toHodnotaList(list: ArrayList<FoodItemInfo.Hodnoty>): ArrayList<Hodnota>{
        val data = ArrayList<Hodnota>()
        list.forEach {
            val hodnota = Hodnota(
                energy = it.energie.toString(),
                proteins = it.bilkoviny.toString(),
                carbohydrates = it.sacharidy.toString(),
                sugar = it.cukry.toString(),
                fat = it.tuky.toString()
            )
            data.add(hodnota)
        }
        return data
    }

    fun toFoodItemDb(foodItem: com.example.domain.models.FoodItemCache): FoodItemDb {
        return FoodItemDb(name = foodItem.name)
    }
}
