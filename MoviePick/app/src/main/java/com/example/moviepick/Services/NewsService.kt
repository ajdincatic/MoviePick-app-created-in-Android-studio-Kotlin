package com.example.moviepick.Services

import com.example.moviepick.Model.News
import com.example.moviepick.Model.Quote
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface NewsService {
    @GET("News")
    fun getAll(): Call<List<News>>

    @POST("News")
    fun post(@Body newItem: Quote): Call<News>
}