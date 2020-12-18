package com.example.wheretoeat.repository

import com.example.wheretoeat.api.RestaurantApi
import com.example.wheretoeat.models.RestaurantHolder

class RestaurantRepository {

    suspend fun getRestaurants(): RestaurantHolder{
        return RestaurantApi.retrofitService.getRestaurants()
    }



}