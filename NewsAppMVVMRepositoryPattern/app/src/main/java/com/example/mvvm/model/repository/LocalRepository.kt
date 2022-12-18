package com.example.mvvm.model.repository

import com.example.mvvm.model.local.AppDatabase
import com.example.mvvm.model.local.News

class LocalRepository(private val appDatabase: AppDatabase) {
    fun getLatestNews() = appDatabase.newsDao().getNews()
    fun saveNews(news: List<News>) = appDatabase.newsDao().saveNews(news)
}