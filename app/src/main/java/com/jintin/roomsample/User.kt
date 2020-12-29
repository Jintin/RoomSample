package com.jintin.roomsample

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    val name: String,
    val age: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)