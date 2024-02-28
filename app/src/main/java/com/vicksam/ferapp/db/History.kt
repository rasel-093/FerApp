package com.vicksam.ferapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History(
    @PrimaryKey(autoGenerate = true) val historyId: Int,
    val dateTime: String,
    val userId: Int,
    val expressionType: Int,
    val capturedFace: ByteArray?, // Optional field for storing captured face image data (BLOB)
    val emotion: String,
    val guidanceId:Int,
    val isRead: Boolean
)
