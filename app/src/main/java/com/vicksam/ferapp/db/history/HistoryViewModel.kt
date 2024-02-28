package com.vicksam.ferapp.db.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.vicksam.ferapp.db.AppDatabase
import com.vicksam.ferapp.db.guidance.Guidance
import com.vicksam.ferapp.db.guidance.GuidanceRepository

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    val allHistory: LiveData<List<History>>
    private val repository: HistoryRepository
    init {
        val historyDao = AppDatabase.getInstance(application).historyDao()
        repository = HistoryRepository(historyDao)
        allHistory = repository.allHistory
    }
}
