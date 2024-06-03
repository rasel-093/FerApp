package com.vicksam.ferapp.db.user

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    val allUsers: LiveData<List<User>> = userDao.getAllUsers()
}