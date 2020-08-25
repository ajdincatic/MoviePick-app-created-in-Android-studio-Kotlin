package com.example.moviepick.Services

import android.util.Base64
import com.example.moviepick.Model.User
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.logging.Logger

object APIService {
    private const val URL = "https://8c6278dbdebd.ngrok.io/api/"
    var username: String = ""
    var password: String = ""
    var loggedUser: User? = null

    private val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttp = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor{ chain ->
                    val newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Basic " + Base64.encodeToString(("$username:$password").toByteArray(),Base64.NO_WRAP))
                        .build()
                    chain.proceed(newRequest)
                }

    private val builder = Retrofit.Builder().baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private val retrofit = builder.build()

    fun <T> buildService(serviceType: Class<T>): T{
        return retrofit.create(serviceType)
    }

}