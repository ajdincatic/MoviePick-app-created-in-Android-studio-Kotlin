package com.example.moviepick.Services

import com.example.moviepick.Model.Posts
import com.example.moviepick.Model.Quote
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface QuoteService {
    @GET("Quote")
    fun getAll(): Call<List<Quote>>

    @POST("Quote")
    fun post(@Body newItem: Quote): Call<Quote>
}