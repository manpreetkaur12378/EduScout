package com.example.eduscout
import android.app.Application
import androidx.room.Room
import com.example.eduscout.data.db.AppDatabase

class App: Application() {
    companion object {
        lateinit var database: AppDatabase
    }
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "eduscout-db")
            .fallbackToDestructiveMigration()
            .build()
    }
}
