package com.vicksam.ferapp.db.history

import androidx.lifecycle.LiveData

class HistoryRepository(private val historyDao: HistoryDao) {
    val allHistory: LiveData<List<History>> = historyDao.getAllHistory()
}