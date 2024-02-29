package com.vicksam.ferapp.db.history

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.vicksam.ferapp.db.user.User

//@Entity(tableName = "history")
@Entity(tableName = "history", foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["userid"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE)])
data class History(
    @PrimaryKey(autoGenerate = true) val historyId: Int,
    val dateTime: String,
    val userId: Int, //Foreign key
    val expressionType: Int,  //detection id
    val capturedFace: ByteArray?, // Optional field for storing captured face image data (BLOB)
    val emotion: String,
    val guidanceId:Int,
    val isRead: Boolean = false
)
