package com.example.wheretoeat.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.wheretoeat.room.DataConverter

@Entity(tableName = "user_table")
@TypeConverters(DataConverter::class)
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val email: String,
    val name: String,
    val phone: String,
    val address: String,
    val password: String,
    val favourites: List<Restaurant>
)