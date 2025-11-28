package com.example.eduscout.data.repository
import android.content.Context
import com.example.eduscout.App
import com.example.eduscout.data.db.UniversityDao
import com.example.eduscout.data.model.UniversityEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class UniversityRepository(private val dao: UniversityDao, private val context: Context) {
    companion object { const val API_URL = "http://universities.hipolabs.com/search?country=Canada" }

    suspend fun fetchFromApiAndSave(): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val url = URL(API_URL)
            val conn = url.openConnection() as HttpURLConnection
            conn.connectTimeout = 10_000
            conn.readTimeout = 10_000
            conn.requestMethod = "GET"
            val code = conn.responseCode
            if (code != 200) return@withContext Result.failure(Exception("HTTP \$code"))
            val text = conn.inputStream.bufferedReader().use { it.readText() }
            val arr = JSONArray(text)
            val list = mutableListOf<UniversityEntity>()
            val now = System.currentTimeMillis()
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                val name = obj.optString("name", "Unknown")
                val province = if (obj.isNull("state-province")) "N/A" else obj.optString("state-province", "N/A")
                val country = obj.optString("country", "Canada")
                val webPages = obj.optJSONArray("web_pages") ?: JSONArray()
                val web = if (webPages.length() > 0) webPages.optString(0, "No website available") else "No website available"
                list.add(UniversityEntity(name = name, province = province ?: "N/A", web = web, country = country, lastUpdated = now))
            }
            dao.insertAll(list)
            return@withContext Result.success(Unit)
        } catch (e: Exception) {
            return@withContext Result.failure(e)
        }
    }

    suspend fun getAll() = withContext(Dispatchers.IO) { dao.fetchAll() }
    suspend fun search(query: String) = withContext(Dispatchers.IO) { dao.searchByName(query) }
    suspend fun filterByProvince(province: String) = withContext(Dispatchers.IO) { dao.filterByProvince(province) }
}
