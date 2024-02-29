package com.vicksam.ferapp.db.user

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    val allUsers: LiveData<List<User>> = userDao.getAllUsers()
    //lateinit var user:LiveData<User>
    suspend fun insertUser(user: User){
        userDao.insertUser(user)
    }

    suspend fun getUser(username:String): User? {
       return  userDao.getUserById(username)
   }
}