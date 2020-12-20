package com.example.wheretoeat.repository

import com.example.wheretoeat.api.RestaurantApi
import com.example.wheretoeat.models.City
import com.example.wheretoeat.models.RestaurantHolder

class RestaurantRepository {

    suspend fun getRestaurants(page: Int): RestaurantHolder {
        return RestaurantApi.retrofitService.getRestaurants(page)
    }

    suspend fun getCities(): City {
        return RestaurantApi.retrofitService.getCities()
    }

    suspend fun getRestaurantsByCity(city: String): RestaurantHolder {
        return RestaurantApi.retrofitService.getRestaurantsFromCity(city)
    }


}