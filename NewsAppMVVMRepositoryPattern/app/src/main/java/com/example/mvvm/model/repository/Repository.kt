package com.example.mvvm.model.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm.model.local.News
import com.example.mvvm.model.remote.dto.SearchNewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(
    val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
) : IRepository {
    override val isProcessing = MutableLiveData<Boolean>()

    override fun getLatestNews(): LiveData<List<News>> {
        updateLatestNews()
        return localRepository.getLatestNews()
    }

    override fun updateLatestNews() {
        val call: Call<SearchNewsResponse> = remoteRepository.loadLatestNews()
        call.enqueue(object : Callback<SearchNewsResponse> {
            override fun onResponse(
                call: Call<SearchNewsResponse>,
                response: Response<SearchNewsResponse>
            ) {
                isProcessing.postValue(false)
                if (!response.isSuccessful) {
                    return
                }
                val newsResponse: SearchNewsResponse = response.body() ?: return

                if (newsResponse.status == "ok") {
                    localRepository.saveNews(newsResponse.news)
                }
            }

            override fun onFailure(call: Call<SearchNewsResponse>, t: Throwable) {
                isProcessing.postValue(false)
                t.printStackTrace()
            }
        })
    }
}