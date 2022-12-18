package com.example.mvvm.model.remote

import com.example.mvvm.model.remote.dto.SearchNewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {
    @Headers("Authorization: ${Constant.AUTH_TOKEN}")
    @GET("latest-news")
    fun getLatestNews(): Call<SearchNewsResponse>

    companion object {
        fun getInstance(): ApiService = ApiClient.retrofit.create(ApiService::class.java)
    }
}