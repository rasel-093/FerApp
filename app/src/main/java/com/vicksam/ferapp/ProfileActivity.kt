package com.vicksam.ferapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vicksam.ferapp.db.history.HistoryViewModel
import com.vicksam.ferapp.db.history.HistoryViewModelFactory
import com.vicksam.ferapp.emotionhistory.EmotionHistoryAdapter

class ProfileActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    //private lateinit var emotionHistory: List<EmotionLogEntry>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        val historyViewModel: HistoryViewModel =
            ViewModelProvider(this, HistoryViewModelFactory(application)).get(HistoryViewModel::class.java)


        recyclerView = findViewById(R.id.recycler_view)
        historyViewModel.allHistory.observe(this, Observer {
            if (!it.isNullOrEmpty()){
                recyclerView.adapter =  EmotionHistoryAdapter(it)
                recyclerView.layoutManager = LinearLayoutManager(this)
            }
        })
    }

}