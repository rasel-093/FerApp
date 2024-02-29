package com.vicksam.ferapp.db.history

data class FullHistory (
    val historyId: Int,
    val dateTime: String,
    val userId: Int,
    val expressionType: Int,  //detection id
    val capturedFace: ByteArray?, // Optional field for storing captured face image data (BLOB)
    val emotion: String,
    val guidanceText:String,
    val isRead: Boolean = false
)