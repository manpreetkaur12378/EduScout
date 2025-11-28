package com.example.eduscout.ui.main
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.eduscout.data.repository.UniversityRepository

class MainViewModel(app: Application, private val repo: UniversityRepository): AndroidViewModel(app) {
    private val _items = MutableStateFlow<List<com.example.eduscout.data.model.UniversityEntity>>(emptyList())
    val items: StateFlow<List<com.example.eduscout.data.model.UniversityEntity>> = _items

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadFromDb() {
        viewModelScope.launch {
            val list = repo.getAll()
            _items.value = list
        }
    }

    fun refreshFromApi() {
        viewModelScope.launch {
            val r = repo.fetchFromApiAndSave()
            if (r.isSuccess) {
                val list = repo.getAll()
                _items.value = list
                _error.value = null
            } else {
                _error.value = r.exceptionOrNull()?.localizedMessage
            }
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            val list = repo.search(query)
            _items.value = list
        }
    }

    fun filterByProvince(province: String) {
        viewModelScope.launch {
            if (province == "All") {
                _items.value = repo.getAll()
            } else {
                _items.value = repo.filterByProvince(province)
            }
        }
    }
}
