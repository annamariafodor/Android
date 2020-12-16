package com.example.wheretoeat.room

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun getUserByEmail(email: String,password: String): User{
        return userDao.getUserByEmail(email,password)
    }

}