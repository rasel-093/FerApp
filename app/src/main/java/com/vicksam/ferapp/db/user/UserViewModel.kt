package com.vicksam.ferapp.db.user

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vicksam.ferapp.db.AppDatabase
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    val allUsers: LiveData<List<User>>
    private val repository: UserRepository

    init {
        val userDao = AppDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsers
    }
    fun insertUser(user: User){
        viewModelScope.launch { repository.insertUser(user) }
    }

    fun getUser(username:String, onComplete:(User?)->Unit){
        viewModelScope.launch {
            val user = repository.getUser(username)
            onComplete(user)
        }
    }
}