package com.vicksam.ferapp.emotionhistory

data class EmotionLogEntry(
    val emotionId: Int,
    val emotionName: String,
    val advice: String,
    val timeStamp: String
)
