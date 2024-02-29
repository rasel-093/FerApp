package com.vicksam.ferapp.db.guidance

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vicksam.ferapp.db.AppDatabase
import kotlinx.coroutines.launch

class GuidanceViewModel(application: Application) : AndroidViewModel(application) {
    private val allGuidance: LiveData<List<Guidance>>
    private val repository: GuidanceRepository
    init {
        val guidanceDao = AppDatabase.getInstance(application).guidanceDao()
        repository = GuidanceRepository(guidanceDao)
        allGuidance = repository.allGuidance
    }
    suspend fun getGuidanceById(id: Int): Guidance?{
      return repository.getGuidanceById(id)
    }
}
