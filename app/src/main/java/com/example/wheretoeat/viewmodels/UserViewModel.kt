package com.example.wheretoeat.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.wheretoeat.models.City
import com.example.wheretoeat.models.User
import com.example.wheretoeat.room.UserDatabase
import com.example.wheretoeat.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository
    var user: MutableLiveData<User> = MutableLiveData()

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun getUserByEmail(email: String,password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getUserByEmail(email,password)
            user.postValue(response)
        }
    }



}