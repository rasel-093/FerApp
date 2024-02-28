package com.vicksam.ferapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vicksam.ferapp.emotionhistory.EmotionHistoryAdapter
import com.vicksam.ferapp.emotionhistory.EmotionLogEntry

class ProfileActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var emotionHistory: List<EmotionLogEntry>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Initialize your emotionHistory list with data
        emotionHistory = listOf(
            EmotionLogEntry(1,"Happy", "Ok","2024-02-27 08:00:00"),
            EmotionLogEntry(2,"Sad","Stay busy", "2024-02-27 10:00:00"),
            // ... add more entries
        )
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = EmotionHistoryAdapter(emotionHistory)
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

}