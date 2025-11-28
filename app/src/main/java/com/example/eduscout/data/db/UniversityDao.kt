package com.example.eduscout.data.db
import androidx.room.*
import com.example.eduscout.data.model.UniversityEntity

@Dao
interface UniversityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<UniversityEntity>)

    @Query("DELETE FROM universities")
    suspend fun deleteAll()

    @Query("SELECT * FROM universities ORDER BY name")
    suspend fun fetchAll(): List<UniversityEntity>

    @Query("SELECT * FROM universities WHERE name LIKE '%' || :query || '%' ORDER BY name")
    suspend fun searchByName(query: String): List<UniversityEntity>

    @Query("SELECT * FROM universities WHERE province = :province ORDER BY name")
    suspend fun filterByProvince(province: String): List<UniversityEntity>
}
