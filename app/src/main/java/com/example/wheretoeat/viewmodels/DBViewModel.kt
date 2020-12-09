package com.example.wheretoeat.viewmodels

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wheretoeat.api.RestaurantApi
import com.example.wheretoeat.api.RestaurantApiService
import com.example.wheretoeat.models.Restaurant
import com.example.wheretoeat.models.RestaurantHolder
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class DBViewModel: ViewModel() {

    private val _response = MutableLiveData<RestaurantHolder>()

    val response: LiveData<RestaurantHolder>
        get() = _response

    init {
        getInitialRestaurants()
    }

    private fun getInitialRestaurants() {
        RestaurantApi.retrofitService.getRestaurants().enqueue(object : Callback<RestaurantHolder>{
            override fun onResponse(call: Call<RestaurantHolder>, response: Response<RestaurantHolder>) {
                _response.value = response.body()
                Log.d("response", _response.value.toString())
            }

            override fun onFailure(call: Call<RestaurantHolder>, t: Throwable) {
                Log.d(TAG,"GetInitialRestaurants failure")
            }
        })
    }
}
