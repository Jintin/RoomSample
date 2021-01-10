package com.jintin.roomsample

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class DbTest {
    private lateinit var userDao: UserDao
    private lateinit var db: UserDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, UserDatabase::class.java
        ).build()
        userDao = db.userDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun testInsert() = runBlocking {
        val list = getTestUsers()
        list.forEach {
            userDao.insert(it)
        }
        val result = userDao.list().first()
        assert(checkListEqual(list, result))
    }

    @Test
    fun testSelect() = runBlocking {
        val user = User("Peter", 23)
        userDao.insert(user)
        val result = userDao.getUser(user.name)
        assert(user == result)
    }

    @Test
    fun testRemove() = runBlocking {
        getTestUsers().toMutableList()
            .forEach {
                userDao.insert(it)
            }
        val list = userDao.list().first().toMutableList()
        val user = list.removeAt(2)

        userDao.delete(user)
        val result = userDao.list().first()
        assert(checkListEqual(list, result))
    }

    private fun getTestUsers() =
        listOf(
            User("Peter", 23),
            User("Jane", 35),
            User("Marry", 12),
        )

    private fun checkListEqual(expect: List<User>, result: List<User>): Boolean {
        if (expect.size != result.size) {
            return false
        }
        expect.forEach {
            if (!result.contains(it)) {
                return false
            }
        }
        return true
    }

}