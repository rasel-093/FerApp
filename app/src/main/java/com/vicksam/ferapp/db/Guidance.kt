package com.vicksam.ferapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guidance")
data class Guidance(
    @PrimaryKey(autoGenerate = true) val guidanceId: Int,
    val guidanceText: String,
    val emotionType: String,
)
