package com.example.mvvm.model.remote.dto

import com.example.mvvm.model.local.News

data class SearchNewsResponse(
    val news: List<News>,
    val page: Int,
    val status: String
)