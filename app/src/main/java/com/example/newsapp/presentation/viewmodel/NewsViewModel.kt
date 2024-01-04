package com.example.newsapp.presentation.viewmodel

// NewsViewModel.kt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.model.ArticlesItem
import com.example.newsapp.data.model.NewsApiResponse
import com.example.newsapp.data.model.SourcesApiResponse
import com.example.newsapp.data.model.SourcesItem
import com.example.newsapp.data.remote.NewsApiService
import com.example.newsapp.data.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    private val _newsData = MutableLiveData<List<ArticlesItem>>()
    val newsData: LiveData<List<ArticlesItem>> get() = _newsData

    private val _sourcesNewsData = MutableLiveData<List<SourcesItem>>()
    val sourcesNewsData: LiveData<List<SourcesItem>> get() = _sourcesNewsData

    // Replace with your actual API key
    private val apiKey = "7a0aa3f209fc45e893f939e3bb7d9443"

    private val newsApiService = RetrofitClient.createService(NewsApiService::class.java)

    private var currentPage = 1
    private val pageSize = 100
    private lateinit var currentCategory:String

    fun getTopHeadlines() {
        val call = newsApiService.getTopHeadlines("us", apiKey,"100")
        call.enqueue(object : Callback<NewsApiResponse> {
            override fun onResponse(
                call: Call<NewsApiResponse>,
                response: Response<NewsApiResponse>
            ) {
                if (response.isSuccessful) {
                    _newsData.value = response.body()?.articles!!
                }
            }

            override fun onFailure(call: Call<NewsApiResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
    fun getNewsByCategory(category:String) {
        currentCategory =category
        val call = newsApiService.getNewCategory("us", apiKey,category,"100")
        call.enqueue(object : Callback<NewsApiResponse> {
            override fun onResponse(
                call: Call<NewsApiResponse>,
                response: Response<NewsApiResponse>
            ) {
                if (response.isSuccessful) {
                    _newsData.value = response.body()?.articles!!
                }
            }

            override fun onFailure(call: Call<NewsApiResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
    fun getNewsBySources(source:String) {
        val call = newsApiService.getNewsBySources( apiKey,source)
        call.enqueue(object : Callback<NewsApiResponse> {
            override fun onResponse(
                call: Call<NewsApiResponse>,
                response: Response<NewsApiResponse>
            ) {
                if (response.isSuccessful) {
                    _newsData.value = response.body()?.articles!!
                }
            }

            override fun onFailure(call: Call<NewsApiResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
    fun getSources() {
        val call = newsApiService.getSources(apiKey)
        call.enqueue(object : Callback<SourcesApiResponse> {
            override fun onResponse(
                call: Call<SourcesApiResponse>,
                response: Response<SourcesApiResponse>
            ) {
                if (response.isSuccessful) {
                    _sourcesNewsData.value = response.body()?.sources!!
                }
            }

            override fun onFailure(call: Call<SourcesApiResponse>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
