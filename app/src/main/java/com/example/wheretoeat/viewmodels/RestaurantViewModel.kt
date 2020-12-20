package com.example.wheretoeat.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wheretoeat.models.City
import com.example.wheretoeat.repository.RestaurantRepository
import com.example.wheretoeat.models.Restaurant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantViewModel : ViewModel() {

    val restaurants: MutableLiveData<List<Restaurant>> = MutableLiveData<List<Restaurant>>()
    private val repository: RestaurantRepository
    var currentRestaurant: MutableLiveData<Restaurant> = MutableLiveData()
    var cities: MutableLiveData<City> = MutableLiveData()
    var currentCity: MutableLiveData<String> = MutableLiveData()

    init {
        repository = RestaurantRepository()
    }

    fun getRestaurant(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getRestaurants(page)
            restaurants.postValue(response.restaurants)
        }
    }

    fun getCities() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCities()
            cities.postValue(response)
        }
    }

    fun getRestaurantByCity(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getRestaurantsByCity(city)
            restaurants.postValue(response.restaurants)
        }
    }

}