package com.example.wheretoeat.api

import com.example.wheretoeat.models.Restaurant
import com.example.wheretoeat.models.RestaurantHolder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://ratpark-api.imok.space/"

private val retrofit = Retrofit.Builder()
//    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

interface RestaurantApiService {
    @GET("restaurants")
    suspend fun getRestaurants(): RestaurantHolder
}

object RestaurantApi {
    val retrofitService: RestaurantApiService by lazy {
        retrofit.create(RestaurantApiService::class.java)
    }
}