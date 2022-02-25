package com.example.foodapp

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.domain.models.FoodItem
import com.example.domain.models.FoodItemInfo
import com.example.domain.models.Resource
import com.example.foodapp.viewmodels.FoodInfoViewModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FoodInfoActivity : AppCompatActivity() {

    private var foodName: TextView? = null
    private var energyInfo: TextView? = null
    private var proteins: TextView? = null
    private var carbohydrates: TextView? = null
    private var fat: TextView? = null
    private var sugars: TextView? = null
    private var image: ImageView? = null

    private lateinit var foodObj: FoodItem

    private var foodInfoViewModel: FoodInfoViewModel? = null

    private val mTag = FoodInfoActivity::class.java.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_info)
        val intent = intent
        val str = intent.getStringExtra("item")

        if (str != null) {
            val gson = Gson()
            foodObj = gson.fromJson(str, FoodItem::class.java)
        }

        initViews()
        initViewModel()

    }

    private fun initViewModel() {
        foodInfoViewModel = ViewModelProvider(this)[FoodInfoViewModel::class.java]

        lifecycleScope.launch {
            foodInfoViewModel?.getFoodInfoResponse(foodObj.id)
                ?.collect(){
                    when(it){
                        is Resource.DataState<*> -> {
                            val i = it.data as FoodItemInfo
                            foodName?.text = i.name

                            Picasso.get().load(foodObj.photo).into(image)

                            energyInfo?.text = String.format("%s %s", resources.getString(R.string.energy), i.hodnoty[0].energy)
                            proteins?.text = String.format("%s %s", resources.getString(R.string.proteins), i.hodnoty[0].proteins)
                            carbohydrates?.text = String.format("%s %s", resources.getString(R.string.carbohydrates), i.hodnoty[0].carbohydrates)
                            fat?.text = String.format("%s %s", resources.getString(R.string.fat), i.hodnoty[0].fat)
                            sugars?.text = String.format("%s %s", resources.getString(R.string.sugar), i.hodnoty[0].sugar)

                            Log.e(mTag, String.format("Food name \"%s\" Characteristics \"%s\" ", i.name, i.hodnoty.toString()))
                        }
                        is Resource.ErrorState -> {
                            Log.e(mTag, it.exception.message.toString())
                        }
                        is Resource.LoadingState -> {

                        }
                    }
                }
        }
    }

    private fun initViews() {
        foodName = findViewById(R.id.foodInfo_name)
        energyInfo = findViewById(R.id.foodInfo_energy)
        proteins = findViewById(R.id.foodInfo_proteins)
        carbohydrates = findViewById(R.id.foodInfo_carbohydrates)
        fat = findViewById(R.id.foodInfo_fats)
        sugars = findViewById(R.id.foodInfo_sugar)
        image = findViewById(R.id.foodInfo_image)

        foodName?.text = foodObj.name
        energyInfo?.text = foodObj.energy
    }
}