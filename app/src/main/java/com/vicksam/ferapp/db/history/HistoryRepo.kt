package com.vicksam.ferapp.db.history

import androidx.lifecycle.LiveData

class HistoryRepository(private val historyDao: HistoryDao) {
    val allHistory: LiveData<List<FullHistory>> = historyDao.getAllHistory()
    suspend fun insertHistory(history: History){
        historyDao.insertHistory(history)
    }
}