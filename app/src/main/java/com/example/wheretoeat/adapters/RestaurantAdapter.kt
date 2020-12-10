package com.example.wheretoeat.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.R
import com.example.wheretoeat.models.Restaurant

class RestaurantAdapter (private var restaurants: List<Restaurant>): RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//        val restaurantName = holder.itemView.findViewById<TextView>(R.id.name)
//        val restaurantNamestaurantAddress = holder.itemView.findViewById<TextView>(R.id.address)
//        val restaurantPrice = holder.itemView.findViewById<TextView>(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurant_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = restaurants[position]
        val restaurantName = holder.itemView.findViewById<TextView>(R.id.name)
        val restaurantAddress = holder.itemView.findViewById<TextView>(R.id.address)
        val restaurantPrice = holder.itemView.findViewById<TextView>(R.id.price)

        restaurantName.text = currentItem.name
        restaurantAddress.text = currentItem.address
        restaurantPrice.text = currentItem.price.toString()
    }

    override fun getItemCount(): Int = restaurants.size

}