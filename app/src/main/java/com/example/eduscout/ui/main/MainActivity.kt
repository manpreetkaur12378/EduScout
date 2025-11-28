package com.example.eduscout.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eduscout.App
import com.example.eduscout.data.repository.UniversityRepository
import com.example.eduscout.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UniversityAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = App.database.universityDao()
        val repo = UniversityRepository(dao, applicationContext)
        viewModel = MainViewModel(application, repo)

        adapter = UniversityAdapter()
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter

        // FAB refresh
        binding.fabRefresh.setOnClickListener {
            viewModel.refreshFromApi()
        }

        // Observe simple StateFlow via coroutine
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.items.collect { list ->
                adapter.submitList(list)
                binding.tvLastUpdated.text = if (list.isNotEmpty()) "Last updated: " + android.text.format.DateFormat.format("yyyy-MM-dd HH:mm", list[0].lastUpdated) else "Never updated"
            }
        }

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.error.collect { err ->
                binding.tvError.text = err ?: ""
                binding.tvError.visibility = if (err == null) android.view.View.GONE else android.view.View.VISIBLE
            }
        }

        viewModel.loadFromDb()
    }
}
