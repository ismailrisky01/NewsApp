package com.example.newsapp.data.remote

// NewsApiService.kt

import com.example.newsapp.data.model.NewsApiResponse
import com.example.newsapp.data.model.SourcesApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") pageSize: String,

    ): Call<NewsApiResponse>
    @GET("top-headlines")
    fun getNewCategory(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("category") category: String,
        @Query("pageSize") pageSize: String
    ): Call<NewsApiResponse>

    @GET("top-headlines")
    fun getNewsBySources(
        @Query("apiKey") apiKey: String,
        @Query("sources") sources: String
    ): Call<NewsApiResponse>
    @GET("top-headlines/sources")
    fun getSources(
        @Query("apiKey") apiKey: String
    ): Call<SourcesApiResponse>
}
