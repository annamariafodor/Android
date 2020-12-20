package com.example.wheretoeat.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wheretoeat.R
import com.example.wheretoeat.models.Restaurant
import com.example.wheretoeat.models.RestaurantHolder
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

        holder.favouriteIcon.setOnClickListener {
            listener.setFavourite(restaurants[holder.adapterPosition],holder.adapterPosition,holder.favouriteIcon)
//            setFavourite(currentItem, position, holder.favouriteIcon, holder.favouriteIconFilled)
        }


    }

    override fun getItemCount(): Int = restaurants.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        val restaurantName: TextView = itemView.findViewById<TextView>(R.id.name)
        val restaurantAddress: TextView = itemView.findViewById<TextView>(R.id.address)
        val restaurantPrice: TextView = itemView.findViewById<TextView>(R.id.price)
        val favouriteIcon: ImageView = itemView.findViewById(R.id.favouriteIcon)


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
        fun setFavourite(item: Restaurant,position: Int, icon: ImageView)
    }

    fun setFavourite(
        item: Restaurant,
        position: Int,
        favouriteIcon: ImageView,
        favouriteIconFilled: ImageView
    ) {

        favouriteIcon.visibility = View.GONE
        favouriteIconFilled.visibility = View.VISIBLE


        Log.d("log", "set as favourite")

    }

    fun removeFavourite(
        item: Restaurant,
        position: Int,
        favouriteIcon: ImageView,
        favouriteIconFilled: ImageView
    ) {

        favouriteIcon.visibility = View.VISIBLE
        favouriteIconFilled.visibility = View.GONE

        Log.d("log", "remove from favourite")
    }

    fun setData(newData: List<Restaurant>){
        restaurants=newData
        notifyDataSetChanged()
    }

}




