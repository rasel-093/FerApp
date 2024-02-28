package com.vicksam.ferapp.db.guidance

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.vicksam.ferapp.db.AppDatabase

class GuidanceViewModel(application: Application) : AndroidViewModel(application) {
    private val allGuidance: LiveData<List<Guidance>>
    private val repository: GuidanceRepository
    init {
        val guidanceDao = AppDatabase.getInstance(application).guidanceDao()
        repository = GuidanceRepository(guidanceDao)
        allGuidance = repository.allGuidance
    }
}
