package com.example.mvvm.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm.model.local.News
import com.example.mvvm.model.repository.Repository

class NewsViewModel(application: Application, private val repository: Repository) : AndroidViewModel(application) {
    val latestNews: LiveData<List<News>> = repository.getLatestNews()
    var isProcessing: MutableLiveData<Boolean> = repository.isProcessing

    fun refreshNews() {
        repository.updateLatestNews()
    }
}