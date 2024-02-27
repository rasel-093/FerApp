package com.vicksam.ferapp.emotionhistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vicksam.ferapp.R

class EmotionHistoryAdapter(private val emotionHistory: List<EmotionLogEntry>) :
    RecyclerView.Adapter<EmotionHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val emotionTextView: TextView = itemView.findViewById(R.id.emotion_text_view)
        val adviceTextView: TextView = itemView.findViewById(R.id.advice_text_view)
        val timeStampTextView: TextView = itemView.findViewById(R.id.timestamp_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.emotion_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = emotionHistory[position]
        holder.emotionTextView.text = entry.emotionName
        holder.adviceTextView.text = entry.advice
        holder.timeStampTextView.text = entry.timeStamp
    }

    override fun getItemCount(): Int = emotionHistory.size
}
