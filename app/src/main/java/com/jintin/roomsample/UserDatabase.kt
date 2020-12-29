package com.jintin.roomsample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    companion object {
        private var instance: UserDatabase? = null
        private var DB_NAME = "db_name"

        fun getInstance(context: Context): UserDatabase {
            return instance ?: Room.databaseBuilder(context, UserDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build().also { instance = it }
        }
    }

    abstract fun userDao(): UserDao
}