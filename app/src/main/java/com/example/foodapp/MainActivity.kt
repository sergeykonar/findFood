package com.example.foodapp

import android.app.SearchManager
import android.content.Intent
import android.database.MatrixCursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.view.autofill.AutofillValue
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.FoodItem
import com.example.domain.models.FoodItemCache
import com.example.domain.models.Resource
import com.example.foodapp.adapters.SearchFoodAdapter
import com.example.foodapp.viewmodels.MainViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    var mainViewModel: MainViewModel? = null

    private var searchView: SearchView? = null
    private var searchFoodList: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var emptyListText: TextView? = null
    private lateinit var hint: List<*>
    private val mTag = MainActivity::class.java.canonicalName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        searchView = findViewById(R.id.searchView)
        searchFoodList = findViewById(R.id.foodList)
        progressBar = findViewById(R.id.progressBar)
        emptyListText = findViewById(R.id.emptyListText)

        loadHistory()
        setSearchView()
    }

    private fun loadHistory(){
        lifecycleScope.launch {
            mainViewModel?.getSavedFood()
                ?.collect(){
                    when(it){
                        is Resource.LoadingState -> {
                            Log.e(mTag, "Loading")
                        }
                        is Resource.DataState<*> -> {
                            hint = it.data as ArrayList<FoodItemCache>
                            Log.e(mTag, it.data.toString())
                        }
                        is Resource.ErrorState -> {
                            Log.e(mTag, it.exception.message.toString())
                        }
                    }
                }
        }
    }

    private fun setSearchView() {
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                getResults()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                mainViewModel?.searchResult = newText!!
                return true
            }
        })
    }

    private fun getResults(){
        lifecycleScope.launch(Dispatchers.IO){
            mainViewModel?.getFoodResponse(mainViewModel!!.searchResult)
                ?.collect(){
                    when(it){
                        is Resource.DataState<*> -> {
                            Log.e(mTag, it.data.toString())
                            launch(Dispatchers.Main) {
                                progressBar?.visibility = View.GONE
                                emptyListText?.visibility = View.GONE

                                searchFoodList?.adapter =
                                    SearchFoodAdapter(it.data as ArrayList<FoodItem>, object: SearchFoodAdapter.ItemListener{
                                        override fun onOpen(position: Int) {
                                            val intent = Intent(baseContext, FoodInfoActivity::class.java)
                                            val gson = Gson()
                                            val str = gson.toJson((it.data as ArrayList<FoodItem>)[position])
                                            intent.putExtra("item", str)
                                            startActivity(intent)
                                        }

                                    })
                                searchFoodList?.layoutManager = LinearLayoutManager(
                                    baseContext,
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                            }
                        }
                        is Resource.ErrorState -> {
                            Log.e(mTag, it.exception.message.toString())
                        }
                        is Resource.LoadingState -> {
                            launch(Dispatchers.Main) {
                                progressBar?.visibility = View.VISIBLE
                            }
                            Log.e(mTag, "Loading")
                        }
                    }
                }
        }
    }

}