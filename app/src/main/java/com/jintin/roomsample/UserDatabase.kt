package com.jintin.roomsample

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [User::class], version = 2)
abstract class UserDatabase : RoomDatabase() {
    companion object {
        private var instance: UserDatabase? = null
        private var DB_NAME = "db_name"
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE users ADD COLUMN address TEXT")
            }
        }

        fun getInstance(context: Context): UserDatabase {
            return instance ?: Room.databaseBuilder(context, UserDatabase::class.java, DB_NAME)
//                .fallbackToDestructiveMigration()
                .addMigrations(MIGRATION_1_2)
                .build().also { instance = it }
        }
    }

    abstract fun userDao(): UserDao
}