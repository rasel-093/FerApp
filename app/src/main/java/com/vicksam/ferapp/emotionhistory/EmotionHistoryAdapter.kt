package com.vicksam.ferapp.emotionhistory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.vicksam.ferapp.R
import com.vicksam.ferapp.db.history.FullHistory
import com.vicksam.ferapp.functions.byteArrayToBitmap

class EmotionHistoryAdapter(private val emotionHistory: List<FullHistory>) :
    RecyclerView.Adapter<EmotionHistoryAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val emotionTextView: TextView = itemView.findViewById(R.id.emotion_text_view)
        val adviceTextView: TextView = itemView.findViewById(R.id.advice_text_view)
        val timeStampTextView: TextView = itemView.findViewById(R.id.timestamp_text_view)
        val capturedImage: ImageView = itemView.findViewById(R.id.capturedIv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.emotion_history_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = emotionHistory[position]
        val capturedImageByteArray = entry.capturedFace
        val capturedImageBitmap = byteArrayToBitmap(capturedImageByteArray)
//            capturedImageByteArray?.let {
//            BitmapFactory.decodeByteArray(
//                capturedImageByteArray,
//                0,
//                it.size
//            )
//        }

        holder.emotionTextView.text = entry.emotion
        holder.adviceTextView.text = entry.guidanceText
        holder.timeStampTextView.text = entry.dateTime
        holder.capturedImage.setImageBitmap(capturedImageBitmap)
    }

    override fun getItemCount(): Int = emotionHistory.size
}
