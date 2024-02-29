package com.vicksam.ferapp.db.guidance

import androidx.lifecycle.LiveData

class GuidanceRepository(private val guidanceDao: GuidanceDao) {
    val allGuidance: LiveData<List<Guidance>> = guidanceDao.getAllGuidance()

    suspend fun insertGuidance(guidance: Guidance){
        guidanceDao.insertGuidance(guidance)
    }
    suspend fun getGuidanceByType(emotionType: String): Guidance?{
        return guidanceDao.getGuidanceByType(emotionType)
    }
}