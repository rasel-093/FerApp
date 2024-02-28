package com.vicksam.ferapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vicksam.ferapp.db.guidance.Guidance
import com.vicksam.ferapp.db.guidance.GuidanceDao
import com.vicksam.ferapp.db.history.History
import com.vicksam.ferapp.db.history.HistoryDao
import com.vicksam.ferapp.db.user.User
import com.vicksam.ferapp.db.user.UserDao

@Database(entities = [Guidance::class, User::class, History::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun guidanceDao(): GuidanceDao
    abstract fun userDao(): UserDao
    abstract fun historyDao(): HistoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
