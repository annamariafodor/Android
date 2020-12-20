package com.example.wheretoeat.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.R
import com.example.wheretoeat.models.Restaurant
import com.example.wheretoeat.ui.HomeFragment


class RestaurantAdapter(
    private var restaurants: List<Restaurant>,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.restaurant_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = restaurants[position]
        holder.restaurantName.text = currentItem.name
        holder.restaurantAddress.text = currentItem.address
        holder.restaurantPrice.text = currentItem.price.toString()
    }

    override fun getItemCount(): Int = restaurants.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val restaurantName: TextView = itemView.findViewById<TextView>(R.id.name)
        val restaurantAddress: TextView = itemView.findViewById<TextView>(R.id.address)
        val restaurantPrice: TextView = itemView.findViewById<TextView>(R.id.price)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(restaurants[position])
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(item: Restaurant)
    }


    fun setData(newData: List<Restaurant>) {
        restaurants = newData
        notifyDataSetChanged()
    }

}




