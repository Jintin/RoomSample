package com.jintin.roomsample

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM users WHERE user = :name")
    suspend fun getUser(name: String): User

    @Query("SELECT * FROM users")
    fun list(): Flow<List<User>>
}