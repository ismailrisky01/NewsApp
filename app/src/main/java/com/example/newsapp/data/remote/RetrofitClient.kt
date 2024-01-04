package com.example.newsapp.data.remote

// RetrofitClient.kt

import okhttp3.CacheControl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private fun getRetrofit(): Retrofit {
        val interceptor = run {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addNetworkInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build()
                chain.proceed(request)
            }
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T> createService(serviceClass: Class<T>): T {
        return getRetrofit().create(serviceClass)
    }
}
