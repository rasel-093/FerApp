package com.vicksam.ferapp.db.guidance

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vicksam.ferapp.db.user.UserViewModel

class GuidanceViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GuidanceViewModel::class.java)) {
            return GuidanceViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}