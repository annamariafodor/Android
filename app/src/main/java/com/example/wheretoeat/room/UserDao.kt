package com.example.wheretoeat.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.wheretoeat.models.Restaurant
import com.example.wheretoeat.models.User

@Dao
@TypeConverters(DataConverter::class)
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Query("SELECT * FROM user_table WHERE email = :email AND password = :password" )
    suspend fun getUserByEmail(email: String, password: String): User

    @Query("SELECT * FROM user_table WHERE email = :email")
    suspend fun getFavourites(email: String): User

    @Query("UPDATE user_table SET favourites = :favourites WHERE email = :email")
    suspend fun updateFavourites(favourites: List<Restaurant>, email: String)

}