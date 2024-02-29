package com.vicksam.ferapp.db.guidance

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface GuidanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGuidance(guidance: Guidance)

    @Update
    suspend fun updateGuidance(guidance: Guidance)

    @Delete
    suspend fun deleteGuidance(guidance: Guidance)

    @Query("SELECT * FROM guidance WHERE emotionType = :emotionType")
    suspend fun getGuidanceByType(emotionType: String): Guidance?

    @Query("SELECT * FROM guidance")
    fun getAllGuidance(): LiveData<List<Guidance>>
}