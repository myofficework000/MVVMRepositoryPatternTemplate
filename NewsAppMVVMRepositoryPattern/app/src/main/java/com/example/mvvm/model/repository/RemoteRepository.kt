package com.example.mvvm.model.repository

import com.example.mvvm.model.remote.ApiService

class RemoteRepository(private val apiService: ApiService) {
    fun loadLatestNews() = apiService.getLatestNews()
}