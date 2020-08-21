package com.example.moviepick.Services

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object APIService {
    private const val URL = "https://jsonplaceholder.typicode.com/"

    private val okHttp = OkHttpClient.Builder()

    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T{
        return retrofit.create(serviceType)
    }

}