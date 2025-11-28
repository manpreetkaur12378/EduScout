package com.example.eduscout.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "universities")
data class UniversityEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val province: String,
    val web: String,
    val country: String,
    val lastUpdated: Long
)
