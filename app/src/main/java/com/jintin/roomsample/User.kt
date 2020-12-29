package com.jintin.roomsample

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @ColumnInfo(name = "user")
    val name: String,
    val age: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)