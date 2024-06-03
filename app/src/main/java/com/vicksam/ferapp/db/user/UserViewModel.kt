package com.vicksam.ferapp.db.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.vicksam.ferapp.db.AppDatabase
class UserViewModel(application: Application) : AndroidViewModel(application) {
    val allUsers: LiveData<List<User>>

    private val repository: UserRepository
    init {
        val userDao = AppDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsers
    }
}