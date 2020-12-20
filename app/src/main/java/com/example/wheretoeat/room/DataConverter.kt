package com.example.wheretoeat.room

import androidx.room.TypeConverter
import com.example.wheretoeat.models.Restaurant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DataConverter {
    @TypeConverter
    fun fromRestaurantList(value: List<Restaurant>): String? {
        val gson = Gson()
        val type = object : TypeToken<List<Restaurant>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toRestaurantList(value: String): List<Restaurant>? {
        val gson = Gson()
        val type = object : TypeToken<List<Restaurant>>() {}.type
        return gson.fromJson(value, type)
    }
}