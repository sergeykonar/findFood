package com.example.foodapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.FoodItem
import com.example.foodapp.R

class SearchFoodAdapter(private val data: ArrayList<FoodItem>, private val itemListener: ItemListener): RecyclerView.Adapter<SearchFoodAdapter.ViewHolder>() {

    interface ItemListener{
        fun onOpen(position: Int)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val itemName: TextView = view.findViewById(R.id.searchFoodItem_name)
        val openBtn: AppCompatImageButton = view.findViewById(R.id.searchFoodItem_openBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_search_food_item, parent,  false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = data[position].name
        holder.openBtn.setOnClickListener{
            itemListener.onOpen(position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}