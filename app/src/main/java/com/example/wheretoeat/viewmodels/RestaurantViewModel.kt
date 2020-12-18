package com.example.wheretoeat.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wheretoeat.repository.RestaurantRepository
import com.example.wheretoeat.models.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantViewModel: ViewModel() {

    val restaurants: MutableLiveData<List<Restaurant>> = MutableLiveData<List<Restaurant>>()
    private val repository: RestaurantRepository
    var currentRestaurant: MutableLiveData<Restaurant> = MutableLiveData()

    init {
        repository = RestaurantRepository()
    }

    fun getRestaurant(){
        viewModelScope.launch(Dispatchers.IO){
            val response = repository.getRestaurants()
            restaurants.postValue(response.restaurants)
        }
    }

}