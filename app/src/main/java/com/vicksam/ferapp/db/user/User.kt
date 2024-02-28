package com.vicksam.ferapp.db.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true) val userid: Int,
    val age: Int,
    val name:String,
    val gender: String,
    val username: String,
    val password: String,
)