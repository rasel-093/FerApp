package com.vicksam.ferapp.db.guidance

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.vicksam.ferapp.db.AppDatabase
import com.vicksam.ferapp.db.user.User
import kotlinx.coroutines.launch

class GuidanceViewModel(application: Application) : AndroidViewModel(application) {
    val allGuidance: LiveData<List<Guidance>>
    val repository: GuidanceRepository
    init {
        val guidanceDao = AppDatabase.getInstance(application).guidanceDao()
        repository = GuidanceRepository(guidanceDao)
        allGuidance = repository.allGuidance
    }

    fun insertGuidance(guidance: Guidance){
        viewModelScope.launch {
            repository.insertGuidance(guidance)
        }

    }
    fun getGuidanceByType(emotionType:String, onComplete:(Guidance?)->Unit){
        viewModelScope.launch {
            val guidance = repository.getGuidanceByType(emotionType)
            onComplete(guidance)
        }

    }
}
