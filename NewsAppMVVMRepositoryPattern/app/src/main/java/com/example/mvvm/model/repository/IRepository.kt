package com.example.mvvm.model.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm.model.local.News

interface IRepository {
    fun getLatestNews(): LiveData<List<News>>
    fun updateLatestNews()
    val isProcessing: MutableLiveData<Boolean>
}