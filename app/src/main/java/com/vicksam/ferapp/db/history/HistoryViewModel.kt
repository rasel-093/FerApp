package com.vicksam.ferapp.db.history

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vicksam.ferapp.db.AppDatabase
import com.vicksam.ferapp.db.guidance.Guidance
import com.vicksam.ferapp.db.guidance.GuidanceRepository
import kotlinx.coroutines.launch

class HistoryViewModel(application: Application) : AndroidViewModel(application) {
    val allHistory: LiveData<List<FullHistory>>
    private val repository: HistoryRepository
    init {
        val historyDao = AppDatabase.getInstance(application).historyDao()
        repository = HistoryRepository(historyDao)
        allHistory = repository.allHistory
    }

    fun insertHistory(history: History){
        viewModelScope.launch {
            repository.insertHistory(history)
        }
    }
}
