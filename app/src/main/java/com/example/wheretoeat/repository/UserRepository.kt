package com.example.wheretoeat.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.wheretoeat.models.Restaurant
import com.example.wheretoeat.models.User
import com.example.wheretoeat.room.DataConverter
import com.example.wheretoeat.room.UserDao
import retrofit2.Response

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun getUserByEmail(email: String, password: String): User {
        return userDao.getUserByEmail(email, password)
    }

    suspend fun getFavourites(email: String): User {
        return userDao.getFavourites(email)
    }

    suspend fun updateFavourites(favourites: List<Restaurant>, email: String) {
        return userDao.updateFavourites(favourites, email)
    }

}