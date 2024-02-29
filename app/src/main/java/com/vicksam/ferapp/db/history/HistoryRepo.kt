package com.vicksam.ferapp.db.history

import androidx.lifecycle.LiveData

class HistoryRepository(private val historyDao: HistoryDao) {
    val allHistory: LiveData<List<FullHistory>> = historyDao.getAllHistory()
    suspend fun insertHistory(history: History){
        historyDao.insertHistory(history)
    }
    suspend fun clearByUserId(userId: Int){
        historyDao.clearByUserId(userId)
    }

    suspend fun getHistorybyUserId(userId: Int): LiveData<List<FullHistory>> {
        return historyDao.getHistorybyUserId(userId)
    }
}