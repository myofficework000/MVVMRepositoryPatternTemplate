package com.example.mvvm.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.databinding.ActivityMainBinding
import com.example.mvvm.model.local.AppDatabase
import com.example.mvvm.model.remote.ApiService
import com.example.mvvm.model.repository.LocalRepository
import com.example.mvvm.model.repository.RemoteRepository
import com.example.mvvm.model.repository.Repository
import com.example.mvvm.viewmodel.NewsViewModel
import com.example.mvvm.viewmodel.createFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var adapter: NewsRvAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        initObserver()
        callViewModel()
    }

    private fun callViewModel() {
        binding.btnSearch.setOnClickListener {
            viewModel.isProcessing.postValue(true)
            viewModel.refreshNews()
        }
    }

    private fun initObserver() {
        binding.rvNews.layoutManager = LinearLayoutManager(this)
        viewModel.isProcessing.observe(this) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        viewModel.latestNews.observe(this) {
            if (it.isNotEmpty()) {
                adapter = NewsRvAdapter(it)
                binding.rvNews.adapter = adapter
            }
        }
    }

    private fun initViewModel() {
        val remoteRepository = RemoteRepository(ApiService.getInstance())
        val localRepository = LocalRepository(AppDatabase.getInstance(this.applicationContext))
        val repository = Repository(localRepository, remoteRepository)
        val factory = NewsViewModel(application, repository).createFactory()
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
    }
}