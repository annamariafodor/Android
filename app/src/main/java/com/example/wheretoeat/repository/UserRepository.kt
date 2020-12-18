package com.example.wheretoeat.repository

import androidx.lifecycle.LiveData
import com.example.wheretoeat.models.User
import com.example.wheretoeat.room.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun getUserByEmail(email: String,password: String): User {
        return userDao.getUserByEmail(email,password)
    }

}