package com.example.moviepick.Services

import com.example.moviepick.Model.Quote
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface QuoteService {
    @GET("Quote")
    fun getAll(): Call<List<Quote>>

    @POST("Quote")
    fun post(@Header("Authorization") authorization: String, @Body newItem: Quote): Call<Quote>
}