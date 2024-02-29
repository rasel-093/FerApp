package com.vicksam.ferapp.db.history

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface HistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: History)

    @Update
    suspend fun updateHistory(history: History)

    @Delete
    suspend fun deleteHistory(history: History)

    @Query("SELECT * FROM history WHERE userId = :userId")
    suspend fun getHistoryByUserId(userId: Int): List<History>

    @Query("SELECT historyId , dateTime,userId,expressionType,capturedFace,emotion,guidanceText,isRead FROM history INNER JOIN guidance ON history.guidanceId = guidance.guidanceId")
    fun getAllHistory(): LiveData<List<FullHistory>>

    @Query("SELECT * FROM history WHERE guidanceId = :guidanceId")
    fun getHistoryByGuidanceId(guidanceId: Int): LiveData<List<History>>
}