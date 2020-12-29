package com.jintin.roomsample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database: UserDatabase = UserDatabase.getInstance(application)
    private val userDao = database.userDao()
    private val userName = listOf("Peter", "Jane", "Tim", "Tina")

    val liveData = database.userDao().list()

    fun update(user: User) {
        viewModelScope.launch {
            userDao.update(user)
        }
    }

    fun delete(user: User) {
        viewModelScope.launch {
            userDao.delete(user)
        }
    }

    fun addRandomUser() {
        viewModelScope.launch {
            val name = userName[Random.nextInt(userName.size)]
            val user = User(name, Random.nextInt(100))
            userDao.insert(user)
        }
    }
}