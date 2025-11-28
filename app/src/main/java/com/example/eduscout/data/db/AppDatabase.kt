package com.example.eduscout.data.db
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eduscout.data.model.UniversityEntity

@Database(entities = [UniversityEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun universityDao(): UniversityDao
}
