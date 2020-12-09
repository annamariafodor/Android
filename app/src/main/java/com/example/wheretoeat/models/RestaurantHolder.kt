package com.example.wheretoeat.models

data class RestaurantHolder(
    val total_entries : Int,
    val per_page : Int,
    var current_page : Int,
    val restaurants : List<Restaurant>
)
